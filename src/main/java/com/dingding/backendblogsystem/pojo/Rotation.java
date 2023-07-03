package com.dingding.backendblogsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * @description rotation
 * @author dingding
 * @date 2023-04-22
 */
@Data
@TableName("tb_rotation")
public class Rotation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * id
    */
    private Integer id;

    /**
    * img
    */
    private String img;
    private Date uploadTime;

    public Rotation() {}
}