package com.longding999.longding.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/3/31 16:57
 * Desc:
 * *****************************************************************
 */
public class LiveFragment extends BasicFragment {
    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;

    private List<Fragment> fragmentList;
    private LivePagerAdpter mAdapter;
    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_live, null);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        return view;
    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new LiveVideoFragment());
        fragmentList.add(new LiveTextFragment());
        mAdapter = new LivePagerAdpter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    protected void setListeners() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) group.getChildAt(i);
                    if (rb.getId() == checkedId) {
                        mViewPager.setCurrentItem(i);
                    }
                }
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton)mRadioGroup.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public class LivePagerAdpter extends FragmentPagerAdapter{

        public LivePagerAdpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList==null?0:fragmentList.size();
        }
    }
}
