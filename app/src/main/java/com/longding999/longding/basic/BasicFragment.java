package com.longding999.longding.basic;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.longding999.longding.R;

/******************************************************************
 * Author:LCM
 * Date: 2016/3/9 11:18
 * Desc:抽取Fragment基类,统一代码格式
 * *****************************************************************
 */
public abstract class BasicFragment extends Fragment {
    protected Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        initBundle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return  initViews();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        setListeners();
    }

    /**
     * 获取传递的数据
     */
    protected abstract void initBundle();


    /**
     * 初始化控件
     *
     */
    protected abstract View initViews();


    /**
     * 加载数据
     */
    protected abstract void initData();


    /**
     * 设置监听
     */
    protected abstract void setListeners();


    public void shortToast(String msg){
        Toast.makeText(mActivity,msg,Toast.LENGTH_SHORT).show();
    }


    public void longToast(String msg){
        Toast.makeText(mActivity,msg,Toast.LENGTH_LONG).show();
    }

}
