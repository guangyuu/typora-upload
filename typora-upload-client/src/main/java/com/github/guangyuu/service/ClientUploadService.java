package com.github.guangyuu.service;

/**
 * @Author Guangyu
 * @Date 2022/11/4
 * @Description 客户端上传 服务类接口
 **/
public interface ClientUploadService {
    /**
     * 文件上传
     *
     * @param filePath 文件绝对路径
     * @return 文件存储地址
     */
    String upload(String filePath);
}
