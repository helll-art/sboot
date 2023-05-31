package com.ppp.sboot.controller.dto;
import com.ppp.sboot.entity.Menu;
import com.ppp.sboot.entity.Tips;
import lombok.Data;

import java.util.List;

@Data
//接收前端登录请求的参数
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String nickname ;
    private String avatarUrl;
    private String token;
    //角色权限
    private String userstatus;

    private List<Menu> menus;
    private List<Integer> ids;

    private List<String> tips;
}
