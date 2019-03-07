package com.hyh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 描述:
 *
 * @Author shilei
 * @Date 2019/1/17
 */
public class UserAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String uri = httpServletRequest.getRequestURI();
        HttpSession session = httpServletRequest.getSession();

        // 表示用户还未登录
        if(session.getAttribute("student") == null){
            // 未登录访问登录页面，放行
            if(uri.endsWith("/login")){
                return true;
            }

            // 用户还未登录， 只能重定向到登录页面
            httpServletResponse.sendRedirect(httpServletRequest.getServletContext().getContextPath() + "/login");
            return false;
        } else {

            // 如果已经登录了，再访问登录页面，直接跳转到主页面
            if(uri.endsWith("/login")){
                httpServletResponse.sendRedirect(httpServletRequest.getServletContext().getContextPath() + "/main");
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
