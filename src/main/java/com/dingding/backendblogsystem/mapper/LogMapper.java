package com.dingding.backendblogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingding.backendblogsystem.pojo.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
/**
 * @description logMapper
 * @author dingding
 * @date 2023-04-21
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}