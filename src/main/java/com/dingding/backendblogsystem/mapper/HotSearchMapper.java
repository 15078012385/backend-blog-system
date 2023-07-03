package com.dingding.backendblogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingding.backendblogsystem.pojo.HotSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
/**
 * @description hot_searchMapper
 * @author dingding
 * @date 2023-04-21
 */
@Mapper
public interface HotSearchMapper extends BaseMapper<HotSearch> {

}