package com.dingding.backendblogsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingding.backendblogsystem.service.NoticeService;
import com.dingding.backendblogsystem.utils.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dingding.backendblogsystem.pojo.Notice;

import java.util.List;

/**
 * @author dingding
 * @description notice控制器
 * @date 2023-04-21
 */
@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/add")
    public ApiResp addNotice(@RequestBody Notice notice) {
        noticeService.save(notice);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResp deleteNotice(@PathVariable("id") Integer id) {
        noticeService.removeById(id);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/batch-delete")
    public ApiResp batchDeleteNotice(@RequestBody List<Integer> ids) {
        noticeService.removeByIds(ids);
        return new ApiResp(200, "success", null);
    }

    @PutMapping("/update")
    public ApiResp updateNotice(@RequestBody Notice notice) {
        noticeService.updateById(notice);
        return new ApiResp(200, "success", null);
    }

    @GetMapping("/page")
    public ApiResp pageNotice(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize,
                              @RequestParam(value = "param", required = false) String param) {
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        if (param != null) {
            wrapper.like("notice_content", param);
        }
        wrapper.orderByDesc("notice_time");
        return new ApiResp(200, "success", noticeService.page(new Page<>(pageNum, pageSize), wrapper));
    }
    @GetMapping("/get-count")
    public ApiResp getCount() {
        return new ApiResp(200, "success", noticeService.count());
    }

}