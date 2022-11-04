package com.github.guangyuu;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.github.guangyuu.common.utils.SignUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

/**
 * @Author Guangyu
 * @Date 2022/11/4
 * @Description
 **/
@SpringBootTest
public class HuToolSecurityTest {
    @Test
    public void testAesSign() {

        String content = "//2022//11//44//00//aa//";
        String key = "c^3B1!4Y#8C$Dxy#";
        String enc = SignUtils.enc(content, key);
        System.out.println(enc);
        String dec = SignUtils.dec(enc, key);
        System.out.println(dec);
    }

    @Test
    public void testSm4Sign() {
        String content = "//2022//11//44//00//aa//";
        SymmetricCrypto sm4 = new SymmetricCrypto("SM4");

        String encryptHex = sm4.encryptHex(content);
        System.out.println(encryptHex);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println(decryptStr);
    }
}
