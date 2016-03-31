package com.longding999.longding.bean;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/3/31 18:14
 * Desc:
 * *****************************************************************
 */
public class LiveVideoInfo {

    private String screenShot;
    private String liveCategroy;
    private String LiveTeacher;
    private String liveBrif;
    private String liveTime;

    public LiveVideoInfo(String screenShot, String liveCategroy, String liveTeacher, String liveBrif, String liveTime) {
        this.screenShot = screenShot;
        this.liveCategroy = liveCategroy;
        LiveTeacher = liveTeacher;
        this.liveBrif = liveBrif;
        this.liveTime = liveTime;
    }

    @Override
    public String toString() {
        return "LiveVideoInfo{" +
                "screenShot='" + screenShot + '\'' +
                ", liveCategroy='" + liveCategroy + '\'' +
                ", LiveTeacher='" + LiveTeacher + '\'' +
                ", liveBrif='" + liveBrif + '\'' +
                ", liveTime='" + liveTime + '\'' +
                '}';
    }

    public String getScreenShot() {
        return screenShot;
    }

    public void setScreenShot(String screenShot) {
        this.screenShot = screenShot;
    }

    public String getLiveCategroy() {
        return liveCategroy;
    }

    public void setLiveCategroy(String liveCategroy) {
        this.liveCategroy = liveCategroy;
    }

    public String getLiveTeacher() {
        return LiveTeacher;
    }

    public void setLiveTeacher(String liveTeacher) {
        LiveTeacher = liveTeacher;
    }

    public String getLiveBrif() {
        return liveBrif;
    }

    public void setLiveBrif(String liveBrif) {
        this.liveBrif = liveBrif;
    }

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }
}
