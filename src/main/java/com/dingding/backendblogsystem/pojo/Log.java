package com.dingding.backendblogsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dingding
 * @description log
 * @date 2023-04-21
 */
@Data
@TableName("tb_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Integer id;

    /**
     * 访问时间
     */
    private Date accessTime;

    /**
     * 接口地址
     */
    private String accessApi;

    /**
     * 入参
     */
    private String accessParams;

    /**
     * 出参
     */
    private String respData;

    /**
     * 访问ip
     */
    private String accessIp;

    /**
     * 耗时ms
     */
    private Long timeConsume;

    public Log() {
    }
}