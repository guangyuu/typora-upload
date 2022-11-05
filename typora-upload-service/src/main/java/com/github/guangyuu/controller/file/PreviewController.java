package com.github.guangyuu.controller.file;

import com.github.guangyuu.service.PreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author Guangyu
 * @Date 2022/11/3
 * @Description 预览 前端控制器
 **/
@RestController
@RequestMapping("/api/typora/preview")
public class PreviewController {
    @Autowired
    private PreviewService previewService;

    /**
     * 文件预览
     *
     * @param name     文件名
     * @param response 客户端响应
     */
    @GetMapping("/{name}")
    public void preview(@PathVariable("name") String name, HttpServletResponse response) {
        previewService.preview(name, response);
    }
}
