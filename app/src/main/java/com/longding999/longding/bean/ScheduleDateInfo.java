package com.longding999.longding.bean;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/1 16:15
 * Desc:
 * *****************************************************************
 */
public class ScheduleDateInfo {

    private String time;
    private String teacher;

    public ScheduleDateInfo(String time, String teacher) {
        this.time = time;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "ScheduleDateInfo{" +
                "time='" + time + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
