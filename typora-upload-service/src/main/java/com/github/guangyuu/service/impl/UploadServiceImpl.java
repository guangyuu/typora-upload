package com.github.guangyuu.service.impl;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.guangyuu.common.exception.StoreException;
import com.github.guangyuu.common.pojo.po.FileMetadata;
import com.github.guangyuu.common.utils.FileUtils;
import com.github.guangyuu.common.utils.IdWorker;
import com.github.guangyuu.common.utils.SignUtils;
import com.github.guangyuu.common.utils.SpaceUtils;
import com.github.guangyuu.mapper.SysSettingMapper;
import com.github.guangyuu.pojo.po.TbSysSetting;
import com.github.guangyuu.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author Guangyu
 * @Date 2022/11/3
 * @Description 上传 业务层接口实现类
 **/
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private SysSettingMapper sysSettingMapper;
    @Autowired
    private IdWorker idWorker;

    @Value("${typora.upload.service.previewPath}")
    private String previewPath;

    /**
     * 上传
     *
     * @param file 目标文件
     * @return 预览地址
     */
    @Override
    public String doUpload(MultipartFile file) {
        if (null == file) {
            throw new StoreException("未读取到目标文件");
        }
        // 构建文件元数据
        String path = SpaceUtils.randomPath();
        String originalFilename = file.getOriginalFilename();
        long fileUid = idWorker.nextId();
        FileMetadata metadata = metadataBuild(path, originalFilename, fileUid);

        // 文件签名
        try (InputStream inputStream = file.getInputStream()) {
            String sign = SecureUtil.md5(inputStream);
            metadata.setSign(sign);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = fileUid + extension;

        String metadataFileName = fileUid + ".json";
        String fileFullPath = path + File.separator + newFileName;

        String secretKey = querySetting();
        String storeName = SignUtils.enc(fileFullPath, secretKey) + extension;
        metadata.setStoreName(storeName);


        // 生成文件的原文件
        try {
            String fileMetadata = JSON.toJSONString(metadata, SerializerFeature.PrettyFormat);
            FileUtils.writeFile(path, metadataFileName, fileMetadata.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            // 删除元文件
            File file1 = new File(path, metadataFileName);
            if (file1.exists()) {
                file1.delete();
            }
            throw new RuntimeException(e);

        }
        // 生成上传文件
        try (InputStream inputStream = file.getInputStream()) {
            FastByteArrayOutputStream arrayOutputStream = IoUtil.read(file.getInputStream());
            byte[] bytes = arrayOutputStream.toByteArray();
            FileUtils.writeFile(path, storeName, bytes);
        } catch (IOException exception) {
            // 删除上传文件
            File file2 = new File(path, newFileName);
            if (file2.exists()) {
                file2.delete();
            }
            // 抛出异常
            throw new RuntimeException(exception);
        }

        return previewPath + storeName;
    }

    public FileMetadata metadataBuild(String path, String originalFilename, Long uid) {
        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setPath(path);
        fileMetadata.setOriginalName(originalFilename);
        fileMetadata.setUid(String.valueOf(uid));
        fileMetadata.setStoreTime(System.currentTimeMillis());

        return fileMetadata;
    }

    public String querySetting() {
        TbSysSetting setting = sysSettingMapper.selectOne(Wrappers.lambdaQuery(TbSysSetting.class).eq(TbSysSetting::getName, "jwt.secret.key"));
        if (setting == null) {
            throw new RuntimeException("查询系统配置失败");
        }
        return setting.getValue();
    }
}
