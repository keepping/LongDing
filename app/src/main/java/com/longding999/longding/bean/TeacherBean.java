package com.longding999.longding.bean;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/6 17:00
 * Desc: 老师信息
 * *****************************************************************
 */
public class TeacherBean {

    /**
     * tid : 1
     * account : sample string 2
     * pwd : sample string 3
     * locked : true
     * nickname : sample string 4
     * regDate : 2016-04-06T16:59:43.0595703+08:00
     * roomID : 1
     * endDate : 2016-04-06T16:59:43.0595703+08:00
     * pwdRetry : sample string 5
     * lastRetryTime : 2016-04-06T16:59:43.0595703+08:00
     * introduce : sample string 7
     */

    private int tid;
    private String account;
    private String pwd;
    private boolean locked;
    private String nickname;
    private String regDate;
    private int roomID;
    private String endDate;
    private String pwdRetry;
    private String lastRetryTime;
    private String introduce;

    public TeacherBean() {
    }

    public TeacherBean(int tid, String account, String pwd, boolean locked, String nickname, String regDate, int roomID, String endDate, String pwdRetry, String lastRetryTime, String introduce) {
        this.tid = tid;
        this.account = account;
        this.pwd = pwd;
        this.locked = locked;
        this.nickname = nickname;
        this.regDate = regDate;
        this.roomID = roomID;
        this.endDate = endDate;
        this.pwdRetry = pwdRetry;
        this.lastRetryTime = lastRetryTime;
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "TeacherBean{" +
                "tid=" + tid +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", locked=" + locked +
                ", nickname='" + nickname + '\'' +
                ", regDate='" + regDate + '\'' +
                ", roomID=" + roomID +
                ", endDate='" + endDate + '\'' +
                ", pwdRetry='" + pwdRetry + '\'' +
                ", lastRetryTime='" + lastRetryTime + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPwdRetry() {
        return pwdRetry;
    }

    public void setPwdRetry(String pwdRetry) {
        this.pwdRetry = pwdRetry;
    }

    public String getLastRetryTime() {
        return lastRetryTime;
    }

    public void setLastRetryTime(String lastRetryTime) {
        this.lastRetryTime = lastRetryTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
