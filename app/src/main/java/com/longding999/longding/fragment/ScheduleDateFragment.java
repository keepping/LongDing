package com.longding999.longding.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.adapter.ScheduleDateAdapter;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.bean.ScheduleDateInfo;
import com.longding999.longding.view.CalculHeightListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/1 15:22
 * Desc: 每天的课程表
 * *****************************************************************
 */
public class ScheduleDateFragment extends BasicFragment {
    private TextView tvDate, tvNonFarm;
    private CalculHeightListView mListView;
    private List<ScheduleDateInfo> scheduleDateInfos;
    private ScheduleDateAdapter mAdapter;

    private String date;
    private String week;
    private String teacher;

    @Override
    protected void initBundle() {
        Bundle bundle = getArguments();
        date = bundle.getString("date");
        week = bundle.getString("week");
    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_schedule_date, null);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvNonFarm = (TextView) view.findViewById(R.id.tv_non_farm);
        mListView = (CalculHeightListView) view.findViewById(R.id.listView);
        return view;
    }

    @Override
    protected void initData() {
        scheduleDateInfos = new ArrayList<>();
        switch (week) {
            case "星期一":
                teacher = "王老师/张老师";
                tvNonFarm.setVisibility(View.GONE);
                break;

            case "星期二":
                teacher = "李老师/张老师";
                tvNonFarm.setVisibility(View.GONE);
                break;

            case "星期三":
                teacher = "王老师/李老师";
                tvNonFarm.setVisibility(View.VISIBLE);
                tvNonFarm.setText("（非农）");
                break;

            case "星期四":
                teacher = "王老师/赵老师";
                tvNonFarm.setVisibility(View.GONE);
                break;
            case "星期五":
                teacher = "赵老师/张老师";
                tvNonFarm.setVisibility(View.VISIBLE);
                tvNonFarm.setText("（大非农）");
                break;
            case "星期六":
                teacher = "李老师/张老师";
                tvNonFarm.setVisibility(View.GONE);
                break;
        }

        tvDate.setText(date);
        for (int i = 0; i < 7; i++) {
            scheduleDateInfos.add(new ScheduleDateInfo((9 + i * 2) + ":00-" + (9 + (i + 1) * 2) + ":00", teacher));
        }

        mAdapter = new ScheduleDateAdapter(scheduleDateInfos, mActivity);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void setListeners() {

    }
}
