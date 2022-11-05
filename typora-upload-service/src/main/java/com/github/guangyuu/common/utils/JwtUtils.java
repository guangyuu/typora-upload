package com.github.guangyuu.common.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Guangyu
 * @Date 2022/11/5
 * @Description Jwt工具类
 **/
public class JwtUtils {
    /**
     * 生成Token
     *
     * @param username  用户名
     * @param secretKey 密钥
     * @return token
     */
    public static String generateToken(String username, String secretKey) {
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("issueAt", System.currentTimeMillis());
        return JWTUtil.createToken(header, payload, secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 校验Token是否有效
     *
     * @param token     令牌
     * @param secretKey 密钥
     * @return boolean
     */
    public static boolean verify(String token, String secretKey) {
        return JWTUtil.verify(token, secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解析Token
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String parseToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return String.valueOf(jwt.getPayload("username"));
    }
}
