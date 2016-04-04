package com.longding999.longding;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/4 17:36
 * Desc:
 * *****************************************************************
 */
public class AboutActivity extends BasicActivity implements View.OnClickListener{

    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_about);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void getIntents() {

    }

    @Override
    protected void initViews() {
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        imageLeft = (ImageView) findViewById(R.id.image_left);
        tvTitle.setText("关于我们");
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
