package com.longding999.longding.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/17 15:53
 * Desc: 日期格式转换工具类
 * *****************************************************************
 */
public class DateParseUtils {


    /**
     * 将字符串转换为时间
     * @param createTime
     * @return
     */
    public static long parseStringToLong(String createTime){
        String s = createTime.replace("T", "|");
        if(!s.contains(".")){
            s=s+".00";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss.SS");
        Date date = new Date();
        try {
            date = sdf.parse(s);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 时间转字符串
     * @param time
     * @return
     */
    public static String parseLongToString(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        return sdf.format(date);
    }
}
