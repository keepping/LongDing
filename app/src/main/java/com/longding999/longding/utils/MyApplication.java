package com.longding999.longding.utils;

import android.app.Application;
import android.content.Context;

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
        mContext = getApplicationContext();
        super.onCreate();
    }
}
