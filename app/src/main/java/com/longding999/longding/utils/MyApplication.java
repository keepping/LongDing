package com.longding999.longding.utils;

import android.app.Application;
import android.content.Context;

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

    @Override
    public void onCreate() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        mContext = getApplicationContext();
        super.onCreate();
    }
}
