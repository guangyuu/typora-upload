package com.github.guangyuu.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author Guangyu
 * @Date 2022/11/3
 * @Description 预览 业务层接口
 **/
public interface PreviewService {
    /**
     * 文件预览
     *
     * @param name     文件名
     * @param response 客户端响应
     */
    void preview(String name, HttpServletResponse response);
}
