package com.scce.controller;

import com.github.pagehelper.PageInfo;
import com.scce.pojo.Room;
import com.scce.pojo.RoomType;
import com.scce.service.RoomTypeService;
import com.scce.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "RoomTypeController测试")
@RequestMapping("/roomtype")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;
    //查询房间类型名
    @RequestMapping("/getRoomType")
    @ApiOperation(value = "getRoomType接口", notes = "getRoomType接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum",value="当前页数",required=true),
            @ApiImplicitParam(name="pageSize",value="当前每页大小",required=true)
    })
    public JsonResult getRoomType(int pageNum, int pageSize)throws Exception{
        System.out.println("执行查询房间类型名的方法");
        PageInfo<RoomType> pageInfo=roomTypeService.getRoomType(pageNum,pageSize);
        System.out.println(pageInfo.getList());
       //List<RoomType> roomType = roomTypeService.getRoomType();
        return  new JsonResult(0,pageInfo.getList(),pageInfo.getTotal());
    }
    //修改客房类型名
    @RequestMapping("/updateRT")
    @ApiOperation(value = "updateRT接口", notes = "updateRT接口", httpMethod = "POST")
    public JsonResult updateRT(RoomType roomType)throws Exception{
        System.out.println("执行修改客房类型名的方法");
        System.out.println(roomType);
        roomTypeService.updateRT(roomType);
        return new JsonResult(0,  "修改成功");
    }
    //增加房间类型
    @RequestMapping("/insertRT")
    @ApiOperation(value = "insertRT接口", notes = "insertRT接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="自编号",required=true),
            @ApiImplicitParam(name="rid",value="房间类型编号",required=true),
            @ApiImplicitParam(name="type",value="房间类型",required=true)
    })
    public JsonResult insertRT(RoomType roomType)throws Exception{
        System.out.println("增加房间类型的方法");
        System.out.println(roomType+"================");
        roomTypeService.insertRT(roomType);
        return new JsonResult(0,"成功");
    }

    //查询所有的房间类型名
    @RequestMapping("/getAllRoomType")
    @ApiOperation(value = "getAllRoomType接口", notes = "getAllRoomType接口", httpMethod = "POST")
    public JsonResult getAllRoomType() throws Exception {
        System.out.println("进入getAllRoomType");
        List<RoomType> list = roomTypeService.getAllRoomType();
        System.out.println(list+"---------");
        return new JsonResult(0,list);
    }
    //根据房间类型编号删除房间类型
    @RequestMapping("/delRT")
    @ApiOperation(value = "delRT接口", notes = "delRT接口", httpMethod = "POST")
    public JsonResult delRT(int rid)throws Exception{
        System.out.println("执行删除方法");
        roomTypeService .delRT(rid);
        return new JsonResult(0,"成功");
    }
}

