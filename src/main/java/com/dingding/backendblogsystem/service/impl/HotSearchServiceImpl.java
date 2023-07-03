package com.dingding.backendblogsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingding.backendblogsystem.mapper.HotSearchMapper;
import com.dingding.backendblogsystem.pojo.HotSearch;
import com.dingding.backendblogsystem.service.HotSearchService;
import org.springframework.stereotype.Service;

@Service
public class HotSearchServiceImpl extends ServiceImpl<HotSearchMapper, HotSearch> implements HotSearchService {
}
