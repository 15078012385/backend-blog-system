package com.dingding.backendblogsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingding.backendblogsystem.pojo.User;
import com.dingding.backendblogsystem.service.UserService;
import com.dingding.backendblogsystem.utils.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author dingding
 * @description user控制器
 * @date 2023-04-22
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService UserService;

    @PostMapping("/add")
    public ApiResp addUser(@RequestBody User User) {
        UserService.save(User);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResp deleteUser(@PathVariable("id") Integer id) {
        UserService.removeById(id);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/batch-delete")
    public ApiResp batchDeleteUser(@RequestBody List<Integer> ids) {
        UserService.removeByIds(ids);
        return new ApiResp(200, "success", null);
    }

    @PutMapping("/update")
    public ApiResp updateUser(@RequestBody User User) {
        UserService.updateById(User);
        return new ApiResp(200, "success", null);
    }

    @GetMapping("/page")
    public ApiResp pageUser(@RequestParam("pageNum") Integer pageNum,
                            @RequestParam("pageSize") Integer pageSize,
                            @RequestParam(value = "param", required = false) String param) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (param != null) {
            wrapper.like("wx_name", param);
        }
        wrapper.orderByDesc("create_time");
        return new ApiResp(200, "success", UserService.page(new Page<>(pageNum, pageSize), wrapper));
    }
    @GetMapping("/get-count")
    public ApiResp getCount() {
        return new ApiResp(200, "success", UserService.count());
    }

}
