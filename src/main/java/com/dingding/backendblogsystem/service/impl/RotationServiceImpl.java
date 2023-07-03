package com.dingding.backendblogsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingding.backendblogsystem.mapper.RotationMapper;
import com.dingding.backendblogsystem.pojo.Rotation;
import com.dingding.backendblogsystem.service.RotationService;
import org.springframework.stereotype.Service;

@Service
public class RotationServiceImpl extends ServiceImpl<RotationMapper, Rotation> implements RotationService {
}
