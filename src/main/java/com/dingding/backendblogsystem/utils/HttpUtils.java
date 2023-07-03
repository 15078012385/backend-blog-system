package com.dingding.backendblogsystem.utils;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

public class HttpUtils {

    private static final int TIMEOUT = 5000; // 超时时间（单位：毫秒）
    private static final int RETRY_COUNT = 3; // 重试次数

    /**
     * 发送 GET 请求
     *
     * @param url 请求的URL
     * @return 返回响应内容
     * @throws HttpException 超过重试次数仍然无法获取到响应时抛出异常
     */
    public static String doGet(String url) throws HttpException {
        // 创建 GET 请求，并设置超时时间
        HttpRequest request = HttpUtil.createGet(url).timeout(TIMEOUT);
        // 执行 HTTP 请求并进行重试
        return executeWithRetry(request, RETRY_COUNT);
    }

    /**
     * 执行 HTTP 请求并进行重试
     *
     * @param request    HTTP 请求
     * @param retryCount 重试次数
     * @return 返回响应内容
     * @throws HttpException 超过重试次数仍然无法获取到响应时抛出异常
     */
    private static String executeWithRetry(HttpRequest request, int retryCount) throws HttpException {
        HttpResponse response = null;
        // 重试指定次数
        for (int i = 0; i <= retryCount; i++) {
            try {
                // 执行 HTTP 请求
                response = request.execute();
                // 如果请求成功，则返回响应内容
                if (response.isOk()) {
                    return response.body();
                }
            } catch (Exception e) {
                // 发生异常时打印堆栈信息
                e.printStackTrace();
            }
        }
        // 如果超过重试次数仍然无法获取到响应，则抛出异常
        throw new HttpException("请求超时并达到重试次数上限，无法获取到响应！");
    }

}
