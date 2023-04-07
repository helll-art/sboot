package com.ppp.sboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ppp.sboot.common.Constants;
import com.ppp.sboot.common.Result;
import com.ppp.sboot.controller.dto.UserDTO;
import com.ppp.sboot.entity.User;
import com.ppp.sboot.exception.ServiceException;
import com.ppp.sboot.mapper.UserMapper;
import com.ppp.sboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppp.sboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 何小磊
 * @since 2023-03-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    //    private static final log LOG = Log.get();
    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(),one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        } else {
            throw new ServiceException(Constants.Code_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        System.out.println("UserServiceImpl====register");
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);

            save(one);//把copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.Code_600, "用户已存在");
        }
        return one;
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
//            e.printStackTrace();
            throw new ServiceException(Constants.Code_500, "系统错误");
        }
        return one;
    }
}
