package com.longding999.longding.bean;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/10 11:05
 * Desc: 老师javabean
 * *****************************************************************
 */
public class TeacherInfo {
    private String Id;
    private String teachername;
    private String teachercontent;
    private String detailcontent;

    public TeacherInfo(){}
    public TeacherInfo(String Id, String teachername, String teachercontent, String detailcontent) {
        this.Id = Id;
        this.teachername = teachername;
        this.teachercontent = teachercontent;
        this.detailcontent = detailcontent;
    }

    @Override
    public String toString() {
        return "TeacherInfo{" +
                "Id='" + Id + '\'' +
                ", teachername='" + teachername + '\'' +
                ", teachercontent='" + teachercontent + '\'' +
                ", detailcontent='" + detailcontent + '\'' +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getTeachercontent() {
        return teachercontent;
    }

    public void setTeachercontent(String teachercontent) {
        this.teachercontent = teachercontent;
    }

    public String getDetailcontent() {
        return detailcontent;
    }

    public void setDetailcontent(String detailcontent) {
        this.detailcontent = detailcontent;
    }
}
