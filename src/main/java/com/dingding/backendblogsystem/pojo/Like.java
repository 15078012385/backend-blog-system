package com.dingding.backendblogsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dingding.com
 * @description like
 * @date 2023-04-19
 */
@Data
@AllArgsConstructor
@TableName("tb_like")
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Integer id;

    /**
     * 文章作者
     */
    private String articleUser;

    /**
     * 文章id
     */
    private String articleId;

    private String articleTitle;
    /**
     * 点赞人
     */
    private String likeUser;

    /**
     * 点赞时间
     */
    private Date likeTime;

    public Like() {
    }
}