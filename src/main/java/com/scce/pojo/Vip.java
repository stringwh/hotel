package com.scce.pojo;


import java.io.Serializable;

public class Vip implements Serializable {
    private int vipId;//会员卡号
    private String name;//姓名
    private String sex;//性别
    private String idCard;//身份证
    private String phone;//电话
    private int level;//会员等级
    private float balance;//余额
    private float addMonetary;//累计消费金额

    private Vipdiscount vipdiscount;

    public Vipdiscount getVipdiscount() {
        return vipdiscount;
    }

    public void setVipdiscount(Vipdiscount vipdiscount) {
        this.vipdiscount = vipdiscount;
    }

    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getAddMonetary() {
        if (addMonetary >= 0 && addMonetary < 500) {
            level = 1;
        } else if (addMonetary >= 500 && addMonetary < 1000) {
            level = 2;
        } else if (addMonetary >= 1000 && addMonetary < 1500) {
            level = 3;
        } else if (addMonetary >= 1500 && addMonetary < 2000) {
            level = 4;
        } else if (addMonetary >= 2000 && addMonetary < 2500) {
            level = 5;
        } else {
            level = 6;
        }
        return addMonetary;
    }

    public void setAddMonetary(float addMonetary) {
        this.addMonetary = addMonetary;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "vipId=" + vipId +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                ", level=" + level +
                ", balance=" + balance +
                ", addMonetary=" + addMonetary +
                ", vipdiscount=" + vipdiscount +
                '}';
    }
}
