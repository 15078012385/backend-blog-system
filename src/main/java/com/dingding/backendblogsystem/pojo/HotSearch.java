package com.dingding.backendblogsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author dingding
 * @description hot_search
 * @date 2023-04-21
 */
@Data
@TableName("tb_hot_search")
public class HotSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Integer id;

    /**
     * 内容
     */
    private String hotSearchContent;

    /**
     * 热搜指数
     */
    private Integer searchCount;

}