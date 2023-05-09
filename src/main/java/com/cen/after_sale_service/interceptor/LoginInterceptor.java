package com.cen.after_sale_service.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义一个拦截器
 * */
public class LoginInterceptor implements HandlerInterceptor {
/**
 * 检查全局session对象中是否有uid数据，如果有则放行，如果没有没有重定向到登录系统
 * @param request 请求对象
 * @param response  响应对象
 * @param handler  处理器（url+Controller：映射）
 * @return boolean（true 放行，flse拦截）
 * @throws Exception
 * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//     HttpServlertRequest对象来获取session对象
            Object object=request.getSession().getAttribute("uid");
            if (object==null){
//                用户没有登录，重定向到login.html
                response.sendRedirect("/web/login.html" );
//                    结束后续的调用
                return false;
            }
        // 请求方行
            return true;
    }
}
