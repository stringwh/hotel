package com.scce.pojo;

import java.io.Serializable;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-05-30 20:54
 **/
public class Check implements Serializable {
    private Integer id;         //编号
    private String roomNumber; //房间号
    private String vipid;      //会员卡号
    private String userName;    //客户姓名
    private String sex;         //性别
    private String idCard;      //身份证
    private String phone;      //电话
    private String checkInTime;//入住时间
    private String checkouttime;//退房时间
    private Integer status;      //入住状态（默认为0）
    private  String statusstr;   ////入住状态str
    //封装房间信息
    private  Room room;





    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getVipid() {
        return vipid;
    }

    public void setVipid(String vipid) {
        this.vipid = vipid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckouttime() {
        return checkouttime;
    }

    public void setCheckouttime(String checkouttime) {
        this.checkouttime = checkouttime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusstr() {
        if (status==0){
            statusstr="入住";
        }else if (status==1){
            statusstr="未入住";
        }
        return statusstr;
    }

    public void setStatusstr(String statusstr) {
        this.statusstr = statusstr;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", vipid='" + vipid + '\'' +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                ", checkInTime='" + checkInTime + '\'' +
                ", checkouttime='" + checkouttime + '\'' +
                ", status=" + status +
                ", statusstr='" + statusstr + '\'' +
                ", room=" + room +
                '}';
    }
}
