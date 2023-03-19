package com.ppp.sboot.service.impl;

import com.ppp.sboot.entity.User;
import com.ppp.sboot.mapper.UserMapper;
import com.ppp.sboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
