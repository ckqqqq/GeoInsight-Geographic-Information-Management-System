package com.myproject.manager.config;

import com.myproject.manager.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
Description:
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所有被拦截器拦截的request路径会送到 controller 里面
        registry.addInterceptor(new PermissionInterceptor()) // 添加拦截器
                .addPathPatterns(""); // 选择拦截路径
    }
}
