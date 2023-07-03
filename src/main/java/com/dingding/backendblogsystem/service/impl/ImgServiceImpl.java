package com.dingding.backendblogsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingding.backendblogsystem.mapper.ImgMapper;
import com.dingding.backendblogsystem.pojo.Img;
import com.dingding.backendblogsystem.service.ImgService;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements ImgService {
}
