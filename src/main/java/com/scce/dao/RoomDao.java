package com.scce.dao;
import com.scce.pojo.Room;
import com.scce.pojo.RoomType;
import org.apache.ibatis.annotations.*;

import java.util.List;
//客房管理
public interface RoomDao {
    //查询所有房间信息
    @Select("select * from room ")
    @Results({
            @Result(id = true,property = "roomNumber",column = "roomNumber"),//id = true表示主键
            @Result(property = "price",column = "price"),
            @Result(property = "roomType",column = "roomType"),
            @Result(property = "deposit",column = "deposit"),
            @Result(property = "status",column = "status"),
            @Result(property = "roomTypestr",column = "roomType",javaType = RoomType.class,one = @One(select = "com.scce.dao.RoomTypeDao.getRoomTpyeById"))
    })
    public List<Room> getRoom();

    //增加房间
    @Insert("insert into room values(#{roomNumber},#{price},#{roomType},#{deposit},#{status})")
    public void insertRoom(Room room);

    //根据房间号修改房间
    @Update("update room set price=#{price},roomType=#{roomType},deposit=#{deposit},status=#{status} where roomNumber=#{roomNumber}")
    public void updateRoom(Room room);

    //根据房间号修改房间状态
    @Update("update room set status=#{status} where roomNumber=#{roomNumber}")
    public void updateStatus(Room room);

    //根据房间号删除房间(删除房间需删除与此房间相关的订单信息和入住信息)
    //@Delete("delete from room where roomNumber=#{roomNumber}")
    @Delete({
            "<script>",
            "delete from `check` where roomNumber=#{roomNumber};",
            "delete from  room where roomNumber=#{roomNumber};",
            "delete from  bill where roomNumber=#{roomNumber};",
            "</script>"
    })
    public void delRoom(int roomNumber);


    //根据房间号查询房间（Lxy）
    @Select("select * from room where roomNumber=#{roomNumber}")
    public  Room getRoomByroomNumber(String roomNumber);

    //查询房间押金
    @Select("select * from room where deposit=#{deposit}")
    public List<Room> roomDeposit();

    //根据房间号，房间类型，房间状态查询房间
  //  @Select("select * from room where roomNumber=#{roomNumber} OR roomType=#{roomType} OR status=#{status}")
    @Select({
            "<script>",
            "select * from room",
            "<where>",
            "<if test='roomNumber!=null'>",
            "roomNumber=#{roomNumber}",
            "</if>",
            "<if test='roomType!=null'>",
            "and roomType=#{roomType}",
            "</if>",
            "<if test='status!=null'>",
            "and status=#{status}",
            "</if>",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(id = true,property = "roomNumber",column = "roomNumber"),//id = true表示主键
            @Result(property = "price",column = "price"),
            @Result(property = "roomType",column = "roomType"),
            @Result(property = "deposit",column = "deposit"),
            @Result(property = "status",column = "status"),
            @Result(property = "roomTypestr",column = "roomType",javaType = RoomType.class,one = @One(select = "com.scce.dao.RoomTypeDao.getRoomTpyeById"))
    })
    public List<Room> roomNTS(@Param("roomNumber") String roomNumber,@Param("roomType") Integer roomType,@Param("status") Integer status);

    //查询每种房间类型数量（Lxy）
    @Select("select count(1) from room where roomType=#{roomType}")
    public Integer getRoomNum(Integer roomType);

    //查询每种房间类型可入住房间数量（Lxy）
    @Select("select count(1) from room  where status=1 and roomType=#{roomType}")
    public Integer getInRoomNum(Integer roomType);

    //查询房间状态为带清理
    @Select("select * from room where status=3")
    public List<Room> getRoomstatus();

    //根据房间号删除多项房间
    @Delete({
            "<script>",
            "delete from room where roomNumber in",
            "<foreach collection='array' item='roomNumber' open='(' separator=',' close=');'>",
            "#{roomNumber}",
            "</foreach>",
            "delete from `check`  where roomNumber in",
            "<foreach collection='array' item='roomNumber' open='(' separator=',' close=');'>",
            "#{roomNumber}",
            "</foreach>",
            "delete from  bill where roomNumber in",
            "<foreach collection='array' item='roomNumber' open='(' separator=',' close=');'>",
            "#{roomNumber}",
            "</foreach>",
            "</script>"

    })
    public void delAll(String[] rn);

    @Select("select * from room where roomType=#{rid}")
  public   List<Room> getRoomByRoomType(int rid);
}

