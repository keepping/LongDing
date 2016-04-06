package com.longding999.longding.bean;

import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/6 17:11
 * Desc:
 * *****************************************************************
 */
public class TeacherListBean {
    private List<TeacherBean> teacher;

    public TeacherListBean() {
    }

    public TeacherListBean(List<TeacherBean> teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "TeacherListBean{" +
                "teacher=" + teacher +
                '}';
    }

    public List<TeacherBean> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<TeacherBean> teacher) {
        this.teacher = teacher;
    }
}
