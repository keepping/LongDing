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
 * Date: 2016/4/5 16:12
 * Desc: 开户页面
 * *****************************************************************
 */
public class OpenAccountActivity extends BasicActivity implements View.OnClickListener{
    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    private Button btnOpenAccount;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_open_account);
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

        btnOpenAccount = (Button) findViewById(R.id.btn_openaccount);
    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        btnOpenAccount.setOnClickListener(this);
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


            case R.id.btn_openaccount:
                break;
        }
    }
}
