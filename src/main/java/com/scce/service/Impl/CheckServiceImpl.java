package com.scce.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scce.dao.BillDao;
import com.scce.dao.CheckDao;
import com.scce.dao.RoomDao;
import com.scce.pojo.Check;
import com.scce.pojo.Room;
import com.scce.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-05 17:01
 **/
@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckDao checkDao;
    @Autowired
    private BillDao billDao ;
    @Autowired
    private RoomDao roomDao;

    @Override
    public PageInfo<Check> getCheck(int pageNum,int pageSize) {
        System.out.println(pageNum+"=="+pageSize);
        PageInfo<Check> checkPageInfo = null;
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Check> checkList = checkDao.getCheck();
            System.out.println(checkList);
            checkPageInfo = new PageInfo<Check>(checkList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkPageInfo;
    }

    @Override
    public PageInfo<Check> seltCheck(Check check, int pageNum, int pageSize) {
        System.out.println("进入service的seltCheck");
        PageInfo<Check> pageInfo= null;
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Check> list=   checkDao.seltCheck(check);
            pageInfo = new PageInfo<Check>(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    @Override
    public List<Check> getAllCheck() {
        List<Check> checkList = checkDao.getCheck();
        return checkList;
    }
    //根据房间号删除多项房间
    public void delAll(String[] rn) throws Exception {
        try{
            checkDao.delAll(rn);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @Override
    public void upCheckcheckoutTime(String checkouttime,String roomNumber) {
        checkDao.upCheckcheckoutTime(checkouttime,roomNumber);
        billDao.upBillcheckoutTime(checkouttime,roomNumber);
    }

    @Override
    public List<String> getRoomNumberByRoomType(String roomType) {
        List<String> list=null;
        try {
            list=checkDao.getRoomNumberByRoomType(roomType);
            System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Room getDepositByRoomNumber(String roomNumber) {
        Room room = checkDao.getDepositByRoomNumber(roomNumber);
        return room;
    }

    @Override
    public void insertCheck(Check check,String roomtype, String roomtypestr) {
        try {
            checkDao.insertCheck(check,roomtype,roomtypestr);
            Room room=new Room();
            room.setStatus(2);
            room.setRoomNumber(check.getRoomNumber());
            roomDao.updateStatus(room);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //根据房间号删除房间
    public void delCheck(int roomNumber) throws Exception {
        try{
            checkDao.delCheck(roomNumber);

        }catch (Exception e){

            e.printStackTrace();
        }
    }
    @Override
    public List checkoutroom(Check check) {
        List list = null;
        try {
            //查询出入住信息
            list = checkDao.seltCheck(check);
            System.out.println(list);
            check= (Check) list.get(0);
            System.out.println(check);
            System.out.println(check.getIdCard()+"==="+check.getCheckInTime());
            // Bill bill = billDao.getBillIdCardAndCheckintime(check.getIdCard(), check.getCheckInTime());
            // System.out.println("bill:"+bill);
            //查询出会员信息
        /*Vip vip = vipDao.getVipAndVipdiscounte(check.getUserName());
        System.out.println(vip);
        String checkInTime = list.get(0).getCheckInTime();
        String checkouttime = list.get(0).getCheckouttime();
        Date date1 = DateUtil.StringToDate(checkInTime);
        Date date2 = DateUtil.StringToDate(checkouttime);
        double day = DateUtil.differentDaysByMillisecond(date1, date2);
        System.out.println(day + "=============");
        double money=day*list.get(0).getRoom().getPrice()*vip.getVipdiscount().getDiscountRate();
        System.out.println(money);
        check.setMoney(money);*/
            // list.add(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
