package com.ahri.mobike.bean;

import java.util.Date;

/**
 * Created by zouyingjie on 2017/2/21.
 */
public class User {

    private String userId;
    private String idCardNntecumber;
    private String userName;
    private String userPhone;
    private double antecedentMoney;
    private double balance;
    private Date registerCtime;
    private String registerIP;
    private String latitude;
    private String longitude;
    private String school;
    private String studentId;
    private int isCycling;
    private String password;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdCardNntecumber() {
        return idCardNntecumber;
    }

    public void setIdCardNntecumber(String idCardNntecumber) {
        this.idCardNntecumber = idCardNntecumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public double getAntecedentMoney() {
        return antecedentMoney;
    }

    public void setAntecedentMoney(double antecedentMoney) {
        this.antecedentMoney = antecedentMoney;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getRegisterCtime() {
        return registerCtime;
    }

    public void setRegisterCtime(Date registerCtime) {
        this.registerCtime = registerCtime;
    }

    public String getRegisterIP() {
        return registerIP;
    }

    public void setRegisterIP(String registerIP) {
        this.registerIP = registerIP;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getIsCycling() {
        return isCycling;
    }

    public void setIsCycling(int isCycling) {
        this.isCycling = isCycling;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", idCardNntecumber='" + idCardNntecumber + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", antecedentMoney=" + antecedentMoney +
                ", balance=" + balance +
                ", registerCtime=" + registerCtime +
                ", registerIP='" + registerIP + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", school='" + school + '\'' +
                ", studentId='" + studentId + '\'' +
                ", isCycling=" + isCycling +
                ", password='" + password + '\'' +
                '}';
    }
}
