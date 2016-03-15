package com.longding999.longding;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/15 9:57
 * Desc: 登陆页面
 * *****************************************************************
 */
public class LoginActivity extends BasicActivity implements View.OnClickListener{
    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    private EditText edtPhoneNumber,edtPassWord;
    private Button btnLogin;
    private TextView tvForgetPwd;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_login);
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
        tvTitle.setText("登录");
        tvLeft.setVisibility(View.GONE);
        tvRight.setText("注册");
        imageLeft.setImageDrawable(getResources().getDrawable(R.mipmap.close));

        edtPhoneNumber = (EditText) findViewById(R.id.edt_phoneNumber);
        edtPassWord = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvForgetPwd = (TextView) findViewById(R.id.tv_forgetpwd);
    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
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

            case R.id.tv_right:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
