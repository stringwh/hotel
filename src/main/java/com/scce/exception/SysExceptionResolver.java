package com.scce.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: IdeaProjects
 * @description  异常处理器
 * @author: Lxy
 * @create: 2019-05-07 10:15
 **/
public class SysExceptionResolver implements HandlerExceptionResolver {


    /*
    * 处理异常业务逻辑
    * */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
       //获取到异常对象
        System.out.println("进入异常处理类");
        SysException e=null;
        if (ex instanceof  SysException){
            e=(SysException)ex;
        }else {
            e=new SysException("系统正在维护....");
        }
        //创建ModelAndView对象
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("errorMsg",e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
