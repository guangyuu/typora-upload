package com.github.guangyuu.service.impl;

import com.github.guangyuu.service.ClientUploadService;
import com.github.guangyuu.utils.HttpUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;

/**
 * @Author Guangyu
 * @Date 2022/11/4
 * @Description 客户端上传 服务类接口实现类
 **/
@Service
public class ClientUploadServiceImpl implements ClientUploadService {
    /**
     * 文件上传
     *
     * @param filePath 文件绝对路径
     * @return 文件存储地址
     */
    @Override
    public String upload(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException(filePath + ": 未能根据文件路径找到文件");
        }
        return HttpUtils.doUpload(file);
    }
}
