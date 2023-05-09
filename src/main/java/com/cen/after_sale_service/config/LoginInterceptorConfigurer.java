package com.cen.after_sale_service.config;

import com.cen.after_sale_service.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理拦截器注册
 */
@Configuration//让拦截器自动加载
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //    创建拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置白名单：存放在List集合
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/index.html");
        patterns.add("/web/login.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");

        //添加拦截器（注册拦截器）;
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**").excludePathPatterns(patterns);     //拦截名单，除了什么之外
    }
}
