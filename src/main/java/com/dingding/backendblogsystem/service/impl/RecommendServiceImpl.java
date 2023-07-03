package com.dingding.backendblogsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingding.backendblogsystem.mapper.RecommendMapper;
import com.dingding.backendblogsystem.pojo.Recommend;
import com.dingding.backendblogsystem.service.RecommendService;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {
}
