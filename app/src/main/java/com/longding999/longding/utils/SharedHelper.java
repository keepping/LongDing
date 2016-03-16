package com.longding999.longding.utils;

import android.content.Context;
import android.content.SharedPreferences;

/******************************************************************
 * Author:LCM
 * Date: 2016/3/9 11:47
 * Desc: SharedPreferences的工具类
 * *****************************************************************
 */
public class SharedHelper {
    private static SharedPreferences sharedPreferences;
    private static String SHARED_NAME = "LONGDING999";
    public static String   LOGIN = "LOGIN";
    public static String ID = "id";


    /**
     * 保存Boolean值
     * @param key
     * @param value
     */
    public static void saveBoolean(String key,boolean value){
        if(sharedPreferences == null){
            sharedPreferences = MyApplication.mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putBoolean(key,value).commit();
    }

    /**
     * 获取boolean值
     * @param key
     * @param defValue
     * @return
     */
    public static Boolean getBoolean(String key,boolean defValue){
        if(sharedPreferences == null){
            sharedPreferences = MyApplication.mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key,defValue);
    }



    /**
     * 保存String值
     * @param key
     * @param value
     */
    public static void saveString(String key,String value){
        if(sharedPreferences == null){
            sharedPreferences = MyApplication.mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putString(key,value).commit();
    }

    /**
     * 获取String值
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(String key,String defValue){
        if(sharedPreferences == null){
            sharedPreferences = MyApplication.mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key,defValue);
    }


}
