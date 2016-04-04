package com.longding999.longding.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.adapter.DateListAdapter;
import com.longding999.longding.adapter.SchedulePagerAdapter;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.view.CustomViewPager;
import com.longding999.longding.view.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/1 14:22
 * Desc: 课程表 碎片
 * *****************************************************************
 */
public class ScheduleFragment extends BasicFragment implements View.OnClickListener{
    private ImageButton ivDateToLeft,ivDateToRight;
    private TextView tvDate;

    private CustomViewPager mViewPager;

    private SchedulePagerAdapter pagerAdapter;

    private int listPosition = 0;

    private List<String> dateList = new ArrayList<>();


    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view =View.inflate(mActivity,R.layout.fragment_schedule,null);
        ivDateToLeft = (ImageButton) view.findViewById(R.id.iv_date_toleft);
        ivDateToRight = (ImageButton) view.findViewById(R.id.iv_date_toright);
        mViewPager = (CustomViewPager) view.findViewById(R.id.viewPager);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        return view;
    }

    @Override
    protected void initData() {
        pagerAdapter = new SchedulePagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        dateList.add("3月28日 星期一");
        dateList.add("3月29日 星期二");
        dateList.add("3月30日 星期三");
        dateList.add("3月31日 星期四");
        dateList.add("4月1日 星期五");
        dateList.add("4月2日 星期六");
        tvDate.setText(dateList.get(listPosition));

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
                tvDate.setText(dateList.get(position));
                if (listPosition == 0){
                    ivDateToLeft.setEnabled(false);
                }else if (listPosition == 5){
                    ivDateToRight.setEnabled(false);
                }else{
                    ivDateToRight.setEnabled(true);
                    ivDateToLeft.setEnabled(true);
                }
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
                    tvDate.setText(dateList.get(listPosition));
                    mViewPager.setCurrentItem(listPosition);
                    if(listPosition == 0){
                        ivDateToLeft.setEnabled(false);
                    }
                    ivDateToRight.setEnabled(true);
                }
                break;

            case R.id.iv_date_toright:
                if(listPosition<5) {
                    listPosition +=1;
                    tvDate.setText(dateList.get(listPosition));
                    mViewPager.setCurrentItem(listPosition);
                    if(listPosition ==5){
                        ivDateToRight.setEnabled(false);
                    }
                    ivDateToLeft.setEnabled(true);
                }
                break;

        }
    }
}
