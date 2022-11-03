package com.github.guangyuu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author Guangyu
 * @Date 2022/11/3
 * @Description 主启动类
 **/
@EnableScheduling
@SpringBootApplication
public class TyporaUploadServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TyporaUploadServiceApplication.class, args);
    }
}
