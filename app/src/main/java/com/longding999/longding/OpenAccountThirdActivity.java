package com.longding999.longding;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/6 13:38
 * Desc:
 * *****************************************************************
 */
public class OpenAccountThirdActivity extends BasicActivity implements View.OnClickListener{
    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    private Button btnBack;
    @Override
    protected void bindView() {
        setContentView(R.layout.activity_openaccount_third);
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
        tvTitle.setText("开户");
        tvLeft.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);

        btnBack = (Button) findViewById(R.id.btn_back);
    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_left:
            case R.id.btn_back:
               onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        setResult(3001);
        finish();
    }
}
