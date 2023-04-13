package com.ppp.sboot.controller.dto;

import lombok.Data;
@Data
//接收前端登录请求的参数
public class UserDTO {
    private String username;
    private String password;
    private String nickname ;
    private String avatarUrl;
    private String token;
    private String userstatus;
}
