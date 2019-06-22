package com.scce.Interceptor;

import com.scce.pojo.Emplyee;
import com.scce.service.EmplyeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-06 12:59
 **/

import java.net.URLDecoder;
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private EmplyeeService emplyeeService;

    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在，
     多个Interceptor，然后SpringMVC会根据声明的顺序一个接一个的执行，而且所有的Interceptor中的preHandler方法都会在Controller方法之前调用
     如果返回的是false的话就能够中断这个请求
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("进入了preHandle方法！！！！");
        //先从session拿取用户
        //cookie_account
        String cookie_account = null;
        //定义cookie_password
        String cookie_password = null;

        Cookie[] cookies = request.getCookies();

        if(cookies!=null){
            for(Cookie cookie : cookies){
                //获得名字是cookie_account和cookie_password
                if("cookie_account".equals(cookie.getName())){
                    cookie_account = cookie.getValue();
                    //恢复中文用户名
                    cookie_account = URLDecoder.decode(cookie_account, "UTF-8");
                }
                if("cookie_password".equals(cookie.getName())){
                    cookie_password = cookie.getValue();
                }
            }
        }
        if(cookie_account!=null&&cookie_password!=null){
            //System.out.println("eeeeeeeeeeeeeeeeee");
            //登录的代码

            Emplyee emplyee = null;
            try {
                emplyee = emplyeeService.login(cookie_account,cookie_password);
                System.out.println("拦截器的emplyee"+emplyee);
                if (emplyee!=null){
                    System.out.println("@@@");
                    response.setContentType("text/html;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    //跳到index.html
                    response.getWriter().print("<script>window.parent.location.href='index.html'</script>");
                   // response.sendRedirect("index.html");
                    //request.getRequestDispatcher("index.html").forward(request,response);
                    return false;
                }else {
                    response.getWriter().print("<script>window.parent.location.href='login.html'</script>");
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //将登录的用户的user对象存到session中
            //session.setAttribute("user", user);
        }

        return true;

    }

    /**
     *这个方法只会在当前的这个Interceptor的preHandler方法返回值为true的时候才执行。
     postHandler是进行处理器拦截用的。它的执行时间实在处理器进行处理之后，也就是在Controller
     的方法调用之后执行，但是他会在DispatcherServler进行视图渲染之前执行，也就是说在这个方法中，
     你可以对返回的ModelAndView进行操作。
     *这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，
     这里与struts2里面的拦截器的执行过程有点像。
     *只是struts2里面的intercept方法中要手动调用的ActionInvocation的invoke方法，Struts2中调用
     ActionInvocation的invoke方法就是调用下一个Inteceptor或者是调用actio,然后在Interceptor之前
     调用的内容都写在invoke之前，要在Interceptor之后调用的都卸载invoke方法之后

     */
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("进入了postHandle方法！！！！");
    }

    /**
     *这个方法需要preHandler方法的返回值是true的时候才会执行。该方法将整个请求完成之后
     * 也就是DispatcherServlet渲染了视图才执行
     * 这个方法的主要用途是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandler方法的返回值
     * 是true的时候才会执行
     */
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("进入了afterCompletion方法！！！！");
    }
}