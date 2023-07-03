package com.dingding.backendblogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingding.backendblogsystem.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
/**
 * @description userMapper
 * @author dingding
 * @date 2023-04-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}