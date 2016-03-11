package com.longding999.longding.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/11 11:10
 * Desc: 操作建议javabean
 * *****************************************************************
 */
@Table(name = "SuggestInfo")
public class SuggestInfo {
    @Column(name = "_id",isId = true)
    private int _id;
    @Column(name = "id")
    private int id;
    @Column(name = "createTime")
    private long createTime;
    @Column(name = "createDate")
    private String createDate;
    @Column(name = "crateUserID")
    private int createUserID;
    @Column(name = "createUserName")
    private String createUserName;
    @Column(name = "comefrom")
    private String comefrom;
    @Column(name = "category")
    private String category;
    @Column(name = "goods")
    private String goods;
    @Column(name = "openprice")
    private String openprice;
    @Column(name = "stopprofit")
    private String stopprofit;
    @Column(name = "stoploss")
    private String stoploss;
    @Column(name = "ps")
    private String ps;

    public SuggestInfo(){}

    public SuggestInfo(int id, long createTime, String createDate, int createUserID, String createUserName, String comefrom, String category, String goods, String openprice, String stopprofit, String stoploss, String ps) {
        this.id = id;
        this.createTime = createTime;
        this.createDate = createDate;
        this.createUserID = createUserID;
        this.createUserName = createUserName;
        this.comefrom = comefrom;
        this.category = category;
        this.goods = goods;
        this.openprice = openprice;
        this.stopprofit = stopprofit;
        this.stoploss = stoploss;
        this.ps = ps;
    }

    @Override
    public String toString() {
        return "SuggestInfo{" +
                "_id=" + _id +
                ", id=" + id +
                ", createTime=" + createTime +
                ", createDate='" + createDate + '\'' +
                ", createUserID=" + createUserID +
                ", createUserName='" + createUserName + '\'' +
                ", comefrom='" + comefrom + '\'' +
                ", category='" + category + '\'' +
                ", goods='" + goods + '\'' +
                ", openprice='" + openprice + '\'' +
                ", stopprofit='" + stopprofit + '\'' +
                ", stoploss='" + stoploss + '\'' +
                ", ps='" + ps + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(int createUserID) {
        this.createUserID = createUserID;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getOpenprice() {
        return openprice;
    }

    public void setOpenprice(String openprice) {
        this.openprice = openprice;
    }

    public String getStopprofit() {
        return stopprofit;
    }

    public void setStopprofit(String stopprofit) {
        this.stopprofit = stopprofit;
    }

    public String getStoploss() {
        return stoploss;
    }

    public void setStoploss(String stoploss) {
        this.stoploss = stoploss;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
