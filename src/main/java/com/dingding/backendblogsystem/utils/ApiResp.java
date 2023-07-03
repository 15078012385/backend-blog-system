package com.dingding.backendblogsystem.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResp<T> {
    private Integer code;
    private String msg;
    private T data;
    public static <T> ApiResp<T> success(String msg, T data) {
        return new ApiResp<>(200, msg, data);
    }

}

