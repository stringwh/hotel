package com.scce.controller;

import com.github.pagehelper.PageInfo;
import com.scce.pojo.Room;
import com.scce.pojo.RoomType;
import com.scce.pojo.Roomchart;
import com.scce.service.RoomService;
import com.scce.service.RoomTypeService;
import com.scce.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "RoomController测试")
@RequestMapping("/room")

public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;

    //查询房间信息并分页
    @RequestMapping("/getRoom")
    @ApiOperation(value = "getRoom接口", notes = "getRoom接口", httpMethod = "POST")
    public JsonResult getRoom(int pageNum, int pageSize)throws Exception{
        System.out.println("执行查询房间信息的方法");
        PageInfo<Room> pageInfo=roomService.getRoom(pageNum,pageSize);
        System.out.println(pageInfo.getList());
        return new  JsonResult(0,pageInfo.getList(),pageInfo.getTotal());
    }
    //根据房间号删除房间
    @RequestMapping("/delRoom")
    @ApiOperation(value = "delRoom接口", notes = "delRoom接口", httpMethod = "POST")
    public JsonResult delRoom(int roomNumber)throws Exception{
        System.out.println("执行删除房间的方法");
        System.out.println(roomNumber+"================");
        roomService.delRoom(roomNumber);
        return new  JsonResult(0,"删除成功");
    }
    //增加房间
    @RequestMapping("/insertRoom")
    @ApiOperation(value = "insertRoom接口", notes = "insertRoom接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="roomNumber",value="房间号",required=true),
            @ApiImplicitParam(name="price",value="价格",required=true),
            @ApiImplicitParam(name="roomType",value="房间类型",required=true),
            @ApiImplicitParam(name="deposit",value="押金",required=true),
            @ApiImplicitParam(name="status",value="客房状态",required=true)
    })
    public JsonResult insertRoom(Room room)throws Exception{
        System.out.println("执行增加房间的方法");
        roomService.insertRoom(room);
        return new  JsonResult(0,"添加成功");
    }
    //根据房间号修改房间
    @RequestMapping("/updateRoom")
    @ApiOperation(value = "updateRoom接口", notes = "updateRoom接口", httpMethod = "POST")
    public JsonResult updateRoom(Room room)throws Exception{
          roomService.updateRoom(room);
          return new  JsonResult(0,  "修改成功");
    }
    //根据房间号修改房间状态
    @RequestMapping("/updateStatus")
    @ApiOperation(value = "updateStatus接口", notes = "updateStatus接口", httpMethod = "POST")
    public JsonResult updateStatus(Room room)throws Exception{
        roomService.updateStatus(room);
        return new JsonResult(0,  "修改成功");
    }
    //查询房间状态并分页
    @RequestMapping("/roomDeposit")
    @ApiOperation(value = "roomDeposit接口", notes = "roomDeposit接口", httpMethod = "POST")
    public JsonResult roomDeposit(int pageNum, int pageSize)throws Exception{
        /*List<Room> room1=roomService.roomDeposit();*/
        System.out.println("执行查询房间状态的方法");
        PageInfo<Room> pageInfo=roomService.roomDeposit(pageNum,pageSize);
        System.out.println(pageInfo.getList());
        return new  JsonResult(0,pageInfo.getList(),pageInfo.getTotal());
    }
    //查询房间状态为带清理
    @RequestMapping("/getRoomstatus")
    @ApiOperation(value = "getRoomstatus接口", notes = "getRoomstatus接口", httpMethod = "POST")
    public JsonResult getRoomstatus(int pageNum, int pageSize)throws Exception{
        System.out.println("执行查询房间状态为带清理的方法");
        PageInfo<Room> pageInfo=roomService.getRoomstatus(pageNum,pageSize);
        System.out.println(pageInfo.getList());
        return new  JsonResult(0,pageInfo.getList(),pageInfo.getTotal());
    }
    //根据房间号，房间类型，房间状态查询
    @RequestMapping("/roomNTS")
    @ApiOperation(value = "roomNTS接口", notes = "roomNTS接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomNumber", value = "房间号", required = false, dataType = "String"),
            @ApiImplicitParam(name = "roomType", value = "房间类型", required = false, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "房间状态", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "int"),
    })
    public JsonResult roomNTS(@RequestParam(value = "roomNumber",required = false) String roomNumber,
                              @RequestParam(value = "roomType",required = false) Integer roomType,
                              @RequestParam(value = "status",required = false) Integer status,
                              int pageNum, int pageSize)throws Exception{
        System.out.println("进入roomNTS方法");
        System.out.println(roomNumber+"==="+roomType+"==="+status+"==="+pageNum+"==="+pageSize);
        //List<Room> room2=roomService.roomNTS();
        PageInfo<Room> pageInfo=roomService.roomNTS(roomNumber,roomType,status,pageNum,pageSize);
        return  new JsonResult(0, pageInfo.getList(), pageInfo.getTotal());
    }

    //roomchart条形图，根据房间类型显示房间总数和可入住的房间数量
    @RequestMapping("/getRoomNum")
    public JsonResult getRoomNum() throws Exception {
        List<Roomchart> list=new ArrayList<Roomchart>();
        List<RoomType> roomTypeList = roomTypeService.getAllRoomType();
        System.out.println(roomTypeList);
        try {
            for (RoomType roomType : roomTypeList) {
                Integer roomNum = roomService.getRoomNum(roomType.getRid());
                Integer inRoomNum = roomService.getInRoomNum(roomType.getRid());
                Roomchart roomchart=new Roomchart();
                roomchart.setRoomNum(roomNum);
                roomchart.setInRoomNum(inRoomNum);
                roomchart.setRoomtypestr(roomType.getType());
                list.add(roomchart);
            }}catch (Exception e){
            e.printStackTrace();
        }
        return new JsonResult(0,list);
    }
    //根据房间号删除多项房间
    @RequestMapping("/delAll")
    @ApiOperation(value = "delAll接口", notes = "delAll接口", httpMethod = "POST")
    public JsonResult delAll(String[] rn)throws Exception{
        System.out.println("执行删除多项房间方法");
        roomService.delAll(rn);
        return new JsonResult(0,"删除成功");
    }
}



