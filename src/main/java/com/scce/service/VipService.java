package com.scce.service;

import com.github.pagehelper.PageInfo;
import com.scce.pojo.Vip;

public interface VipService {
  //查询VIP会员信息
  public PageInfo<Vip> getVip(int pageNum, int pageSize);

 public void addVip(Vip vip);

  public   Vip getVipByVipID(int id);

    public  Vip getVipByName(String key);

   public Vip getVipByIdCard(String key);

   public Vip getVipByPhone(String key);

   public void delVip(Integer vipid);

   public  void update(Vip vip);

   public  void delAll(String[] ids);

   public Vip selectVip(String idCard);

public     void upVipbalanceAndaddMonetary(Vip vip);
}
