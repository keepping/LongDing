package com.longding999.longding.utils;

import android.util.Log;

/******************************************************************
 * Author:LCM
 * Date: 2016/3/9 11:33
 * Desc:  Log打印工具类
 * *****************************************************************
 */
public class Logger {
    private static final String TAG="LongDing";

    /**
     * error级别打印
     * @param msg
     */
    public static void e(String msg){
        if(MyApplication.isDebug){
            Log.e(TAG,msg);
        }
    }

    /**
     * info级别打印
     * @param msg
     */
    public static void i(String msg){
        if(MyApplication.isDebug){
            Log.i(TAG,msg);

        }
    }

    /**
     * debug级别打印
     * @param msg
     */
    public static void d(String msg){
        if(MyApplication.isDebug){
            Log.d(TAG,msg);
        }
    }

    /**
     * warning级别打印
     * @param msg
     */
    public static void w(String msg){
        if(MyApplication.isDebug){
            Log.w(TAG,msg);
        }
    }
}
