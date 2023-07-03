package com.dingding.backendblogsystem.controller;

import com.dingding.backendblogsystem.service.ArticleService;
import com.dingding.backendblogsystem.service.ImgService;
import com.dingding.backendblogsystem.service.NoticeService;
import com.dingding.backendblogsystem.service.UserService;
import com.dingding.backendblogsystem.utils.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/data-echart")
public class DataEChartController {

    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ImgService imgService;

    @GetMapping
    public ApiResp getDataEChart() {
        ArrayList<Object> dataList = new ArrayList<>();
        dataList.add(userService.count());
        dataList.add(noticeService.count());
        dataList.add(articleService.count());
        dataList.add(imgService.count());
        return new ApiResp(200, "success", dataList);
    }
}
