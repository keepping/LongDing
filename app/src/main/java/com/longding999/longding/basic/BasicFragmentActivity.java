package com.longding999.longding.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/******************************************************************
 * Author:LCM
 * Date: 2016/3/9 14:09
 * Desc: 抽取FragmentActivity的基类
 * *****************************************************************
 */
public abstract class BasicFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView();
        getIntents();
        initViews();
        setListeners();
        initData();
    }

    /**
     * 绑定布局文件
     */
    protected abstract void bindView();
    /**
     * 获取Intent数据
     */
    protected abstract void getIntents();

    /**
     * 初始化布局文件
     */
    protected abstract void initViews();

    /**
     * 设置事件监听
     */
    protected abstract void setListeners();

    /**
     * 加载数据
     */
    protected abstract void initData();



    /**
     * 打印短吐司
     * @param msg
     */
    public void shortToast(String msg){
        Toast.makeText(getApplication(),msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 打印长吐司
     * @param msg
     */
    public void longToast(String msg){
        Toast.makeText(getApplication(),msg,Toast.LENGTH_LONG).show();
    }

}
