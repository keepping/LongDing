package com.longding999.longding.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/******************************************************************
 * Author:LCM
 * Date: 2016/3/9 15:09
 * Desc: ViewPager的适配器
 * *****************************************************************
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;
    private List<String> titleList;


    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mList = fragmentList;
        titleList = new ArrayList<>();
        titleList.add("聊天");
        titleList.add("老师");
        titleList.add("日刊");
        titleList.add("免责声明");
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
