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
 * @description user
 * @date 2023-04-22
 */
@Data
@TableName("tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date lastLogin;
    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Integer id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * wxid
     */
    private String wxId;

    /**
     * wx名
     */
    private String wxName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否可用，0-可用  1-封禁
     */
    private Integer availableStatus;

    /**
     * 身份，0-普通用户  1-管理员
     */
    private String identity;

    public User() {
    }
}