package com.scce.dao;

import com.scce.pojo.Bill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-02 21:36
 **/
public interface BillDao {
    /*
    *@Select({"<script>",
    "SELECT * FROM tbl_order",
    "WHERE 1=1",
    "<when test='title!=null'>",
    "AND mydate = #{mydate}",
    "</when>",
    "</script>"})
    **/


    //查询订单信息
    @Select("select * from bill")
    public List<Bill> getBill();

    //根据房间类型，查询订单数量
    @Select("select count(1) from bill where roomType=#{roomType}")
    public Integer getBillNumByRoomType(Integer roomtype);

    //根据房间类型和身份证号查询订单
    @Select({
            "<script>",
            "select * from bill",
            "<where>",
            "<if test='roomType!=null'>",
            "roomType=#{roomType}",
            "</if>",
            "<if test='idCard!=null'>",
            "and idCard=#{idCard}",
            "</if>",
            "</where>",
            "</script>"
    })
  public   List<Bill> getBillByRoomAndIdCard(@Param("roomType") Integer roomType,@Param("idCard") String idCard);

   //@Select("select * from bill where idCard=#{idCard} and checkintime=#{checkintime}")
   @Select("select * from bill where roomNumber=#{roomNumber} and idCard=#{idCard} and checkintime=#{checkintime}")
    public Bill getBillIdCardAndCheckintime(@Param("idCard") String idCard, @Param("checkintime") String checkintime,@Param("roomNumber") String roomNumber);

   @Update("update bill set billstatus=0 where idCard=#{idCard} and checkintime=#{checkintime}")
  public   void upBillstatus(@Param("idCard") String idCard, @Param("checkintime") String checkintime);


   //修改退房时间
   @Update("update `bill` set checkouttime=#{checkouttime} where roomNumber=#{roomNumber}")
   public   void upBillcheckoutTime(@Param("checkouttime") String checkouttime,@Param("roomNumber") String roomNumber);
}
