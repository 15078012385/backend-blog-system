package com.dingding.backendblogsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingding.backendblogsystem.pojo.Like;
import com.dingding.backendblogsystem.service.LikeService;
import com.dingding.backendblogsystem.utils.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//66666888h哈哈哈7
//66888h哈哈哈666
//66666

@Slf4j
@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/add")
    public ApiResp add(@RequestBody Like like) {
        String articleId = like.getArticleId();
        String likeUser = like.getLikeUser();
        QueryWrapper<Like> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId);
        wrapper.eq("like_user", likeUser);
        Like one = likeService.getOne(wrapper);
        if (one != null) {
            return new ApiResp(500, "您已经点过赞了", null);
        }
        like.setLikeTime(new java.util.Date());
        likeService.save(like);
        return new ApiResp(200, "success", null);
    }

    @RequestMapping("/page-by-user")
    public ApiResp pageByUser(@RequestParam("articleUser") String articleUser, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        // new一个构造器
        QueryWrapper<Like> wrapper = new QueryWrapper<>();
        // 按照时间倒序
        wrapper.orderByDesc("like_time");
        // 按照用户自己查询
        wrapper.eq("article_user", articleUser);
        // 分页查询
        Page<Like> page = likeService.page(new Page<>(pageNum, pageSize), wrapper);
        return new ApiResp(200, "success", page);
    }

}
