package com.dingding.backendblogsystem.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Lux Sun
 * @date 2022/7/5
 */
public class DingUtil {
    private static final String SECRET = "SECe4c86ea170c3aa45456284ffe705d4fba2a63738336e101cf459ddf8f883ab91";
    private static final String URL = "https://oapi.dingtalk.com/robot/send?access_token=a588b711f6eb81c160d4028225a6a89002638322109d12f06450cca33dd71af8";

    public static void send(String content) throws ApiException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + SECRET;
        Mac mac = Mac.getInstance("HmacSHA256");

        mac.init(new SecretKeySpec(SECRET.getBytes("UTF-8"), "HmacSHA256"));

        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        String signResult = "&timestamp=" + timestamp + "&sign=" + sign;
        // 得到拼接后的 URL
        String url = URL + signResult;
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(content);
        request.setText(text);
        OapiRobotSendResponse response = client.execute(request);
    }

}