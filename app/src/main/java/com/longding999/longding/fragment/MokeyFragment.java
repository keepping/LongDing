package com.longding999.longding.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.longding999.longding.R;
import com.longding999.longding.adapter.EmotionPagerAdapter;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/3/29 13:20
 * Desc:
 * *****************************************************************
 */
public class MokeyFragment extends Fragment {
    private Context mContext;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.emotion_fragment, null);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        EmotionPagerAdapter adapter = new EmotionPagerAdapter(1,getChildFragmentManager());
        viewPager.setAdapter(adapter);
        return view;
    }
}

