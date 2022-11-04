package com.github.guangyuu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.guangyuu.pojo.po.TbSysUser;
import org.springframework.stereotype.Repository;

/**
 * @Author Guangyu
 * @Date 2022/11/4
 * @Description 系统用户表 Mapper接口
 **/
@Repository
public interface SysUserMapper extends BaseMapper<TbSysUser> {
}
