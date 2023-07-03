package com.dingding.backendblogsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingding.backendblogsystem.pojo.LoginInfo;
import com.dingding.backendblogsystem.pojo.QrCodeSession;
import com.dingding.backendblogsystem.pojo.User;
import com.dingding.backendblogsystem.service.UserService;
import com.dingding.backendblogsystem.utils.ApiResp;
import com.dingding.backendblogsystem.utils.DingUtil;
import com.dingding.backendblogsystem.utils.HttpUtils;
import com.dingding.backendblogsystem.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/qr-login")
public class QrLoginController {

    @Value("${wxLogin.tempUserIdUrl}")
    private String tempUserIdUrl;

    @Value("${wxLogin.secretKey}")
    private String secretKey;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 生成二维码
     * 思路：先从“码上登录”服务器，获取会话信息
     * 然后从会话信息中提取"带有唯一session标识"的二维码url，最后生成二维码图片
     *
     * @param response
     * @throws IOException
     * @throws WriterException
     * @throws WriterException
     */
    @GetMapping("/generation-qrcode")
    public ApiResp generationQrcode(HttpServletResponse response) throws IOException, WriterException, WriterException {
        // 获取会话信息
        String loginInfo = HttpUtils.doGet(tempUserIdUrl + "?secretKey=" + secretKey);
        // ObjectMapper json转换工具
        JsonNode jsonNode = new ObjectMapper().readTree(loginInfo);
        // 从响应json中提取二维码url, qrCodeReturnUrl中，携带了session会话的唯一标识
        String url = jsonNode.get("data").get("qrCodeReturnUrl").asText();
        // 会话信息存入redis
        writeRedis(jsonNode.get("data").get("tempUserId").asText());

        // 生成二维码图片
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 200, 200);
        // 将二维码转换成 BufferedImage，并替换黑白像素
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int color = bitMatrix.get(x, y) ? 0x0099ff : 0xFFFFFF; // 二维码调色
                image.setRGB(x, y, color);
            }
        }
        // 发送到客户端
        response.setContentType("image/png");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        byte[] imageBytes = bos.toByteArray();

        // 返回二维码图片、session会话唯一标识
        return ApiResp.success(jsonNode.get("data").get("tempUserId").asText(), "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes));
    }

    private void writeRedis(String tempUserId) {
        QrCodeSession qrCodeSession = new QrCodeSession();
        qrCodeSession.setSessionId(tempUserId);
        qrCodeSession.setLoginStatus(0);
        // 将会话信息存入redis
        redisUtils.set(tempUserId, qrCodeSession, 60);
    }

    /**
     * 扫码确认回调
     *
     * @param loginInfo
     * @return
     */
    @Autowired
    private UserService userService;

    @RequestMapping("/acknowledge-callback")
    public String acknowledgeCallback(LoginInfo loginInfo) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, ApiException {
        Object o = redisUtils.get(loginInfo.getTempUserId());
        System.out.println("扫码确认回调");
        System.out.println(JSONObject.toJSONString(loginInfo));
        if (o != null) {
            User userInfo = new User();
            userInfo.setWxName(loginInfo.getNickname());
            userInfo.setWxId(loginInfo.getUserId());
            userInfo.setAvatar(loginInfo.getAvatar());
            userInfo.setLastLogin(new Date());
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("wx_id", loginInfo.getUserId());
            User one = userService.getOne(wrapper);
            if (one == null) {
//                设置默认数值
                userInfo.setCreateTime(new Date());
                userInfo.setAvailableStatus(0);
                userInfo.setIdentity("普通用户");
//                userInfo转json串

                DingUtil.send("新用户注册了,注册信息{" +JSONObject.toJSONString(userInfo) + "}"); // 推送钉钉消息
                userService.save(userInfo);
            } else {
                userService.update(userInfo, wrapper);
            }

            // 将会话信息存入redis
            QrCodeSession qrCodeSession = new QrCodeSession();
            qrCodeSession.setUserId(loginInfo.getUserId());
            qrCodeSession.setSessionId(loginInfo.getTempUserId());
            qrCodeSession.setLoginStatus(1);
            redisUtils.set(loginInfo.getTempUserId(), qrCodeSession, 3);
            // 响应给"码登陆服务器"服务器
            return "{\"errcode\": 0, \"message\": \"登录成功.\"}";
        } else {
            return "{\"errcode\": 1, \"message\": \"登录失败.\"}";
        }

    }


    // 前端轮询当前登录状态
    @RequestMapping("/get-login-status")
    public ApiResp getLoginStatus(String tempUserId) throws JsonProcessingException {
        QrCodeSession qrCodeSession = (QrCodeSession) redisUtils.get(tempUserId);
        if (qrCodeSession == null) {
            return ApiResp.success("当前会话过期，请刷新二维码。", null);
        }
        if (qrCodeSession.getLoginStatus() == 0) {
            return ApiResp.success("待确认登录", null);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.ge("wx_id", qrCodeSession.getUserId());
        wrapper.eq("wx_id", qrCodeSession.getUserId());
        User one = userService.getOne(wrapper);
        if(one.getAvailableStatus()==1){
            return ApiResp.success("您已被封禁,请联系管理员。", null);
        }
        return ApiResp.success("登录成功", one);
    }

}
