package com.longding999.longding;

import android.test.InstrumentationTestCase;

import com.longding999.longding.utils.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/11 14:03
 * Desc:
 * *****************************************************************
 */
public class TestClass extends InstrumentationTestCase{

    public void test(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss.SS");
        Date date = new Date();
        try {
            date = sdf.parse("2015-01-01|17:54:59.783");
            Logger.e(date.getTime()+"");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
