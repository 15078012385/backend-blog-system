package com.dingding.backendblogsystem.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingding.backendblogsystem.pojo.Rotation;
import com.dingding.backendblogsystem.service.RotationService;
import com.dingding.backendblogsystem.utils.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author dingding
 * @description rotation控制器
 * @date 2023-04-22
 */
@Slf4j
@RestController
@RequestMapping("/rotation")
public class RotationController {

    @Autowired
    private RotationService RotationService;


    @PostMapping("/add")
    public ApiResp addRotation(@RequestBody Rotation rotation) {
        rotation.setUploadTime(new Date());
        RotationService.save(rotation);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResp deleteRotation(@PathVariable("id") Integer id) {
        RotationService.removeById(id);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/batch-delete")
    public ApiResp batchDeleteRotation(@RequestBody List<Integer> ids) {
        RotationService.removeByIds(ids);
        return new ApiResp(200, "success", null);
    }

    @PutMapping("/update")
    public ApiResp updateRotation(@RequestBody Rotation rotation) {
        RotationService.updateById(rotation);
        return new ApiResp(200, "success", null);
    }

    @GetMapping("/page")
    public ApiResp pageRotation(@RequestParam("pageNum") Integer pageNum,
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam(value = "param", required = false) String param) {
        QueryWrapper<Rotation> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("upload_time");
        return new ApiResp(200, "success", RotationService.page(new Page<>(pageNum, pageSize), wrapper));
    }
}