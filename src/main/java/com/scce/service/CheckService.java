package com.scce.service;

import com.github.pagehelper.PageInfo;
import com.scce.pojo.Check;
import com.scce.pojo.Room;

import java.util.List;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-05 17:01
 **/
public interface CheckService {

    public  PageInfo<Check> getCheck(int pageNum,int pageSize);

    PageInfo<Check> seltCheck(Check check, int pageNum, int pageSize);

   public List<Check> getAllCheck();
    public    List checkoutroom(Check check);
    public List<String> getRoomNumberByRoomType(String roomType);
    public Room getDepositByRoomNumber(String roomNumber);

    public void insertCheck(Check check, String roomtype, String roomtypestr);
    //根据房间号删除房间
    public void delCheck(int roomNumber) throws Exception;
    //根据房间号删除多项房间
    public void delAll(String[] rn)throws Exception;

  public   void upCheckcheckoutTime(String checkouttime,String roomNumber);
}
