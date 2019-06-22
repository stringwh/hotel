package com.scce.dao;

import com.scce.pojo.RoomType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
//客房管理
public interface RoomTypeDao {
    //查询房间类型名
    @Select("select * from roomtype")
    public List<RoomType> getRoomType();
    //修改客房类型名
    @Update("update roomtype set type=#{type} where rid=#{rid}")
    public void updateRT(RoomType roomType);
    //增加房间类型
    @Insert("insert into roomtype(rid,type) values(#{rid},#{type})")
    public void insertRT(RoomType roomType);

    //根据rid查询房间状态
    @Select("select * from roomtype where rid=#{rid}")
    public RoomType getRoomTpyeById(int rid);

    //根据房间类型编号删除房间类型
    @Delete("delete from roomtype where rid=#{rid}")
    public void delRT(int rid);
}
