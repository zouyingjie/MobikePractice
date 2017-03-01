package com.ahri.mobike.bean;

import java.util.Date;

/**
 * Created by zouyingjie on 2017/2/21.
 */
public class DealDetail {

    private String detailId;
    private String userId;
    private double dealMoney;
    private int dealType;
    private int dealState;
    private int dealPay;
    private Date dealStartTime;
    private Date dealFinishTime;
    private String dealDesc;


    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(double dealMoney) {
        this.dealMoney = dealMoney;
    }

    public int getDealType() {
        return dealType;
    }

    public void setDealType(int dealType) {
        this.dealType = dealType;
    }

    public int getDealState() {
        return dealState;
    }

    public void setDealState(int dealState) {
        this.dealState = dealState;
    }

    public int getDealPay() {
        return dealPay;
    }

    public void setDealPay(int dealPay) {
        this.dealPay = dealPay;
    }

    public Date getDealStartTime() {
        return dealStartTime;
    }

    public void setDealStartTime(Date dealStartTime) {
        this.dealStartTime = dealStartTime;
    }

    public Date getDealFinishTime() {
        return dealFinishTime;
    }

    public void setDealFinishTime(Date dealFinishTime) {
        this.dealFinishTime = dealFinishTime;
    }

    public String getDealDesc() {
        return dealDesc;
    }

    public void setDealDesc(String dealDesc) {
        this.dealDesc = dealDesc;
    }

    @Override
    public String toString() {
        return "DealDetail{" +
                "dealMoney=" + dealMoney +
                ", dealType=" + dealType +
                ", dealState=" + dealState +
                ", dealPay=" + dealPay +
                ", dealStartTime=" + dealStartTime +
                ", dealDesc='" + dealDesc + '\'' +
                '}';
    }
}
