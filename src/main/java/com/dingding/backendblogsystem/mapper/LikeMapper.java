package com.dingding.backendblogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingding.backendblogsystem.pojo.Like;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description likeMapper
 * @author dingding.com
 * @date 2023-04-19
 */
@Mapper
public interface LikeMapper extends BaseMapper<Like> {
}