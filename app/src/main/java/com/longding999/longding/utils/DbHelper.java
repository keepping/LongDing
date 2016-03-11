package com.longding999.longding.utils;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/11 11:31
 * Desc: 数据库操作工具类
 * *****************************************************************
 */
public class DbHelper {
    private static DbHelper dbHelper;

    private static DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("Suggest.db")
            .setDbVersion(1)
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {

                }
            })
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                }
            });

    public static DbHelper getInstance(){
        if(dbHelper == null){
            dbHelper = new DbHelper();
//            if(SDCardUtils.isMounted()){
//                Logger.e(SDCardUtils.getRootStringPath());
//                daoConfig.setDbDir(new File(SDCardUtils.getRootStringPath()+File.separator+"longding"));
//            }
        }
        return dbHelper;
    }

    public  DbManager getDbManger(){
        DbManager db = x.getDb(daoConfig);
        return db;
    }

}
