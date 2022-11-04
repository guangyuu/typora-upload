package com.github.guangyuu.common.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.nio.charset.StandardCharsets;

/**
 * @Author Guangyu
 * @Date 2022/11/4
 * @Description 加密工具类
 **/
public class SignUtils {
    /**
     * 加密
     *
     * @param content   明文
     * @param secretKey 密钥
     * @return 密文
     */
    public static String enc(String content, String secretKey) {
        byte[] key = secretKey.getBytes(StandardCharsets.UTF_8);
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        return aes.encryptHex(content);
    }

    /**
     * 解密
     *
     * @param enc       密文
     * @param secretKey 密钥
     * @return 明文
     */
    public static String dec(String enc, String secretKey) {
        byte[] key = secretKey.getBytes(StandardCharsets.UTF_8);
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        return aes.decryptStr(enc, CharsetUtil.CHARSET_UTF_8);
    }
}
