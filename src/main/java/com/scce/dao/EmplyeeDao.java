package com.scce.dao;

import com.scce.pojo.Emplyee;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-05-30 21:38
 **/
public interface EmplyeeDao {

    //查询所有员工信息
    @Select("select * from emplyee")
    public List<Emplyee> getEmplyee();

    //根据id删除
    @Delete("delete from emplyee where id=#{id}")
    public void delEmplyee(int id) throws Exception;

    //根据账号密码查询员工信息
    @Select("select * from emplyee  where account=#{account} and password=#{password}")
    public Emplyee login(@Param("account") String account, @Param("password") String password);

    @Insert("insert into emplyee(account,realName,password,sex,birthDate,idCard,phone,address,type) values(#{account},#{realName},#{password},#{sex},#{birthDate},#{idCard},#{phone},#{address},#{type})")
    public void inserEmplyee(Emplyee emplyee);

    @Select("select * from emplyee where account=#{account} and phone=#{phone}")
    public Emplyee check(@Param("account") String account, @Param("phone") String phone);

    @Update("update emplyee set password=#{passwprd} where account=#{account} and phone=#{phone}")//修改
    public void upEmplyeePwd(@Param("account") String account, @Param("phone") String phone, @Param("passwprd") String passwprd);

    @Update("update emplyee set account=#{account},realName=#{realName},password=#{password},sex=#{sex},phone=#{phone},address=#{address},type=#{type} where id=#{id}")
    public void upEmplyee(Emplyee emplyee);

    @Select({
            "<script>",
            "select * from emplyee",
            "<where>",
            "<if test='account!=null'>",
            "account=#{account}",
            "</if>",
            "<if test='realName!=null'>",
            "and realName=#{realName}",
            "</if>",
            "<if test='sex!=null'>",
            "and sex=#{sex}",
            "</if>",
            "<if test='phone!=null'>",
            "and phone=#{phone}",
            "</if>",
            "<if test='address!=null'>",
            "and address=#{address}",
            "</if>",
            "</where>",
            "</script>"
    })
    public List<Emplyee> getemplyeeBysome(@Param("account") String account, @Param("realName") String realName, @Param("sex") String sex, @Param("phone") String phone, @Param("address") String address);

    @Delete({
            "<script>",
            "DELETE FROM emplyee WHERE id in",
            "<foreach collection='list'  item='item' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    public void delM(@Param("list") List<Integer> list);
    @Select("select * from emplyee where idCard=#{idcard}")
    public Emplyee checkidcard(@Param("idcard")String idcard);
    @Select("select * from emplyee where account=#{account}")
    public Emplyee checkaccount(@Param("account")String account);
}
