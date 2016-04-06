package com.longding999.longding.bean;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/6 17:31
 * Desc: 视频直播列表信息
 * *****************************************************************
 */
public class VideoListBean {

    /**
     * roomID : 1
     * roomName : sample string 2
     * roomAdmin : 1
     * introduce : sample string 3
     * opened : true
     * beginDate : 2016-04-06T17:28:29.5947265+08:00
     * pwd : sample string 4
     * maxCount : 1
     * speakType : 1
     * intoType : 1
     */

    private int roomID;
    private String roomName;
    private int roomAdmin;
    private String introduce;
    private boolean opened;
    private String beginDate;
    private String pwd;
    private int maxCount;
    private int speakType;
    private int intoType;

    public VideoListBean() {
    }

    public VideoListBean(int roomID, String roomName, int roomAdmin, String introduce, boolean opened, String beginDate, String pwd, int maxCount, int speakType, int intoType) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomAdmin = roomAdmin;
        this.introduce = introduce;
        this.opened = opened;
        this.beginDate = beginDate;
        this.pwd = pwd;
        this.maxCount = maxCount;
        this.speakType = speakType;
        this.intoType = intoType;
    }

    @Override
    public String toString() {
        return "VideoListBean{" +
                "roomID=" + roomID +
                ", roomName='" + roomName + '\'' +
                ", roomAdmin=" + roomAdmin +
                ", introduce='" + introduce + '\'' +
                ", opened=" + opened +
                ", beginDate='" + beginDate + '\'' +
                ", pwd='" + pwd + '\'' +
                ", maxCount=" + maxCount +
                ", speakType=" + speakType +
                ", intoType=" + intoType +
                '}';
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomAdmin() {
        return roomAdmin;
    }

    public void setRoomAdmin(int roomAdmin) {
        this.roomAdmin = roomAdmin;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getSpeakType() {
        return speakType;
    }

    public void setSpeakType(int speakType) {
        this.speakType = speakType;
    }

    public int getIntoType() {
        return intoType;
    }

    public void setIntoType(int intoType) {
        this.intoType = intoType;
    }
}
