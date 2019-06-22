package com.scce.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scce.dao.EmplyeeDao;
import com.scce.pojo.Emplyee;
import com.scce.service.EmplyeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-01 10:20
 **/
@Service
@Transactional
public class EmplyeeServiceImpl implements EmplyeeService {
    @Autowired
    private EmplyeeDao emplyeeDao;

    //查询员工信息并分页
    @Override
    public PageInfo<Emplyee> getEmplyee(int pageNum, int pageSize) throws Exception{
        PageHelper.startPage(pageNum, pageSize);
        //紧跟在PageHelper.startPage(pageNum, pageSize);的查询语句会执行分页，mysql中就会为它拼上limit
        List<Emplyee> list=emplyeeDao.getEmplyee();
        PageInfo<Emplyee> pageInfo = new PageInfo<Emplyee>(list);
        return pageInfo;

    }

    @Override
    public void delEmplyee(int id) throws Exception {
        emplyeeDao.delEmplyee(id);
    }

    @Override
    public Emplyee login(String account,String password) {
        System.out.println(account+"="+password);
       // Emplyee emplyee=new Emplyee();
        Emplyee emplyee=null;
        try{
            System.out.println(account+password);
            emplyee = emplyeeDao.login(account, password);
            System.out.println(emplyee);
        }catch (Exception e){
            e.printStackTrace();
        }
        return emplyee;
    }

    @Override
    public void addEmplyee(Emplyee emplyee) {
        try{
            System.out.println(emplyee);
        emplyeeDao.inserEmplyee(emplyee);}
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Emplyee check(String account, String phone) {
        Emplyee emplyee=null;
        try {
            emplyee=  emplyeeDao.check(account,phone);
        }catch (Exception e){
            e.printStackTrace();
        }

        return emplyee;
    }

    @Override
    public void upEmplyeePwd(String account, String phone, String password) {
        try {
            emplyeeDao.upEmplyeePwd(account, phone, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void upEmplyee(Emplyee emplyee) {
        try {
            emplyeeDao.upEmplyee(emplyee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageInfo<Emplyee> getemplyeeBysome(String account, String realName, String sex, String phone, String address, int pageNum, int pageSize) {
        PageInfo<Emplyee> pageInfo= null;
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Emplyee> list= emplyeeDao.getemplyeeBysome(account,realName,sex,phone,address);
            pageInfo = new PageInfo<Emplyee>(list);
            System.out.println("Service方法中："+pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageInfo;
    }

    @Override
    public void delM(String[] ids) {

        List<Integer> list = new ArrayList<Integer>();

        for(int i=0;i<ids.length;i++){
            if(ids[i]==null||"".equals(ids[i].trim().toString())){
                continue;
            }else{
                list.add(Integer.valueOf(ids[i]));
            }

        }
          System.out.println(list);
        emplyeeDao.delM(list);
    }

    @Override
    public Emplyee checkidcard(String idcard) {
        Emplyee emplyee=null;
        try {
            emplyee=  emplyeeDao.checkidcard(idcard);
        }catch (Exception e){
            e.printStackTrace();
        }

        return emplyee;
    }

    @Override
    public Emplyee checkaccount(String account) {
        Emplyee emplyee=null;
        try {
            emplyee=  emplyeeDao.checkaccount(account);
        }catch (Exception e){
            e.printStackTrace();
        }

        return emplyee;
    }


}
