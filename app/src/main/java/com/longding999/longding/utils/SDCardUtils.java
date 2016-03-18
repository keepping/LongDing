package com.longding999.longding.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    /**
     * 将assets文件夹下的文件写入磁盘中
     * @param pathName
     * @param fileName
     * @param is
     */
    public static boolean writeAssets(String pathName,String fileName, InputStream is){
        FileOutputStream fos = null;
        boolean isCreate;

        //封装文件存储目录
        String newDir = pathName;
        if(!new File(newDir).exists()){
            isCreate = new File(newDir).mkdirs();
            Logger.i("创建目录--->"+isCreate);
        }
        try {
            fos = new FileOutputStream(new File(newDir,fileName));
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff))!= -1){
                fos.write(buff,0,len);
            }
            fos.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                if(fos != null){
                    fos.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }


}
