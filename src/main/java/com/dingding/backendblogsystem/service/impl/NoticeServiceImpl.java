package com.dingding.backendblogsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingding.backendblogsystem.mapper.NoticeMapper;
import com.dingding.backendblogsystem.pojo.Notice;
import com.dingding.backendblogsystem.service.NoticeService;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
