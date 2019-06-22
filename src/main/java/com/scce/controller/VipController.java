package com.scce.controller;


import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scce.pojo.Vip;
import com.scce.service.VipService;
import com.scce.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.nimbus.State;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vip")
public class VipController {
    @Autowired
    private VipService vs;
     @RequestMapping("/getVip")
    public JsonResult selectVip(int pageNum, int pageSize){
        System.out.println("进入SelectVip方法");
         System.out.println(pageNum+"<<<<<"+pageSize);
        PageInfo<Vip> pageInfo=vs.getVip(pageNum,pageSize);
                return new JsonResult(0,pageInfo.getList(),pageInfo.getTotal());
    }
    @RequestMapping("/addVip")
    public JsonResult addVip(Vip vip){
        System.out.println("进入addVip()方法");
        System.out.println(vip);
         vs.addVip(vip);
         return new JsonResult(0,"成功");

    }
    @RequestMapping("/getVipbykey")
    public JsonResult getVipByKey(String vipType,String key,int pageNum, int pageSize){
        Vip vip=null;
        List<Vip> list=new ArrayList<Vip>();
        PageInfo<Vip> pageInfo=null;
        System.out.println("进入getVipByKey方法");
        System.out.println("vipType=="+vipType+">>>>>key=="+key);

        try{
        if(vipType.equals("vipId")){
            Integer id=Integer.parseInt(key);
         vip=vs.getVipByVipID(id);
         list.add(vip);
         pageInfo=new PageInfo<Vip>(list);
        }else if (vipType.equals("name")){
            vip=vs.getVipByName(key);
            list.add(vip);
            pageInfo=new PageInfo<Vip>(list);
        }else if (vipType.equals("idCard")){
            vip=vs.getVipByIdCard(key);
            list.add(vip);
            pageInfo=new PageInfo<Vip>(list);
        }else if (vipType.equals("phone")){
            vip=vs.getVipByPhone(key);
            list.add(vip);
            pageInfo=new PageInfo<Vip>(list);
        }}catch (Exception e){
            e.printStackTrace();
        }
         return  new JsonResult(0,pageInfo.getList(),pageInfo.getTotal());
    }


    @RequestMapping("/delVip")
    public JsonResult delVip(String vipId) {
        System.out.println("进入delVip方法");
        System.out.println(vipId);
        Integer vipid = Integer.parseInt(vipId);
        vs.delVip(vipid);
        return new JsonResult(0,"删除成功");
    }
    @RequestMapping("/update")
    public JsonResult update(Vip vip){
        System.out.println("进入updateVip 方法");
        System.out.println(vip);
         vs.update(vip);
         return new JsonResult(0,"修改成功");
    }
    @RequestMapping("/delAll")
    public JsonResult delAll(String[] ids){
        System.out.println("进入delAll方法");
        vs.delAll(ids);
        return  new JsonResult(0,"删除成功");
    }
    @RequestMapping("/slectVip")
    public JsonResult slectVip(String idCard){
         Vip vip=null;
        System.out.println("进入select VIP方法22222");
        System.out.println(idCard);
      vip=vs.selectVip(idCard);
        System.out.println(vip);
      if (vip!=null){
          return  new JsonResult(0,"身份证已存在");
      }else{
          return  new JsonResult(1,"身份证不存在");
      }


    }
}
