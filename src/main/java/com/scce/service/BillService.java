package com.scce.service;

import com.github.pagehelper.PageInfo;
import com.scce.pojo.Bill;
import com.scce.pojo.Roomchartwithbill;

import java.util.List;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-03 21:16
 **/
public interface BillService {

    //查询订单信息
    public PageInfo<Bill> getBill(int pageNum, int pageSize);

    //根据房间类型，查询订单数量
    public List<Roomchartwithbill> getBillNumByRoomType();

  public   PageInfo<Bill> getBillByRoomAndIdCard(Integer roomType,String idCard, int pageNum, int pageSize);

  public   List<Bill> getAllBill();

  //根据房间号和省份证和入住时间查询订单
    public Bill getBillIdCardAndCheckintime(String idCard, String roomNumber, String checkintime);

   public void upBillstatus(String idCard, String checkintime);
}
