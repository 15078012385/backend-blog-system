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
 * @description article
 * @date 2023-04-22
 */
@Data
@TableName("tb_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Integer id;

    /**
     * 发布用户
     */
    private String publishUser;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 文章简介
     */
    private String articleIntroduction;

    /**
     * 内容
     */
    private String articleContent;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 文章浏览量
     */
    private String views;
    private String avatar;

    /**
     * 首图
     */
    private String firstImg;

    public Article() {
    }
}