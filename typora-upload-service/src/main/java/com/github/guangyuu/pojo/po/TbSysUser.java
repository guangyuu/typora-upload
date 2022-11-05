package com.github.guangyuu.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Guangyu
 * @Date 2022/11/4
 * @Description 系统用户表
 **/
@Data
@TableName("tb_sys_user")
public class TbSysUser {
    /**
     * 主键标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 删除标识(0 未删除 1 删除)
     */
    @TableLogic(value = "0", delval = "1")
    private Integer delFlag;
    /**
     * 用户名
     */
    private String username;
    /**
     * 访问Token
     */
    private String accessToken;
}
