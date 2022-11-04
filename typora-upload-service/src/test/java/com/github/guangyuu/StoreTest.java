package com.github.guangyuu;

import com.github.guangyuu.common.utils.SpaceUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.PortUnreachableException;

/**
 * @Author Guangyu
 * @Date 2022/11/4
 * @Description
 **/
@SpringBootTest
public class StoreTest {
    @Test
    public void test() {
        String s = SpaceUtils.randomPath();
        System.out.println(s);
    }
}
