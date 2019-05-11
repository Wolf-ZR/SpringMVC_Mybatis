package com.ssm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        ModelAndView modelAndView=new ModelAndView();
        CustomException customException=null;
        if(e instanceof CustomException){
            customException=new CustomException(e.getMessage());
        }else{
            customException=new CustomException("未知错误");
        }
        modelAndView.addObject("message",customException.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
