package com.dingding.backendblogsystem.controller;

import com.alibaba.fastjson.JSON;
import com.dingding.backendblogsystem.mapper.LogMapper;
import com.dingding.backendblogsystem.pojo.Like;
import com.dingding.backendblogsystem.pojo.Log;
import com.dingding.backendblogsystem.service.LogService;
import com.dingding.backendblogsystem.utils.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dingding
 * @description log控制器
 * @date 2023-04-21
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/page")
    public ApiResp pageByUser(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        // new一个构造器
        QueryWrapper<Log> wrapper = new QueryWrapper<>();
        // 按照时间倒序
        wrapper.orderByDesc("access_time");
        // 分页查询
        Page<Log> page = logService.page(new Page<>(pageNum, pageSize), wrapper);
        return new ApiResp(200, "success", page);
    }

    @Autowired
    private LogMapper logMapper;

    //    new java.sql.Date(log.getAccessTime().getTime()).toLocalDate()
    @GetMapping("/get-echart-data")
    public ApiResp getEChartData() {
        List<Map<String, Object>> resultList = logMapper.selectMaps(new QueryWrapper<Log>()
                .select("DATE(access_time) AS access_date", "COUNT(*) AS access_count")
                .between("access_time", DateUtils.addDays(new Date(), -7), new Date())
                .groupBy("access_date")
                .orderByAsc("access_date"));
        HashMap<LocalDate, Long> accessCount = new HashMap<>();
        resultList.forEach(result -> {
            java.sql.Date accessDate = (java.sql.Date) result.get("access_date");
            LocalDate localDate = accessDate.toLocalDate();
            Long count = (Long) result.get("access_count");
            System.out.println(localDate + ": " + count);
            accessCount.put(localDate, count);
        });


        return new ApiResp(200, "success", accessCount);
    }

}