package com.scce.pojo;

import java.io.Serializable;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-20 20:21
 **/
public class Vipdiscount implements Serializable {
    private int lever;
    private String leverName;
    private float discountRate;

    public int getLever() {
        return lever;
    }

    public void setLever(int lever) {
        this.lever = lever;
    }

    public String getLeverName() {
        return leverName;
    }

    public void setLeverName(String leverName) {
        this.leverName = leverName;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "Vipdiscount{" +
                "lever=" + lever +
                ", leverName='" + leverName + '\'' +
                ", discountRate=" + discountRate +
                '}';
    }
}
