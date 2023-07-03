package com.dingding.backendblogsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {

    //    用户唯一ID
    private String userId;
    //该次会话的凭证
    private String tempUserId;
    //    登录用户的昵称（用户授权后,可获得,可能为空）
    private String nickname;
    //    登录用户的头像url（用户授权后,可获得,可能为空）
    private String avatar;

    //    登录地点的ip
    private String ipAddr;
}
