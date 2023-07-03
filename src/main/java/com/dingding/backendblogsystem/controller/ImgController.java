package com.dingding.backendblogsystem.controller;

import com.alibaba.fastjson.JSON;
import com.dingding.backendblogsystem.pojo.Img;
import com.dingding.backendblogsystem.service.ImgService;
import com.dingding.backendblogsystem.utils.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author dingding
 * @description img控制器
 * @date 2023-04-22
 */
@Slf4j
@RestController
@RequestMapping("/img")
public class ImgController {

    @Autowired
    private com.dingding.backendblogsystem.service.ImgService ImgService;


    @PostMapping("/add")
    public ApiResp addImg(@RequestBody Img Img) {
        Img.setUploadTime(new Date());
        ImgService.save(Img);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResp deleteImg(@PathVariable("id") Integer id) {
        ImgService.removeById(id);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/batch-delete")
    public ApiResp batchDeleteImg(@RequestBody List<Integer> ids) {
        ImgService.removeByIds(ids);
        return new ApiResp(200, "success", null);
    }

    @PutMapping("/update")
    public ApiResp updateImg(@RequestBody Img Img) {
        ImgService.updateById(Img);
        return new ApiResp(200, "success", null);
    }

    @GetMapping("/page")
    public ApiResp pageImg(@RequestParam("pageNum") Integer pageNum,
                           @RequestParam("pageSize") Integer pageSize,
                           @RequestParam(value = "param", required = false) String param) {
        QueryWrapper<Img> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("upload_time");
        return new ApiResp(200, "success", ImgService.page(new Page<>(pageNum, pageSize), wrapper));
    }

    @GetMapping("/page-user")
    public ApiResp pageImgUser(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "param") String param) {
        QueryWrapper<Img> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("upload_time");
        wrapper.eq("upload_user", param);
        return new ApiResp(200, "success", ImgService.page(new Page<>(pageNum, pageSize), wrapper));
    }

    @GetMapping("/get-count")
    public ApiResp getCount() {
        return new ApiResp(200, "success", ImgService.count());
    }
}