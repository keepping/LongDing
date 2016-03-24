package com.longding999.longding.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicFragment;

/******************************************************************
 * Author:LCM
 * Date: 2016/3/9 13:42
 * Desc: 行情 页面
 * *****************************************************************
 */
public class MarketFragment extends BasicFragment {
    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_textlive, null);
        tvLeft = (TextView) view.findViewById(R.id.tv_left);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        imageLeft = (ImageView) view.findViewById(R.id.image_left);
        tvTitle.setText("行情");
        tvRight.setVisibility(View.GONE);
        tvLeft.setVisibility(View.GONE);
        imageLeft.setVisibility(View.GONE);
        return view;
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void setListeners() {

    }
}
