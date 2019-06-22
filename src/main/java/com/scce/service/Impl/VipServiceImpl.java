package com.scce.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scce.dao.VipDao;
import com.scce.pojo.Vip;
import com.scce.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class VipServiceImpl implements VipService {
    @Autowired
    private VipDao vipDao;
    Vip vip=null;

    @Override
   public PageInfo<Vip> getVip(int pageNum, int pageSize){
       PageInfo<Vip> pageInfo=null;
       try { PageHelper.startPage(pageNum,pageSize);
           List<Vip>  vipList=vipDao.getVip();
           pageInfo =new PageInfo<Vip>(vipList);
       }catch (Exception e){
           e.printStackTrace();
       }

         return pageInfo;
    }

    @Override
    public void addVip(Vip vip) {
        try {
            vipDao.addVip(vip);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Vip getVipByVipID(int id) {
        System.out.println("进入getVipByVipID方法");
        try {
         vip=vipDao.getVipById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return vip;
    }

    @Override
    public Vip getVipByName(String key) {
        try {
            vip=vipDao.getVipByName(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return vip;
    }

    @Override
    public Vip getVipByIdCard(String key) {
        try {
            vip=vipDao.getVipByIdCard(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return vip;
    }

    @Override
    public Vip getVipByPhone(String  key) {
        try {
            vip=vipDao.getVipByPhone(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return vip;
    }

    @Override
    public void delVip(Integer vipid) {

        try {
           vipDao.delVip(vipid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Vip vip) {
        System.out.println("进入Vip   update");
        System.out.println(vip);
        try {
            vipDao.updateVip(vip);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delAll(String[] ids) {

        try {
            vipDao.delAll(ids);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Vip selectVip(String idCard) {
       Vip vip=null;
        try {
         vip=vipDao.selectVip(idCard);
        }catch (Exception e){
            e.printStackTrace();
        }
        return vip;
    }

    @Override
    public void upVipbalanceAndaddMonetary(Vip vip) {
        System.out.println("进入upVipbalanceAndaddMonetary："+vip);
        vipDao.upVipbalanceAndaddMonetary(vip);
    }


}
