package com.dingding.backendblogsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingding.backendblogsystem.pojo.Article;
import com.dingding.backendblogsystem.pojo.HotSearch;
import com.dingding.backendblogsystem.service.HotSearchService;
import com.dingding.backendblogsystem.utils.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author dingding
 * @description article控制器
 * @date 2023-04-22
 */
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private com.dingding.backendblogsystem.service.ArticleService articleService;

    @PostMapping("/add")
    public ApiResp addArticle(@RequestBody Article article) {
        if (article.getFirstImg().equals("")) {
            article.setFirstImg("https://img0.baidu.com/it/u=3032776730,2178451350&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800");
        }
        article.setViews("0");
        article.setPublishTime(new Date());
        articleService.save(article);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResp deleteArticle(@PathVariable("id") Integer id) {
        articleService.removeById(id);
        return new ApiResp(200, "success", null);
    }

    @DeleteMapping("/batch-delete")
    public ApiResp batchDeleteArticle(@RequestBody List<Integer> ids) {
        articleService.removeByIds(ids);
        return new ApiResp(200, "success", null);
    }

    @PutMapping("/update")
    public ApiResp updateArticle(@RequestBody Article Article) {
        Article.setUpdateTime(new Date());
        articleService.updateById(Article);
        return new ApiResp(200, "success", null);
    }


    @GetMapping("/getById/{id}")
    public ApiResp getArticle(@PathVariable("id") Integer id) {
        Article article = articleService.getById(id);
        article.setViews(String.valueOf(Integer.parseInt(article.getViews()) + 1));
        articleService.updateById(article);
        return new ApiResp(200, "success", article);
    }

    @Autowired
    private HotSearchService hotSearchService;

    @GetMapping("/page")
    public ApiResp pageArticle(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "param", required = false) String param) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        if (param != null && !param.equals("") && !param.trim().equals("")) {
            HotSearch one = hotSearchService.getOne(new QueryWrapper<HotSearch>().eq("hot_search_content", param));
            if (one == null) {
                HotSearch hotSearch = new HotSearch();
                hotSearch.setHotSearchContent(param);
                hotSearch.setSearchCount(1);
                hotSearchService.save(hotSearch);
            } else {
                one.setSearchCount(one.getSearchCount() + 1);
                hotSearchService.updateById(one);
            }
            wrapper.like("article_title", param).or().like("article_content", param).or().like("article_introduction", param);
        }
        wrapper.orderByDesc("publish_time");
        return new ApiResp(200, "success", articleService.page(new Page<>(pageNum, pageSize), wrapper));
    }

    @GetMapping("/page-user")
    public ApiResp pageArticleUser(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "param") String param) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("publish_user", param);
        wrapper.orderByDesc("publish_time");
        return new ApiResp(200, "success", articleService.page(new Page<>(pageNum, pageSize), wrapper));
    }


    @GetMapping("/get-count")
    public ApiResp getCount() {
        return new ApiResp(200, "success", articleService.count());
    }

}