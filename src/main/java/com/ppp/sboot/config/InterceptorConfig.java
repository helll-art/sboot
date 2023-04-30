package com.ppp.sboot.config;

import com.ppp.sboot.common.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//所有的配置类都需要加configuration
public class InterceptorConfig  implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/Login","user/Register","/**/export","/**/import","/file/upload","/menu/**"); //拦截所有请求，通过判断token是否合法来决定是否需要登录
//    }

//    @Bean
//    public JwtInterceptor jwtInterceptor(){
//        return new JwtInterceptor();
//    }
}
