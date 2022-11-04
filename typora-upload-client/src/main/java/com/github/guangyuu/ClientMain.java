package com.github.guangyuu;

import com.github.guangyuu.service.ClientUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author Guangyu
 * @Date 2022/11/3
 * @Description 主程序
 **/
public class ClientMain {
    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.github.guangyuu");) {
            ClientUploadService clientUploadService = applicationContext.getBean(ClientUploadService.class);
            if (null == args || args.length == 0) {
                return;
            }
            String filePath = args[0];
            if (StringUtils.isBlank(filePath)) {
                return;
            }
            String preview = clientUploadService.upload(filePath);
            System.out.println(preview);
        } catch (BeansException e) {
            e.printStackTrace();
            System.out.println("上传失败: " + e.getMessage());
        }
    }
}
