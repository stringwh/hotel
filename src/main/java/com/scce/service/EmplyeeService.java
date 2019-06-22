package com.scce.service;

import com.github.pagehelper.PageInfo;
import com.scce.pojo.Emplyee;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-01 10:18
 **/
public interface EmplyeeService  {
    //查询员工信息并分页
   public PageInfo<Emplyee> getEmplyee(int pageNum, int pageSize) throws Exception;

   //根据id删除员工
    public void delEmplyee(int id) throws Exception;

    public  Emplyee login(String account,String password);
    public void addEmplyee(Emplyee emplyee);

    public Emplyee check(String account, String phone);

    public   void upEmplyeePwd(String account, String phone, String password);
    public void upEmplyee(Emplyee emplyee);
    public PageInfo<Emplyee>getemplyeeBysome(String account,String realName,String sex,String phone,String address,int pageNum,int pageSize);
   public void delM(String[] ids);
   public Emplyee checkidcard(String idcard);
   public  Emplyee checkaccount(String account);
}
