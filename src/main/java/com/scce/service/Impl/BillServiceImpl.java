package com.scce.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scce.dao.*;
import com.scce.pojo.*;
import com.scce.service.BillService;
import com.scce.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-03 21:17
 **/
@Service
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;
    @Autowired
    private VipDao vipDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private RoomTypeDao roomTypeDao;

    @Autowired
    private VipdiscountDao vipdiscountDao;

    //查询订单信息
    @Override
    public PageInfo<Bill> getBill(int pageNum, int pageSize) {
        PageInfo<Bill> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Bill> billList = billDao.getBill();
            pageInfo = new PageInfo<Bill>(billList);
            System.out.println("BillServiceImpl的"+pageInfo.getList());
            for (Bill bill : pageInfo.getList()) {
                Room room = roomDao.getRoomByroomNumber(bill.getRoomNumber());
                System.out.println("BillServiceImpl的room:"+room);
                if (room != null) {
                    if (room != null) {
                        bill.setRoom(room);
                        Vip vip = vipDao.getVipAndVipdiscounte(bill.getGuestName());
                        if (vip!=null) {
                            bill.setVip(vip);
                            System.out.println(vip);
                            String checkInTime = bill.getCheckintime();
                            String checkouttime = bill.getCheckouttime();
                            if (checkouttime==null||"".equals(checkouttime)){
                                bill.setPayAmount(0f);
                            }else {
                            Date date1 = DateUtil.StringToDate(checkInTime);
                            Date date2 = DateUtil.StringToDate(checkouttime);
                            int day = DateUtil.differentDaysByMillisecond(date1, date2);
                            System.out.println(day + "=============");
                            float money = day * room.getPrice() * vip.getVipdiscount().getDiscountRate();
                            System.out.println(money);
                            bill.setPayAmount(money);
                        }
                    }}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageInfo;
    }

    @Override
    public List<Roomchartwithbill> getBillNumByRoomType() {
        List<Roomchartwithbill> list = new ArrayList<Roomchartwithbill>();
        List<RoomType> roomTypeList = roomTypeDao.getRoomType();
        try {
            for (RoomType roomType : roomTypeList) {
                Integer num = billDao.getBillNumByRoomType(roomType.getRid());
                Roomchartwithbill roomchartwithbill = new Roomchartwithbill();
                roomchartwithbill.setBillnumber(num);
                roomchartwithbill.setRoomtype(roomType.getType());
                System.out.println(roomchartwithbill);
                list.add(roomchartwithbill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public PageInfo<Bill> getBillByRoomAndIdCard(Integer roomType, String idCard, int pageNum, int pageSize) {
        PageInfo<Bill> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Bill> list = billDao.getBillByRoomAndIdCard(roomType, idCard);
            pageInfo = new PageInfo<Bill>(list);
            for (Bill bill : pageInfo.getList()) {
                System.out.println(bill.getRoomNumber());
                Room room = roomDao.getRoomByroomNumber(bill.getRoomNumber());
                System.out.println(room);
                bill.setRoom(room);
            }
            System.out.println("Service方法中：" + pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageInfo;
    }

    @Override
    public List<Bill> getAllBill() {
        List<Bill> billList = billDao.getBill();
        for (Bill bill : billList) {
            Room room = roomDao.getRoomByroomNumber(bill.getRoomNumber());
            System.out.println("room:"+room);
            if (room != null) {
                bill.setRoom(room);
                Vip vip = vipDao.getVipAndVipdiscounte(bill.getGuestName());
                if (vip!=null) {
                    bill.setVip(vip);
                    System.out.println(vip);
                    String checkInTime = bill.getCheckintime();
                    String checkouttime = bill.getCheckouttime();
                    if (checkouttime==null||"".equals(checkouttime)){
                        bill.setPayAmount(0f);
                    }else {
                    Date date1 = DateUtil.StringToDate(checkInTime);
                    Date date2 = DateUtil.StringToDate(checkouttime);
                    int day = DateUtil.differentDaysByMillisecond(date1, date2);
                    System.out.println(day + "=============");
                    float money = day * room.getPrice() * vip.getVipdiscount().getDiscountRate();
                    System.out.println(money);
                    bill.setPayAmount(money);
                }}
            }
        }
        return billList;
    }

    @Override
    public Bill getBillIdCardAndCheckintime(String IdCard, String checkintime,String roomNumber) {
        System.out.println("进入getBillIdCardAndCheckintime");
        System.out.println(IdCard+"==="+checkintime+"==="+roomNumber);
        Bill bill = billDao.getBillIdCardAndCheckintime(IdCard,checkintime,roomNumber);
        System.out.println(bill);
        Room room = roomDao.getRoomByroomNumber(bill.getRoomNumber());
        System.out.println("room:"+room);
        System.out.println(room);
        if (room != null) {
            bill.setRoom(room);
            Vip vip = vipDao.getVipAndVipdiscounte(bill.getGuestName());
            System.out.println("vip"+vip);
            if (vip!=null){
                bill.setVip(vip);
            System.out.println(vip);
            String checkInTime = bill.getCheckintime();
            String checkouttime = bill.getCheckouttime();
                if (checkouttime==null||"".equals(checkouttime)){
                    bill.setPayAmount(0f);
                }else {
            Date date1 = DateUtil.StringToDate(checkInTime);
            Date date2 = DateUtil.StringToDate(checkouttime);
                int day = DateUtil.differentDaysByMillisecond(date1, date2);
            System.out.println(day + "=============");
                float money = day * room.getPrice() * vip.getVipdiscount().getDiscountRate();
            System.out.println(money);
            bill.setPayAmount(money);

        }}}
        return bill;
    }

    @Override
    public void upBillstatus(String idCard, String checkintime) {
        billDao.upBillstatus(idCard,checkintime);
    }
}
