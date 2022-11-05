package com.github.guangyuu.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.guangyuu.common.utils.JwtUtils;
import com.github.guangyuu.mapper.SysSettingMapper;
import com.github.guangyuu.mapper.SysUserMapper;
import com.github.guangyuu.pojo.po.TbSysSetting;
import com.github.guangyuu.pojo.po.TbSysUser;
import com.github.guangyuu.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Guangyu
 * @Date 2022/11/5
 * @Description 用户 业务层接口
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysSettingMapper sysSettingMapper;
    @Autowired
    private SysUserMapper userMapper;


    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    @Override
    public List<TbSysUser> list() {
        return userMapper.selectList(null);
    }

    /**
     * 新增
     *
     * @param username 用户名
     * @return rows
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(String username) {
        // 查询用户是否存在
        Long count = userMapper.selectCount(Wrappers.lambdaQuery(TbSysUser.class).eq(TbSysUser::getUsername, username));
        if (count > 0) {
            throw new RuntimeException("重复的用户名");
        }
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUsername(username);
        // 生成accessToken
        String secretKey = querySetting();
        String accessToken = JwtUtils.generateToken(username, secretKey);
        if (StringUtils.isBlank(accessToken)) {
            throw new RuntimeException("保存失败");
        }
        tbSysUser.setAccessToken(accessToken);
        int insert = userMapper.insert(tbSysUser);
        if (insert == 0) {
            throw new RuntimeException("保存失败");
        }
        return insert;
    }

    /**
     * 删除
     *
     * @param id 用户标识
     * @return rows
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }


    public String querySetting() {
        TbSysSetting setting = sysSettingMapper.selectOne(Wrappers.lambdaQuery(TbSysSetting.class).eq(TbSysSetting::getName, "jwt.secret.key"));
        if (setting == null) {
            throw new RuntimeException("查询系统配置失败");
        }
        return setting.getValue();
    }
}
