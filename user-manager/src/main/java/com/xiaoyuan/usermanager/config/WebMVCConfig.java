package com.xiaoyuan.usermanager.config;

import com.xiaoyuan.usermanager.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * FileName:    WebMVCConfig
 * Author:      小袁教程
 * Date:        2022/5/13 12:08
 * Description:
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所有被拦截器拦截的request路径会送到 controller 里面
        registry.addInterceptor(new PermissionInterceptor()) // 添加拦截器
                .addPathPatterns("/item/**"); // 选择拦截路径
    }
}
