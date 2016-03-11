package com.longding999.longding.utils;

import android.os.Environment;

import java.io.File;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/11 11:34
 * Desc: SDCard操作工具类
 * *****************************************************************
 */
public class SDCardUtils {

    /**
     * SDCard是否挂载上
     * @return
     */
    public static boolean isMounted(){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }

    /**
     * 获取SD卡根目录
     * @return File
     */
    public static File getRootFilePath(){
        if(isMounted()){
            return Environment.getExternalStorageDirectory();
        }
        return null;
    }

    /**
     * 获取SD卡根目录
     * @return String
     */
    public static String getRootStringPath(){
        if(getRootFilePath()!=null){
            return getRootFilePath().getAbsolutePath();
        }
        return null;
    }


}
