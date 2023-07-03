package com.dingding.backendblogsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * @description recommend
 * @author dingding
 * @date 2023-04-21
 */
@Data
@TableName("tb_recommend")
public class Recommend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * id
    */
    private Integer id;

    /**
    * 推荐内容
    */
    private String recommendContent;

    public Recommend() {}
}