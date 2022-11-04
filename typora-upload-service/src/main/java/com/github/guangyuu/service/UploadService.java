package com.github.guangyuu.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Guangyu
 * @Date 2022/11/3
 * @Description 上传 业务层接口
 **/
public interface UploadService {
    /**
     * 上传
     *
     * @param file 目标文件
     * @return 预览地址
     */
    String doUpload(MultipartFile file);
}
