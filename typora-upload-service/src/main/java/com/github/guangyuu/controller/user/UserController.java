package com.github.guangyuu.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.github.guangyuu.common.pojo.vo.R;
import com.github.guangyuu.pojo.po.TbSysUser;
import com.github.guangyuu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Guangyu
 * @Date 2022/11/5
 * @Description 用户管理前端控制器
 **/
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    @GetMapping("/list")
    public R<List<TbSysUser>> list() {
        return R.ok(userService.list());
    }

    /**
     * 新增
     *
     * @param object 请求体
     * @return rows
     */
    @PostMapping("/add")
    public R<Integer> add(@RequestBody JSONObject object) {
        if (object == null) {
            throw new RuntimeException("保存失败");
        }
        String username = object.getString("username");
        return R.ok(userService.add(username));
    }

    /**
     * 删除
     *
     * @param id 用户标识
     * @return rows
     */
    @DeleteMapping("/{id}")
    public R<Integer> delete(@PathVariable("id") Long id) {
        return R.ok(userService.delete(id));
    }


}
