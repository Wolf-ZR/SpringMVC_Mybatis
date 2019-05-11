package com.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登陆页面拦截器
 */
public class LoginInteceptor implements HandlerInterceptor {
    //执行handler之前执行
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        StringBuffer url=httpServletRequest.getRequestURL();

        if(url.indexOf("login.action")>=0){
            return true;
        }
        //从request中提取session信息
        HttpSession session=httpServletRequest.getSession();
        //从session中获得用户信息
        String username=(String)session.getAttribute("username");
        //判断用户信息
        if(username!=null){
            return true;
        }
        //执行到这里说明没通过认证
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp").
                forward(httpServletRequest,httpServletResponse);
        return false;
    }
    //执行handler，返回ModelAndView之前
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("HandlerInterceptor1...postHandle()");
    }
    //执行handler之后
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("HandlerInterceptor1...afterCompletion()");
    }
}
