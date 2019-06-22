package com.scce.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scce.dao.RoomDao;
import com.scce.dao.RoomTypeDao;
import com.scce.pojo.Room;
import com.scce.pojo.RoomType;
import com.scce.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService{

    @Autowired
    private RoomTypeDao roomTypeDao;

    @Autowired
    private RoomDao roomDao;
    //查询房间类型名
    public PageInfo<RoomType> getRoomType(int pageNum, int pageSize) throws Exception {
        PageInfo<RoomType> pageInfo= null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<RoomType> list=roomTypeDao.getRoomType();
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    //修改客房类型名
    public void updateRT(RoomType roomType) throws Exception {
        roomTypeDao.updateRT(roomType);
    }

    //增加房间类型
    public void insertRT(RoomType roomType) throws Exception {
        roomTypeDao.insertRT(roomType);
    }

    //查询所有房间类型信息
    public List<RoomType> getAllRoomType() {
        List<RoomType> roomTypeList = roomTypeDao.getRoomType();
        return roomTypeList;
    }

    //根据房间类型编号删除房间类型
    public void delRT(int rid) throws Exception {
     List<Room> list=   roomDao.getRoomByRoomType(rid);
        for (Room room : list) {
            roomDao.delRoom(new Integer(room.getRoomNumber()));
        }
        roomTypeDao.delRT(rid);
    }
}
