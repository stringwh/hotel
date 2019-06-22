package com.scce.dao;

import com.scce.pojo.Vip;
import com.scce.pojo.Vipdiscount;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VipDao {
  @Select("select * from vip")
  public  List<Vip> getVip();
   @Insert("insert into vip(name,sex,idCard,phone) values(#{name},#{sex},#{idCard},#{phone})")
   public  void addVip(Vip vip);



    @Select("select * from vip where vipId=#{id}")
  public Vip getVipById(@Param("id") Integer id);

    @Select("select * from vip where name=#{name}")
   public  Vip getVipByName(String key);

    @Select("select * from vip where idCard=#{idCard}")
   public Vip getVipByIdCard(@Param("idCard") String key);


    @Select("select * from vip where phone=#{phone}")
   public  Vip getVipByPhone(@Param("phone") String key);


    @Delete("delete from vip where vipId=#{vipId}")
   public void delVip(@Param("vipId") Integer vipid);

    @Update("update vip set name=#{vip.name},sex=#{vip.sex},idCard=#{vip.idCard},phone=#{vip.phone} where vipId=#{vip.vipId}")
  public void updateVip(@Param("vip") Vip vip);

    @Update("update vip set balance=#{vip.balance},addMonetary=#{vip.addMonetary},level=#{vip.level} where vipId=#{vip.vipId}")
    public void upVipbalanceAndaddMonetary(@Param("vip") Vip vip);

    @Delete({"<script>",
            "delete from vip where vipId in",
            "<foreach collection='array' item='vipId' open='(' separator=',' close=')'>",
            "#{vipId}",
            "</foreach>",
            "</script>"
    })
    public  void delAll(String[] ids);

    @Select("select * from vip where idCard=#{idCard}")
    public Vip selectVip(@Param("idCard") String idCard);

    @Select("select * from vip where name=#{name}")
    @Results({
            @Result(id = true,property = "vipId",column = "vipId"),
            @Result(property = "name",column = "name"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "idCard",column = "idCard"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "level",column = "level"),
            @Result(property = "balance",column = "balance"),
            @Result(property = "addMonetary",column = "addMonetary"),
            @Result(property = "vipdiscount",column = "level",javaType = Vipdiscount.class,one = @One(select = "com.scce.dao.VipdiscountDao.getVipdiscountBylevel")),
    })
   public Vip getVipAndVipdiscounte(String name);

}
