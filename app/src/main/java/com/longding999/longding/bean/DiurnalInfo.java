package com.longding999.longding.bean;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/10 14:58
 * Desc: 日刊 javabean
 * *****************************************************************
 */
public class DiurnalInfo {
    private String diurnaltitle;
    private String diurnalContent;

    public DiurnalInfo(){}
    public DiurnalInfo(String diurnaltitle, String diurnalContent) {
        this.diurnaltitle = diurnaltitle;
        this.diurnalContent = diurnalContent;
    }

    @Override
    public String toString() {
        return "DiurnalInfo{" +
                "diurnaltitle='" + diurnaltitle + '\'' +
                ", diurnalContent='" + diurnalContent + '\'' +
                '}';
    }

    public String getDiurnaltitle() {
        return diurnaltitle;
    }

    public void setDiurnaltitle(String diurnaltitle) {
        this.diurnaltitle = diurnaltitle;
    }

    public String getDiurnalContent() {
        return diurnalContent;
    }

    public void setDiurnalContent(String diurnalcontent) {
        this.diurnalContent = diurnalcontent;
    }
}
