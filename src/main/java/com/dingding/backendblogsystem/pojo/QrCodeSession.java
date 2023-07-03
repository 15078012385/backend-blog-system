package com.dingding.backendblogsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeSession {
    private String sessionId;
    private String userId;
    private Integer loginStatus;

}
