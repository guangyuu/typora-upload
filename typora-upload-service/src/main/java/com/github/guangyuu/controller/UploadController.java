package com.github.guangyuu.controller;

import com.github.guangyuu.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Guangyu
 * @Date 2022/11/3
 * @Description 上传 前端控制器
 **/
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传
     *
     * @param file 目标文件
     * @return 预览地址
     */
    @PostMapping("/file")
    public String doUpload(@RequestPart("file") MultipartFile file) {
        return uploadService.doUpload(file);
    }
}
