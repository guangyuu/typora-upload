package com.github.guangyuu.common.pojo.po;

import lombok.Data;

/**
 * @Author Guangyu
 * @Date 2022/9/16
 * @Description 文件元数据
 **/
@Data
public class FileMetadata {
    /**
     * 文件签名
     */
    private String sign;
    /**
     * 原文件名
     */
    private String originalName;
    /**
     * 文件存储名称
     */
    private String storeName;
    /**
     * 存储时间
     */
    private Long storeTime;
    /**
     * 文件标识
     */
    private String uid;
    /**
     * 存储路径
     */
    private String path;
}
