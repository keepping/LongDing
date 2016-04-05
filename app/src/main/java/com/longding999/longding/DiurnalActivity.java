package com.longding999.longding;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/5 10:04
 * Desc: 日刊界面
 * *****************************************************************
 */
public class DiurnalActivity extends BasicActivity implements View.OnClickListener{
    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;


    @Override
    protected void bindView() {
        setContentView(R.layout.activity_diurnal);
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
        tvTitle.setText("日刊");
        tvLeft.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);


    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
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
}
