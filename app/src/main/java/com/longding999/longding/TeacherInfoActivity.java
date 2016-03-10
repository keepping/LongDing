package com.longding999.longding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.longding999.longding.basic.BasicActivity;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/10 15:55
 * Desc:
 * *****************************************************************
 */
public class TeacherInfoActivity extends BasicActivity implements View.OnClickListener{
    private String teacherId;

    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;
    @Override
    protected void bindView() {
        setContentView(R.layout.activity_teacherinfo);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void getIntents() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        teacherId = bundle.getString("teacherId");
    }

    @Override
    protected void initViews() {
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        imageLeft = (ImageView) findViewById(R.id.image_left);
        tvTitle.setText("老师详情");
        tvRight.setVisibility(View.GONE);
        tvLeft.setVisibility(View.GONE);
    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        shortToast("老师id:"+teacherId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_left:
                onBackPressed();
                break;

            default:
                break;
        }
    }
}
