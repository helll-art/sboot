package com.ppp.sboot.service;

import com.ppp.sboot.controller.dto.UserDTO;
import com.ppp.sboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 何小磊
 * @since 2023-03-18
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
