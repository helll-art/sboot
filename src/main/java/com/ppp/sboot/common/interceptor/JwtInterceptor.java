package com.ppp.sboot.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ppp.sboot.common.Constants;
import com.ppp.sboot.entity.User;
import com.ppp.sboot.exception.ServiceException;
import com.ppp.sboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private IUserService userService;
//拦截器
    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response , Object handler) throws Exception{
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //执行认证
        if(StrUtil.isBlank(token)){
            throw new ServiceException(Constants.Code_401,"无token,请重新登录");
        }
        //获取token中的userid
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);
        }catch(JWTDecodeException j){
            throw new ServiceException(Constants.Code_401,"token验证失败");
        }
        //根据token中的userid查询数据库，
        User user = userService.getById(userId);
        if(user == null){
            throw new ServiceException(Constants.Code_401,"用户不存在，请重新登录");
        }
        //用户密码加签验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try{
            jwtVerifier.verify(token); // 验证token
        }catch (JWTVerificationException e ){
            throw new ServiceException(Constants.Code_401,"token验证失败");
        }
        return true;
    }
}
