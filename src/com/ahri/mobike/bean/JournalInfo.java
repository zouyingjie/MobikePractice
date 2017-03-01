package com.ahri.mobike.bean;

import java.util.Date;

/**
 * Created by zouyingjie on 2017/2/21.
 */
public class JournalInfo {

    private String journalId;
    private String userId;
    private String mobikeId;
    private Date startCtime;
    private Date endCtime;
    private double cost;
    private String path;
    private double enerySave;
    private double sportsAchieve;
    private double distance;

    public String getJournalId() {
        return journalId;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobikeId() {
        return mobikeId;
    }

    public void setMobikeId(String mobikeId) {
        this.mobikeId = mobikeId;
    }

    public Date getStartCtime() {
        return startCtime;
    }

    public void setStartCtime(Date startCtime) {
        this.startCtime = startCtime;
    }

    public Date getEndCtime() {
        return endCtime;
    }

    public void setEndCtime(Date endCtime) {
        this.endCtime = endCtime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getEnerySave() {
        return enerySave;
    }

    public void setEnerySave(double enerySave) {
        this.enerySave = enerySave;
    }

    public double getSportsAchieve() {
        return sportsAchieve;
    }

    public void setSportsAchieve(double sportsAchieve) {
        this.sportsAchieve = sportsAchieve;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "JournalInfo{" +
                "journalId='" + journalId + '\'' +
                ", userId='" + userId + '\'' +
                ", mobikeId='" + mobikeId + '\'' +
                ", startCtime=" + startCtime +
                ", endCtime=" + endCtime +
                ", cost=" + cost +
                ", path='" + path + '\'' +
                ", enerySave=" + enerySave +
                ", sportsAchieve=" + sportsAchieve +
                ", distance=" + distance +
                '}';
    }
}
