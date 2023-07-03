package com.dingding.backendblogsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingding.backendblogsystem.mapper.UserMapper;
import com.dingding.backendblogsystem.pojo.User;
import com.dingding.backendblogsystem.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
