package com.scce.controller;

import com.github.pagehelper.PageInfo;
import com.scce.pojo.Bill;
import com.scce.pojo.Check;
import com.scce.pojo.Room;
import com.scce.pojo.Vip;
import com.scce.service.BillService;
import com.scce.service.CheckService;
import com.scce.service.RoomService;
import com.scce.service.VipService;
import com.scce.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-05 17:07
 **/
@RestController
@Api(description = "CheckController测试")
@RequestMapping("/check")
public class CheckController {
    @Autowired
    private VipService vipService;
    @Autowired
    private RoomService roomService;

    @Autowired
    private CheckService checkService;
    @Autowired
    private BillService billService;

    @RequestMapping("/getCheck")
    public JsonResult getCheck(int pageNum, int pageSize) {
        System.out.println("进入getCheck方法");
        PageInfo<Check> pageInfo = checkService.getCheck(pageNum, pageSize);
        return new JsonResult(0, pageInfo.getList(), pageInfo.getTotal());
    }

    @RequestMapping(value = "/seltCheck")
    public JsonResult seltCheck(Check check, int pageNum, int pageSize) {
        System.out.println("进入seltCheck");
        System.out.println(check);
        PageInfo<Check> pageInfo = null;
        try {
            pageInfo = checkService.seltCheck(check, pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(pageInfo);
        return new JsonResult(0, pageInfo.getList(), pageInfo.getTotal());
    }

    //查询所有房间信息
    @RequestMapping("/getAllCheck")
    public JsonResult getAllCheck() {
        System.out.println("getAllCheck");
        List<Check> list = checkService.getAllCheck();
        System.out.println("list:" + list);
        return new JsonResult(0, list);
    }

    @RequestMapping("/checkoutroom")
    public JsonResult checkoutroom(Check check) {
        System.out.println(check);
        //设置退房时间
        checkService.upCheckcheckoutTime(check.getCheckouttime(),check.getRoomNumber());
        List list = checkService.checkoutroom(check);
        check= (Check) list.get(0);
        Bill bill = billService.getBillIdCardAndCheckintime(check.getIdCard(), check.getCheckInTime(),check.getRoomNumber());
        list.add(bill);
        System.out.println(list);

        return new JsonResult(0, list);
    }
    //根据房间号删除多项房间
    @RequestMapping("/delAll")
    @ApiOperation(value = "delAll接口", notes = "delAll接口", httpMethod = "POST")
    public JsonResult delAll(String[] rn)throws Exception{
        System.out.println("执行删除多项房间方法");
        checkService.delAll(rn);
        return new JsonResult(0,"删除成功");
    }
    //根据房间号删除房间
    @RequestMapping("/delCheck")
    @ApiOperation(value = "delCheck接口", notes = "delCheck接口", httpMethod = "POST")
    public JsonResult delCheck(int roomNumber)throws Exception{
        System.out.println("执行删除房间的方法");
        System.out.println(roomNumber+"================");
        checkService.delCheck(roomNumber);
        return new  JsonResult(0,"删除成功");
    }
    @RequestMapping("/getRoomNumberByRoomType")
    public JsonResult getRoomNumberByRoomType(String roomType){
        System.out.println("进入getRoomNumberByRoomType()方法");
        System.out.println(roomType);
        List<String> list=checkService.getRoomNumberByRoomType(roomType);
        return new JsonResult(0,list);
    }
    @RequestMapping("/getDepositByRoomNumber")
    public JsonResult getDepositByRoomNumber(String roomNumber){
        Room room=null;
        System.out.println("进入getDepositByRoomNumber方法");
        System.out.println(roomNumber);
        room =checkService.getDepositByRoomNumber(roomNumber);
        return  new JsonResult(0,room);
    }
    @RequestMapping("/getVipByIdCard")
    public  JsonResult getVipByIdCard(String idCard){
        System.out.println("进入getVipByIdCard方法");
        System.out.println(idCard);
        Vip vip =vipService.getVipByIdCard(idCard);
        System.out.println(vip);
        if (vip!=null) {
            return new JsonResult(0, vip);
        }else {
            return new JsonResult(1,1);
        }

    }
    @RequestMapping("/insertCheck")
    public JsonResult insertCheck(Check check,String roomtype,String roomtypestr){
        Room room=new Room();
        System.out.println("進入insertCheck 方法");
        System.out.println(check+roomtype+roomtypestr);

        checkService.insertCheck(check,roomtype,roomtypestr);

        room.setRoomNumber(check.getRoomNumber());
        room.setStatus(2);
        try {
            roomService.updateStatus(room);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new JsonResult();
    }
}
