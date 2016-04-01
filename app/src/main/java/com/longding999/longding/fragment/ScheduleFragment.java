package com.longding999.longding.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.longding999.longding.R;
import com.longding999.longding.adapter.DateListAdapter;
import com.longding999.longding.adapter.SchedulePagerAdapter;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.view.HorizontalListView;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/1 14:22
 * Desc: 课程表 碎片
 * *****************************************************************
 */
public class ScheduleFragment extends BasicFragment implements View.OnClickListener{
    private ImageButton ivDateToLeft,ivDateToRight;
    private HorizontalListView dateListView;
    private ViewPager mViewPager;

    private SchedulePagerAdapter pagerAdapter;
    private DateListAdapter mAdapter;

    private int listPosition = 0;


    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view =View.inflate(mActivity,R.layout.fragment_schedule,null);
        ivDateToLeft = (ImageButton) view.findViewById(R.id.iv_date_toleft);
        ivDateToRight = (ImageButton) view.findViewById(R.id.iv_date_toright);
        dateListView = (HorizontalListView) view.findViewById(R.id.list_date);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        return view;
    }

    @Override
    protected void initData() {
        pagerAdapter = new SchedulePagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

        mAdapter = new DateListAdapter(mActivity);
        dateListView.setAdapter(mAdapter);

    }

    @Override
    protected void setListeners() {
        ivDateToRight.setOnClickListener(this);
        ivDateToLeft.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                listPosition = position;
                dateListView.scrollTo(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_date_toleft:
                if (listPosition> 0){
                    listPosition-=1;
                    dateListView.scrollTo(listPosition);
                    mViewPager.setCurrentItem(listPosition);
                }
                break;

            case R.id.iv_date_toright:
                if(listPosition<6) {
                    listPosition +=1;
                    dateListView.scrollTo(listPosition);
                    mViewPager.setCurrentItem(listPosition);
                }
                break;


        }
    }
}
