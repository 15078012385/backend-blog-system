package com.dingding.backendblogsystem.controller;

import com.dingding.backendblogsystem.pojo.HotSearch;
import com.dingding.backendblogsystem.service.HotSearchService;
import com.dingding.backendblogsystem.utils.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author dingding
 * @description hot_search控制器
 * @date 2023-04-21
 */
@Slf4j
@RestController
@RequestMapping("/hot-search")
public class HotSearchController {
    @Autowired
    private HotSearchService HotSearchService;

    @PostMapping("/add")
    public ApiResp addHotSearch(@RequestBody HotSearch HotSearch) {
        HotSearchService.save(HotSearch);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResp deleteHotSearch(@PathVariable("id") Integer id) {
        HotSearchService.removeById(id);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/batch-delete")
    public ApiResp batchDeleteHotSearch(@RequestBody List<Integer> ids) {
        HotSearchService.removeByIds(ids);
        return new ApiResp(200, "success", null);
    }

    @PutMapping("/update")
    public ApiResp updateHotSearch(@RequestBody HotSearch HotSearch) {
        HotSearchService.updateById(HotSearch);
        return new ApiResp(200, "success", null);
    }

    @GetMapping("/page")
    public ApiResp pageHotSearch(@RequestParam("pageNum") Integer pageNum,
                                 @RequestParam("pageSize") Integer pageSize,
                                 @RequestParam(value = "param", required = false) String param) {
        QueryWrapper<HotSearch> wrapper = new QueryWrapper<>();
        if (param != null) {
            wrapper.like("hot_search_content", param);
        }
        wrapper.orderByDesc("search_count");
        return new ApiResp(200, "success", HotSearchService.page(new Page<>(pageNum, pageSize), wrapper));
    }

}