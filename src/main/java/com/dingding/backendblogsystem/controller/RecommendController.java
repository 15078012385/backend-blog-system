package com.dingding.backendblogsystem.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingding.backendblogsystem.pojo.Recommend;
import com.dingding.backendblogsystem.utils.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dingding
 * @description recommend控制器
 * @date 2023-04-21
 */
@Slf4j
@RestController
@RequestMapping("/recommend")
public class RecommendController {
    @Autowired
    private com.dingding.backendblogsystem.service.RecommendService RecommendService;

    @PostMapping("/add")
    public ApiResp addRecommend(@RequestBody Recommend Recommend) {
        RecommendService.save(Recommend);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResp deleteRecommend(@PathVariable("id") Integer id) {
        RecommendService.removeById(id);
        return new ApiResp(200, "success", null);
    }


    @PutMapping("/update")
    public ApiResp updateRecommend(@RequestBody Recommend Recommend) {
        RecommendService.updateById(Recommend);
        return new ApiResp(200, "success", null);
    }

    @GetMapping("/list")
    public ApiResp listRecommend() {
        return new ApiResp(200, "success", RecommendService.list());
    }

}