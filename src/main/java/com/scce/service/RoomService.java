package com.scce.service;

import com.github.pagehelper.PageInfo;
import com.scce.pojo.Room;

import java.util.List;

//客房管理
public interface RoomService {
    //查询房间信息并分页
    public PageInfo<Room> getRoom(int pageNum, int pageSize) throws Exception;
    //增加房间
    public void insertRoom(Room room) throws Exception;
    //根据房间号修改房间
    public void updateRoom(Room room) throws Exception;
    //根据房间号修改房间状态
    public void updateStatus(Room room) throws Exception;
    //根据房间号删除房间
    public void delRoom(int roomNumber) throws Exception;
    //查询房间状态
    //public List<Room> roomDeposit()throws Exception;
    public PageInfo<Room> roomDeposit(int pageNum, int pageSize)throws Exception;
    //根据房间号，房间类型，房间状态查询
    public PageInfo<Room> roomNTS(String roomNumber,Integer roomType,Integer status,int pageNum, int pageSize)throws Exception;
    //查询每种房间类型数量
    public Integer getRoomNum(Integer roomType);
    //查询每种房间类型可入住房间数量
    public Integer getInRoomNum(Integer roomType);
    //查询房间状态为带清理
    public PageInfo<Room> getRoomstatus(int pageNum, int pageSize)throws Exception;
    //根据房间号删除多项房间
    public void delAll(String[] rn)throws Exception;
}
