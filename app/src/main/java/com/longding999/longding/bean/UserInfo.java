package com.longding999.longding.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/16 14:35
 * Desc: 用户信息
 * *****************************************************************
 */
@Table(name = "UserInfo")
public class UserInfo {
    @Column(name = "_id",isId = true)
    private int _id;

    @Column(name = "userIcon")
    private String userIcon;

    @Column(name ="userName")
    private String userName;

    @Column(name = "userPwd")
    private String userPwd;

    @Column(name = "userBirthDay")
    private long userBirthDay;

    @Column(name = "userGander")
    private int userGander;

    @Column(name = "userPhone")
    private String userPhone;

    @Column(name = "userLocation")
    private String userLocation;

    @Column(name = "userQQ")
    private String userQQ;

    @Column(name = "userRank")
    private int userRank;

    public UserInfo(){}
    public UserInfo(String userIcon, String userName, String userPwd, long userBirthDay, int userGander, String userPhone, String userLocation, String userQQ, int userRank) {
        this.userIcon = userIcon;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userBirthDay = userBirthDay;
        this.userGander = userGander;
        this.userPhone = userPhone;
        this.userLocation = userLocation;
        this.userQQ = userQQ;
        this.userRank = userRank;
    }

    public UserInfo(int _id, String userIcon, String userName, String userPwd, long userBirthDay, int userGander, String userPhone, String userLocation, String userQQ, int userRank) {
        this._id = _id;
        this.userIcon = userIcon;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userBirthDay = userBirthDay;
        this.userGander = userGander;
        this.userPhone = userPhone;
        this.userLocation = userLocation;
        this.userQQ = userQQ;
        this.userRank = userRank;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "_id=" + _id +
                ", userIcon='" + userIcon + '\'' +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userBirthDay=" + userBirthDay +
                ", userGander=" + userGander +
                ", userPhone='" + userPhone + '\'' +
                ", userLocation='" + userLocation + '\'' +
                ", userQQ='" + userQQ + '\'' +
                ", userRank=" + userRank +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public long getUserBirthDay() {
        return userBirthDay;
    }

    public void setUserBirthDay(long userBirthDay) {
        this.userBirthDay = userBirthDay;
    }

    public int getUserGander() {
        return userGander;
    }

    public void setUserGander(int userGander) {
        this.userGander = userGander;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserQQ() {
        return userQQ;
    }

    public void setUserQQ(String userQQ) {
        this.userQQ = userQQ;
    }

    public int getUserRank() {
        return userRank;
    }

    public void setUserRank(int userRank) {
        this.userRank = userRank;
    }
}
