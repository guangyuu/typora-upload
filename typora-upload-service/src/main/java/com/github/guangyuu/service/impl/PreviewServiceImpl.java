package com.github.guangyuu.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.guangyuu.common.exception.StoreException;
import com.github.guangyuu.common.utils.SignUtils;
import com.github.guangyuu.mapper.SysSettingMapper;
import com.github.guangyuu.pojo.po.TbSysSetting;
import com.github.guangyuu.service.PreviewService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @Author Guangyu
 * @Date 2022/11/3
 * @Description 预览 业务层接口实现类
 **/
@Service
public class PreviewServiceImpl implements PreviewService {
    @Autowired
    private SysSettingMapper sysSettingMapper;

    /**
     * 文件预览
     *
     * @param name     文件名
     * @param response 客户端响应
     */
    @Override
    public void preview(String name, HttpServletResponse response) {
        if (name == null) {
            throw new StoreException("无效的名称");
        }
        String secretKey = querySetting();
        String enc = name.substring(0, name.lastIndexOf("."));
        String filePath = SignUtils.dec(enc, secretKey);
        File file = new File(filePath);
        if (!file.exists()) {
            throw new StoreException("文件不存在");
        }
        byte[] bytes = FileUtil.readBytes(file);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String querySetting() {
        TbSysSetting setting = sysSettingMapper.selectOne(Wrappers.lambdaQuery(TbSysSetting.class).eq(TbSysSetting::getName, "jwt.secret.key"));
        if (setting == null) {
            throw new RuntimeException("查询系统配置失败");
        }
        return setting.getValue();
    }
}
