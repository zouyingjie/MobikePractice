package com.ahri.mobike.bean;

import java.util.Date;

/**
 * Created by zouyingjie on 2017/2/21.
 */
public class MobikeBicycle {

    private String mobikeId;
    private int mobikeType;
    private String userId;
    private Date purchaseCtime;
    private double purchasePrice;
    private Date lastUsetime;
    private double roadDistance;
    private String latitude;
    private String longitude;
    private int isUsing;

    public int getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(int isUsing) {
        this.isUsing = isUsing;
    }

    public String getMobikeId() {
        return mobikeId;
    }

    public void setMobikeId(String mobikeId) {
        this.mobikeId = mobikeId;
    }

    public int getMobikeType() {
        return mobikeType;
    }

    public void setMobikeType(int mobikeType) {
        this.mobikeType = mobikeType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPurchaseCtime() {
        return purchaseCtime;
    }

    public void setPurchaseCtime(Date purchaseCtime) {
        this.purchaseCtime = purchaseCtime;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getLastUsetime() {
        return lastUsetime;
    }

    public void setLastUsetime(Date lastUsetime) {
        this.lastUsetime = lastUsetime;
    }

    public double getRoadDistance() {
        return roadDistance;
    }

    public void setRoadDistance(double roadDistance) {
        this.roadDistance = roadDistance;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "MobikeBicycle{" +
                "mobikeId='" + mobikeId + '\'' +
                ", mobikeType=" + mobikeType +
                ", userId='" + userId + '\'' +
                ", purchaseCtime=" + purchaseCtime +
                ", purchasePrice=" + purchasePrice +
                ", lastUsetime=" + lastUsetime +
                ", roadDistance=" + roadDistance +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", isUsing=" + isUsing +
                '}';
    }
}
