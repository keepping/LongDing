package com.longding999.longding;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/16 13:32
 * Desc: 用户设置
 * *****************************************************************
 */
public class UserSetActivity extends BasicActivity implements View.OnClickListener{

    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;
    @Override
    protected void bindView() {
        setContentView(R.layout.activity_userset);
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
        tvTitle.setText("个人设置");
        tvRight.setText("保存");
        tvLeft.setVisibility(View.GONE);

    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
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

            case R.id.tv_left:
                break;

            default:
                break;
        }
    }
}
