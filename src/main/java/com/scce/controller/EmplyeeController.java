package com.scce.controller;
import com.github.pagehelper.PageInfo;
import com.scce.pojo.Emplyee;
import com.scce.service.EmplyeeService;
import com.scce.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.EncodingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-01 18:29
 **/
/*
 *  接口文档访问地址：
 *       http://localhost:8080/项目名/swagger-ui.html
 *  swagger2 注解说明文档：
 *       https://blog.csdn.net/weixin_41846320/article/details/82970204
 *
 *   @Api用于类上，说明该类的作用。可以标记一个Controller类做为swagger 文档资源
 *   示例：@Api(value = "xxx", description = "xxx")
 *         description	对api资源的描述
 *   @ApiOperation用于方法上，说明方法的作用，每一个url资源的定义
 *    示例：@ApiOperation(value = "xxx",httpMethod="POST", notes= "xxx",response=String.class)
 */
@RestController
@Api(description = "测试")
@RequestMapping("/emplyee")
public class EmplyeeController {
    @Autowired
    private EmplyeeService emplyeeService;

    //登陆方法，并可以实现自动登陆
    @RequestMapping(value = "/login")
    @ApiOperation(value = "login接口", notes = "login接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="password",value="密码",required=true),
            @ApiImplicitParam(name="account",value="账号",required=true),
            @ApiImplicitParam(name="auto",value="是否记住密码",required=true),
            @ApiImplicitParam(name="request",value="HttpServletRequest对象",required=false),
            @ApiImplicitParam(name="response",value="HttpServletResponse对象",required=false),
            @ApiImplicitParam(name="session",value="HttpSession对象",required=false)
    })
    public JsonResult login(String account, String password, Boolean auto, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        System.out.println("进入login方法");
        System.out.println(account+"==="+password+"==="+auto);
        Emplyee  emplyee = emplyeeService.login(account,password);
        System.out.println(emplyee);
        System.out.println("======="+emplyee.getAccount());
        if (emplyee != null) { //登陆成功，将用户名和用户类型存入cookie
            //对用户名进行编码（cookie不能存储中文）
            String account_code = URLEncoder.encode(account, "UTF-8");
            //创建cookie,把用户名存起来
            Cookie cookie_account = new Cookie("cookie_account",account_code);
            //把用户类型保存起来
            Cookie cookie_type=new Cookie("cookie_type",Integer.toString(emplyee.getType()));
            //设置cookie的持久化时间
            cookie_account.setMaxAge(60*60);//单位秒
            cookie_type.setMaxAge(60*60);
            //设置cookie的携带路径，设置为当前项目下都携带这个cookie
            cookie_account.setPath(request.getContextPath());
            cookie_type.setPath(request.getContextPath());
            //发送cookie,向客户端发送cookie
            response.addCookie(cookie_account);
            response.addCookie(cookie_type);
            //如果勾选了自动登陆,就把密码也存起来
            if (auto){
                //创建cookie,把用户名密码存起来
                Cookie cookie_password = new Cookie("cookie_password",password);
                //设置cookie的持久化时间
                cookie_password.setMaxAge(60*60);
                //设置cookie的携带路径，设置为当前项目下都携带这个cookie
                cookie_password.setPath(request.getContextPath());
                //发送cookie,向客户端发送cookie
                response.addCookie(cookie_password);
            }
            //将登录的用户的user对象存到session中，使每个页面都能获得账号的相关信息。
            // session.setAttribute("emplyee", emplyee);
            return new JsonResult(0, "登陆成功");
        } else {
            return new JsonResult(1,  "登陆失败，账号密码错误");
        }
    }
    //注销
    @RequestMapping("/logout")
    @ResponseBody
    public JsonResult logout(HttpServletRequest request,HttpServletResponse response){
        //删除cookie
        System.out.println("进入logout");
        Cookie usernameCookie = new Cookie("cookie_password","");
        usernameCookie.setMaxAge(0);
        usernameCookie.setPath(request.getContextPath());
        response.addCookie(usernameCookie);
        return new JsonResult(0, "注销成功");
    }
    //查询所有员工并分页
    @RequestMapping(value = "/getEmplyee")
    @ApiOperation(value = "hello接口", notes = "hello接口", httpMethod = "POST")
    public JsonResult getEmplyee(int pageNum,int pageSize) throws Exception {
        System.out.println("执行查询所有员工的方法");
        PageInfo<Emplyee> pageInfo = emplyeeService.getEmplyee(pageNum, pageSize);
        System.out.println(pageInfo.getList());
        return new JsonResult(0,pageInfo.getList(),pageInfo.getTotal());
    }

    //根据id删除员工
    @RequestMapping(value = "/del")
    @ApiOperation(value = "delEmplyee", notes = "delEmplyee", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="id",required=true),})
    public JsonResult delEmplyee(int ids) throws Exception {
        System.out.println(ids);
        emplyeeService.delEmplyee(ids);
        return new JsonResult(0,"删除成功");
    }
    @ResponseBody
    @RequestMapping(value = "/add")
    @ApiOperation(value = "addEmplyee接口", notes = "addEmplyee接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="account",value="账户",required=true),
            @ApiImplicitParam(name="realName",value="真实姓名",required=true),
            @ApiImplicitParam(name="password",value="密码",required=true),
            @ApiImplicitParam(name="sex",value="性别",required=true),
            @ApiImplicitParam(name="birthDate",value="生日",required=true),
            @ApiImplicitParam(name="idCard",value="身份证",required=true),
            @ApiImplicitParam(name="phone",value="手机号",required=true),
            @ApiImplicitParam(name="address",value="地址",required=true),
            @ApiImplicitParam(name="type",value="类型",required=true),
    })
    public JsonResult add(Emplyee emplyee){
        System.out.println(emplyee);
        System.out.println("执行增加员工方法");
        emplyeeService.addEmplyee(emplyee);
        return new JsonResult(0,"添加成功");
    }

    //根据账号和电话号码判断用户是否存在
    @ResponseBody
    @RequestMapping("/check")
    public JsonResult check(String account,String phone){
        System.out.println(account+"=="+phone);
        Emplyee emplyee=emplyeeService.check(account,phone);
        System.out.println(emplyee);
        if (emplyee != null) {
            return new JsonResult(0, "验证成功");
        } else {
            return new JsonResult(1,  "验证失败，账号和手机号不匹配");
        }
    }

    //根据账号和手机号修改密码
    @ResponseBody
    @RequestMapping(value = "/upEmplyeePwd")
    @ApiOperation(value = "login接口", notes = "login接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="password",value="密码",required=true),
            @ApiImplicitParam(name="account",value="账号",required=true),
    })
    public JsonResult upEmplyeePwd(String account,String phone,String password){
        emplyeeService.upEmplyeePwd(account,phone,password);
        return new JsonResult(0,  "修改成功");
    }
    @ResponseBody
    @RequestMapping(value = "/up")
    @ApiOperation(value = "upEmplyee接口", notes = "upEmplyee接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="自编号",required=true),
            @ApiImplicitParam(name="account",value="账号",required=true),
            @ApiImplicitParam(name="realName",value="真实姓名",required=true),
            @ApiImplicitParam(name="password",value="密码",required=true),
            @ApiImplicitParam(name="sex",value="性别",required=true),
            @ApiImplicitParam(name="phone",value="手机号",required=true),
            @ApiImplicitParam(name="address",value="地址",required=true),
            @ApiImplicitParam(name="type",value="类型",required=true),
    })
    public JsonResult up(Emplyee emplyee){
        System.out.println(emplyee);
        emplyeeService.upEmplyee(emplyee);
        return new JsonResult(0,"修改成功");

    }
    @ResponseBody
    @RequestMapping("/getemplyeeBysome")
    @ApiOperation(value = "getemplyeeBysome接口", notes = "getemplyeeBysome接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="account",value="账号",required=false),
            @ApiImplicitParam(name="realName",value="姓名",required=false),
            @ApiImplicitParam(name="sex",value="性别",required=false),
            @ApiImplicitParam(name="phone",value="手机",required=false),
            @ApiImplicitParam(name="address",value="地址",required=false),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
    })
    public JsonResult getemplyeeBysome(@RequestParam(value = "account",required = false)String account,
                                       @RequestParam(value = "realName",required = false)String realName,
                                       @RequestParam(value = "sex",required = false)String sex,
                                       @RequestParam(value = "phone",required = false)String phone,
                                       @RequestParam(value = "address",required = false)String address,
                                       int pageNum,
                                      int pageSize){
        System.out.println("进入多条件查询");
        System.out.println(account+realName+sex+phone+address);
        PageInfo<Emplyee> pageInfo = emplyeeService.getemplyeeBysome(account,realName,sex,phone,address,pageNum, pageSize);

        return new JsonResult(0, pageInfo.getList(), pageInfo.getTotal());
    }
    @ResponseBody
    @RequestMapping("/delM")
    @ApiOperation(value = "delM接口", notes = "delM接口", httpMethod = "POST")
    public JsonResult delM(String[] ids){
        System.out.println("进入delM");
       emplyeeService.delM(ids);
        return new JsonResult(0,"删除成功");
    }
    @ResponseBody
    @RequestMapping("/checkidcard")
    @ApiOperation(value = "checkidcard接口", notes = "checkidcard接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="idcard",value="身份证号",required=false),})
    public JsonResult checkidcard(@RequestParam(value = "idcard")String idcard) {
        System.out.println(idcard);
       String year = idcard.substring(6, 10);// 截取年
        String month = idcard.substring(10, 12);// 截取月份
        String day = idcard.substring(12, 14);// 截取天
        String birthday=year+"-"+month+"-"+day;
        System.out.println(birthday);
        Emplyee emplyee = emplyeeService.checkidcard(idcard);
        if (emplyee!= null) {
            return new JsonResult(1, "用户存在");
        } else {
            return new JsonResult(0, "用户不存在",birthday);
        }
    }
    @ResponseBody
    @RequestMapping("/checkaccount")
    @ApiOperation(value = "checkaccount接口", notes = "checkaccount接口", httpMethod = "POST")
    public JsonResult checkaccount(@RequestParam(value = "account")String account){
        System.out.println(account);
        Emplyee emplyee = emplyeeService.checkaccount(account);
        if (emplyee!= null) {
            return new JsonResult(1, "用户存在");
        } else {
            return new JsonResult(0, "用户不存在");
        }
    }

}
