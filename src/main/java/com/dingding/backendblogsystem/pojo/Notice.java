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
 * @description notice
 * @date 2023-04-21
 */
@Data
@TableName("tb_notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Integer id;

    /**
     * 发布日期
     */
    private Date noticeTime;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 发布用户
     */
    private String publishUser;

    public Notice() {
    }
}