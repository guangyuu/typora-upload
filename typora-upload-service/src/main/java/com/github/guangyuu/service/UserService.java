package com.github.guangyuu.service;

import com.github.guangyuu.pojo.po.TbSysUser;

import java.util.List;

/**
 * @Author Guangyu
 * @Date 2022/11/5
 * @Description 用户 业务层接口
 **/
public interface UserService {
    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    List<TbSysUser> list();

    /**
     * 新增
     *
     * @param username 用户名
     * @return rows
     */
    int add(String username);

    /**
     * 删除
     *
     * @param id 用户标识
     * @return rows
     */
    int delete(Long id);
}
