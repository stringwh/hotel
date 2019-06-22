package com.scce.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scce.dao.RoomDao;
import com.scce.pojo.Room;
import com.scce.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;
    //查询房间信息并分页
    public PageInfo<Room> getRoom(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Room> list=roomDao.getRoom();
        PageInfo<Room> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
    //增加房间
    public void insertRoom(Room room) throws Exception {
         roomDao.insertRoom(room);
    }
    //根据房间号修改房间
    public void updateRoom(Room room) throws Exception {
         roomDao.updateRoom(room);
    }

    //根据房间号修改房间状态
    public void updateStatus(Room room) throws Exception {
         roomDao.updateStatus(room);
    }
    //根据房间号删除房间
    public void delRoom(int roomNumber) throws Exception {
        try{
            roomDao.delRoom(roomNumber);
        }catch (Exception e){

            e.printStackTrace();
        }

    }

    //查询房间状态
    public PageInfo<Room> roomDeposit(int pageNum, int pageSize) throws Exception {
        System.out.println("执行查询房间状态的方法");
        PageHelper.startPage(pageNum, pageSize);
        List<Room> list=roomDao.getRoom();
        PageInfo<Room> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    //根据房间号，房间类型，房间状态查询
    public PageInfo<Room> roomNTS(String roomNumber, Integer roomType, Integer status, int pageNum, int pageSize) throws Exception {
        PageInfo<Room> pageInfo= null;
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Room> list=roomDao.roomNTS(roomNumber,roomType,status);
            pageInfo=new PageInfo<Room>(list);
            for(Room room : pageInfo.getList()){
                System.out.println(room.getRoomNumber());
                Room room1 = roomDao.getRoomByroomNumber(room.getRoomNumber());
                System.out.println(room1);
                room1.setRoom(room1);
            }
            System.out.println("Service方法中："+pageInfo.getList());
        }catch(Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
    /*public List<Room> roomNTS() throws Exception {
        List<Room> list=roomDao.roomNTS();
        return list;
    }*/

    /*public List<Room> roomDeposit()throws Exception {
        System.out.println("执行查询房间状态的方法");
        List<Room> list=roomDao.roomDeposit();
        return list;
    }*/


    @Override
    public Integer getRoomNum(Integer roomType) {
        Integer roomNum = roomDao.getRoomNum(roomType);
        System.out.println(roomNum);
        return roomNum;
    }

    @Override
    public Integer getInRoomNum(Integer roomType) {
        Integer inRoomNum = roomDao.getInRoomNum(roomType);
        System.out.println(inRoomNum);
        return inRoomNum;
    }

    //查询房间状态为带清理
    public PageInfo<Room> getRoomstatus(int pageNum, int pageSize) throws Exception {
        System.out.println("执行查询房间状态为带清理的方法");
        PageHelper.startPage(pageNum, pageSize);
        List<Room> list=roomDao.getRoomstatus();
        PageInfo<Room> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    //根据房间号删除多项房间
    public void delAll(String[] rn)throws Exception {
        try{
            roomDao.delAll(rn);
        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
