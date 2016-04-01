package com.longding999.longding.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.longding999.longding.fragment.ScheduleDateFragment;
import com.longding999.longding.fragment.ScheduleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/1 16:52
 * Desc:
 * *****************************************************************
 */
public class SchedulePagerAdapter extends FragmentPagerAdapter {
    private List<ScheduleDateFragment> mList;
    private Bundle bundle;
    private ScheduleDateFragment scheduleFragment;

    public SchedulePagerAdapter(FragmentManager fm) {
        super(fm);
        initData();
    }

    private void initData() {
        mList = new ArrayList<>();

        for (int i = 0;i<6;i++){
            scheduleFragment = new ScheduleDateFragment();
            bundle = new Bundle();
            switch (i){
                case 0:
                    bundle.putString("date","3月28日");
                    bundle.putString("week","星期一");
                    break;

                case 1:
                    bundle.putString("date","3月29日");
                    bundle.putString("week","星期二");
                    break;

                case 2:
                    bundle.putString("date","3月30日");
                    bundle.putString("week","星期三");
                    break;

                case 3:
                    bundle.putString("date","3月31日");
                    bundle.putString("week","星期四");
                    break;

                case 4:
                    bundle.putString("date","4月1日");
                    bundle.putString("week","星期五");
                    break;

                case 5:
                    bundle.putString("date","4月2日");
                    bundle.putString("week","星期六");
                    break;
            }
            scheduleFragment.setArguments(bundle);
            mList.add(scheduleFragment);
        }

    }


    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
