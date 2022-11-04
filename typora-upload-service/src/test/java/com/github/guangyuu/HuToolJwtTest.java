package com.github.guangyuu;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Guangyu
 * @Date 2022/11/4
 * @Description
 **/
@SpringBootTest
public class HuToolJwtTest {

    @Test
    public void testGenerate() {
        String key = "c^3B1!4Y#8C$Dxy#";
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        Map<String, Object> payload = new HashMap<>();
        payload.put("username", "dev");

        String token1 = JWTUtil.createToken(header, payload, key.getBytes(StandardCharsets.UTF_8));
        System.out.println(token1);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImRldiJ9.DoiGzUoHcPF_rpd1BO3uWVv3hzpQV3dcFENSmIGEiEA";
        boolean verify = JWTUtil.verify(token, key.getBytes(StandardCharsets.UTF_8));
        System.out.println(verify);

        if (verify){
            JWT jwt = JWTUtil.parseToken(token);
            Object username = jwt.getPayload("username");
            System.out.println(username);
        }

    }
}
