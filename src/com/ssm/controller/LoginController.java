package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登陆控制器
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(HttpSession session, String username,
                             String password)throws Exception{
        //在Session保存用户信息
        session.setAttribute("username",username);
        //重定向到商品列表页面
        return "redirect:/items/queryItems.action";

    }
    @RequestMapping("/logout")
    public String logout(HttpSession session)throws Exception{
        //清除session
        session.invalidate();
        return "redirect:/items/queryItems.action";
    }
}
