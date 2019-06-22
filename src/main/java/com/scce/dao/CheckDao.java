package com.scce.dao;

import com.scce.pojo.Check;
import com.scce.pojo.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-05-30 21:37
 **/
/*@Result(property = "room",column = "roomNumber",javaType = Room.class,one = @One(select = "com.scce.dao.RoomDao.getRoomByroomNumber")),*/
public interface CheckDao {

    @Select("select * from `check`")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roomNumber",column = "roomNumber"),
            @Result(property = "vipid",column = "vipid"),
            @Result(property = "userName",column = "userName"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "idCard",column = "idCard"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "checkInTime",column = "checkInTime"),
            @Result(property = "checkouttime",column = "checkouttime"),
            @Result(property = "status",column = "status"),
            @Result(property = "room",column = "roomNumber",javaType = Room.class,one = @One(select = "com.scce.dao.RoomDao.getRoomByroomNumber")),

    })
    public List<Check> getCheck();

    @Select({
            "<script>",
            "select * from `check`",
            "<where>",
            "<if test='check.userName!=null'>",
            "and userName = #{check.userName}",
            "</if>",
            "<if test='check.roomNumber!=null'>",
            "and roomNumber = #{check.roomNumber}",
            "</if>",
            "<if test='check.phone!=null'>",
            "and phone like CONCAT('%',#{check.phone},'%')",
            "</if>",
            "<if test='check.checkInTime!=null'>",
            "and checkInTime like CONCAT('%',#{check.checkInTime},'%')",
            "</if>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roomNumber",column = "roomNumber"),
            @Result(property = "vipid",column = "vipid"),
            @Result(property = "userName",column = "userName"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "idCard",column = "idCard"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "checkInTime",column = "checkInTime"),
            @Result(property = "checkouttime",column = "checkouttime"),
            @Result(property = "status",column = "status"),
            @Result(property = "room",column = "roomNumber",javaType = Room.class,one = @One(select = "com.scce.dao.RoomDao.getRoomByroomNumber")),
    })
    public  List<Check> seltCheck(@Param("check") Check check);


    //根据房间号删除多项房间
    @Delete({
            "<script>",
            "delete from `check` where roomNumber in",
            "<foreach collection='array' item='roomNumber' open='(' separator=',' close=')'>",
            "#{roomNumber}",
            "</foreach>",
            "</script>"

    })
    public void delAll(String[] rn);


    //根据房间号删除入住信息
    @Delete({
            "<script>",
            "delete from `check` where roomNumber=#{roomNumber};",
            "</script>"
    })
    public void delCheck(int roomNumber);

    @Select("SELECT roomNumber FROM room WHERE roomType=#{type} and status=1")
    public  List<String> getRoomNumberByRoomType(@Param("type") String roomType);

    @Select("select * from room where roomNumber=#{roomNumber}")
    public Room getDepositByRoomNumber(@Param("roomNumber") String roomNumber);

    @Insert({
            "<script>",
            "insert into `check`(roomNumber,vipid,userName,sex,idCard,phone,checkInTime) values(#{check.roomNumber},#{check.vipid},#{check.userName},#{check.sex},#{check.idCard},#{check.phone},#{check.checkInTime});",
            "INSERT INTO `wineshop2`.`bill`( `roomNumber`, `roomType`, `guestName`, `idCard`, `generationTime`, `phone`, `payAmount`, `checkintime`, `checkouttime`, `billstatus`, `roomTypeStr`)",
            " VALUES ( #{check.roomNumber},#{roomtype}, #{check.userName}, #{check.idCard}, #{check.checkInTime}, #{check.phone}, null, #{check.checkInTime},null, 1, #{roomtypestr});",
            "</script>"
    })
    public  void insertCheck(@Param("check") Check check,@Param("roomtype") String roomtype,@Param("roomtypestr") String roomtypestr);

    @Update("update `check` set checkouttime=#{checkouttime} where roomNumber=#{roomNumber}")
  public   void upCheckcheckoutTime(@Param("checkouttime") String checkouttime,@Param("roomNumber") String roomNumber);
}
