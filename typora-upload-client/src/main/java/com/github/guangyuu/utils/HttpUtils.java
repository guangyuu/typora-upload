package com.github.guangyuu.utils;

import com.github.guangyuu.properties.ClientProperties;
import okhttp3.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Author Guangyu
 * @Date 2022/11/4
 * @Description Http请求客户端
 **/
public class HttpUtils {
    private static ClientProperties clientProperties;
    private static OkHttpClient httpClient;


    static {
        // 读取配置文件
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.github.guangyuu");
        clientProperties = applicationContext.getBean(ClientProperties.class);
        // 初始化http客户端
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        httpClient = builder.connectTimeout(clientProperties.getTimeout(), TimeUnit.SECONDS).build();

    }

    /**
     * 文件上传
     *
     * @param file 目标文件
     * @return 预览地址
     */
    public static String doUpload(File file) {
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "8888");
        try {
            // 请求地址
            String url = clientProperties.getHosts() + clientProperties.getUploadPath();
            // 构建请求体
            MediaType mediaType = MediaType.Companion.parse("multipart/form-data");
            RequestBody requestBody = RequestBody.Companion.create(file, mediaType);
            MultipartBody multipartBody = new MultipartBody.Builder()
                    .setType(mediaType)
                    .addFormDataPart("file", file.getName())
                    .addFormDataPart("file", file.getName(), requestBody).build();
            // 构建请求
            Request request = new Request.Builder().url(url)
                    .addHeader("username", clientProperties.getUsername())
                    .addHeader("token", clientProperties.getAccessToken())
                    .post(multipartBody)
                    .build();

            // 发送请求
            try (Response response = httpClient.newCall(request).execute()) {
                // 处理响应
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        return body.string();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("上传失败:" + e.getMessage());
        }
        return null;
    }
}
