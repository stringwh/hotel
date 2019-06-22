package com.scce.service;

import com.github.pagehelper.PageInfo;
import com.scce.pojo.RoomType;

import java.util.List;

//客房管理
public interface RoomTypeService {
    //查询房间类型名
    public PageInfo<RoomType> getRoomType(int pageNum, int pageSize) throws Exception;
    //修改客房类型名
    public void updateRT(RoomType roomType) throws Exception;
    //增加房间类型
    public void insertRT(RoomType roomType) throws Exception;
    //查询所有房间类型信息
    public List<RoomType> getAllRoomType()throws Exception;
    //根据房间类型编号删除房间类型
    public void delRT(int rid)throws Exception;
}
