package com.dingding.backendblogsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingding.backendblogsystem.mapper.ArticleMapper;
import com.dingding.backendblogsystem.pojo.Article;
import com.dingding.backendblogsystem.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
