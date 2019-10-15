package com.scce.controller;

import com.github.pagehelper.PageInfo;
import com.scce.pojo.Bill;
import com.scce.pojo.Room;
import com.scce.pojo.Roomchartwithbill;
import com.scce.pojo.Vip;
import com.scce.service.BillService;
import com.scce.service.CheckService;
import com.scce.service.RoomService;
import com.scce.service.VipService;
import com.scce.utils.ExcelUtil;
import com.scce.utils.JsonResult;
import com.scce.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-04 00:06
 **/
@RestController
@Api(description = "订单接口测试")
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;
    @Autowired
    private CheckService checkService;
    @Autowired
    private VipService vipService;
    @Autowired
    private RoomService roomService;


    //查询订单信息
    @RequestMapping("/getBill")
    public JsonResult getBill(int pageNum, int pageSize) {
        System.out.println("进入getBill");
        System.out.println(pageNum + "=======" + pageSize);
        PageInfo<Bill> pageInfo = billService.getBill(pageNum, pageSize);
        System.out.println(pageInfo.getList());
        return new JsonResult(0, pageInfo.getList(), pageInfo.getTotal() );
    }

    @RequestMapping("/getBillNumByRoomType")
    public JsonResult getBillNumByRoomType() {
        System.out.println("进入getBillNumByRoomType");
        List<Roomchartwithbill> list = billService.getBillNumByRoomType();
        return new JsonResult(0, list);
    }

    //根据房间类型和身份证好查询订单
    @RequestMapping("/getBillByRoomAndIdCard")
    @ApiOperation(value = "getBillByRoomAndIdCard接口", notes = "接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomType", value = "房间类型", required = false, dataType = "int"),
            @ApiImplicitParam(name = "idCard", value = "账号", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
    })
    public JsonResult getBillByRoomAndIdCard(@RequestParam(value = "roomType",required = false) Integer roomType,
                                             @RequestParam(value = "idCard",required = false)String idCard,
                                             int pageNum, int pageSize) {
        System.out.println("进入getBillByRoomAndIdCard方法");
        System.out.println(roomType+"==="+idCard+"==="+pageNum+"==="+pageSize);
        PageInfo<Bill> pageInfo = billService.getBillByRoomAndIdCard(roomType, idCard, pageNum, pageSize);
        return new JsonResult(0, pageInfo.getList(), pageInfo.getTotal());
    }

    //导出表格
    @RequestMapping("/exportExcel")
    public String exportExcel(HttpServletResponse response){
        System.out.println("进入TestExcel");
        try {
            Workbook wb = new HSSFWorkbook();  //创建工作簿
            String headers[] = {"编号", "房间号", "房间类型", "客人姓名", "客人手机号", "入住时间", "退房时间", "消费金额", "是否结账"};
            List<Bill> list = billService.getAllBill();
            System.out.println(list);
            ExcelUtil.fillExcelData(list, wb, headers);
            ResponseUtil.export(response, wb, "订单信息.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //结账
    @RequestMapping("/BillPayment")
    public JsonResult BillPayment(Integer roomNumber,String userName,String idCard,String checkintime,float maney,float paymoney) throws Exception {
        System.out.println(roomNumber+userName+idCard+checkintime+maney+paymoney);
        //删除入住信息
        checkService.delCheck(roomNumber);
        //将订单状态改为已结账
        billService.upBillstatus(idCard,checkintime);
        //修会员卡余额和累计消费金额
        Vip vip = vipService.getVipByIdCard(idCard);

        vip.setBalance(vip.getBalance()-maney);//减少余额
        vip.setAddMonetary(vip.getAddMonetary()+paymoney);//累计消费金额增加
        vipService.upVipbalanceAndaddMonetary(vip);
        //修改房间状态
        Room room=new Room();
        room.setStatus(3);
        room.setRoomNumber(roomNumber.toString());
        roomService.updateStatus(room);
        return new JsonResult(0,"结账成功");
    }

}
