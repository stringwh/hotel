package com.scce.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: IdeaProjects
 * @description:   员工信息实体类
 * @author: Lxy
 * @create: 2019-05-30 21:01
 **/
public class Emplyee implements Serializable {
    private Integer id;     //自动增长列
    private String account;//账号
    private String realName;    //真实姓名
    private String password;//密码
    private String sex;      //性别

    private Date birthDate; //出生日期
    private String idCard;  //身份证
    private String phone;     //电话
    private String address; //地址
    private Integer type;    //用户类型
private String typestr;


    public String getTypestr() {
        if (type==1){
            typestr="管理员";
        }else if (type==2){
            typestr="普通用户";
        }
        return typestr;
    }

    public void setTypestr(String typestr) {
        this.typestr = typestr;
    }

    public Emplyee() {
    }

    public Emplyee(Integer id, String account, String realName, String password, String sex, Date birthDate, String idCard, String phone, String address, Integer type) {
        this.id = id;
        this.account = account;
        this.realName = realName;
        this.password = password;
        this.sex = sex;
        this.birthDate = birthDate;
        this.idCard = idCard;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {

        this.birthDate = birthDate;
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Emplyee{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                ", idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                '}';
    }
}
