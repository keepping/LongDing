package com.longding999.longding;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.utils.MyApplication;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/5 11:12
 * Desc: 学院页面
 * *****************************************************************
 */
public class CollegeActivity extends BasicActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_college);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void getIntents() {

    }

    @Override
    protected void initViews() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        imageLeft = (ImageView) findViewById(R.id.image_left);
        tvTitle.setText("学院");
        tvLeft.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);


        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        if(MyApplication.isLOLLIPOP) {
            for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
                RadioButton rb = (RadioButton) mRadioGroup.getChildAt(i);
                rb.setBackgroundResource(R.drawable.ripple_bg);
            }
        }

    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_left:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            RadioButton rb = (RadioButton) group.getChildAt(i);

        }
    }
}
