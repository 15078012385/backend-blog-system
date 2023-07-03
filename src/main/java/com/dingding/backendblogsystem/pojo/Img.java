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
 * @description img
 * @date 2023-04-22
 */
@Data
@TableName("tb_img")
public class Img implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Integer id;

    /**
     * 图片url
     */
    private String img;

    /**
     * 上传人
     */
    private String uploadUser;

    /**
     * 上传时间
     */
    private Date uploadTime;

    public Img() {
    }
}