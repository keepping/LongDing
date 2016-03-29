package com.longding999.longding.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.longding999.longding.fragment.ClassicEmotionFragment;
import com.longding999.longding.fragment.MokeyEmotionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/3/29 15:40
 * Desc:
 * *****************************************************************
 */
public class EmotionPagerAdapter extends FragmentPagerAdapter {
    private int count;
    private List<Fragment> mList;

    public EmotionPagerAdapter(int type,FragmentManager fm) {
        super(fm);
        if(type ==0) {
            count = 44 / 21 + 1;
            mList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                ClassicEmotionFragment classicEmotionFragment = new ClassicEmotionFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("page", i);
                classicEmotionFragment.setArguments(bundle);
                mList.add(classicEmotionFragment);
            }
        }else if(type ==1){
            count = 41/8+1;
            mList = new ArrayList<>();
            for (int i = 0; i <count ;i++) {
                MokeyEmotionFragment mokeyEmotionFragment = new MokeyEmotionFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("page",i);
                mokeyEmotionFragment.setArguments(bundle);
                mList.add(mokeyEmotionFragment);
            }

        }

    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }
}
