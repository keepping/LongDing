package com.longding999.longding.utils;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.umeng.socialize.PlatformConfig;

import org.xutils.BuildConfig;
import org.xutils.x;

/******************************************************************
 * Author:LCM
 * Date: 2016/3/9 11:31
 * Desc:
 * *****************************************************************
 */
public class MyApplication extends Application {
    public static boolean isDebug = true;  //配合Logger工具类使用

    public static Context mContext;
    public static final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    public static final boolean isLOLLIPOP = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;



    @Override
    public void onCreate() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        mContext = getApplicationContext();
        super.onCreate();

        //微信    wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wxeab4141fb7392f0d", "6452e7894795e900945a2f8e8768022c");
        //豆瓣RENREN平台目前只能在服务器端配置
        //新浪微博
        PlatformConfig.setSinaWeibo("3686775990", "99a6b02ad07a70265dfbd8ce550e596e");
        //易信
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("1105258140", "NLhWXDfF1P04dpi6");
//        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//        PlatformConfig.setAlipay("2015111700822536");
//        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
//        PlatformConfig.setPinterest("1439206");
    }
}
