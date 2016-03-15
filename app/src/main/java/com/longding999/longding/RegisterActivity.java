package com.longding999.longding;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/15 11:05
 * Desc: 注册页面
 * *****************************************************************
 */
public class RegisterActivity extends BasicActivity implements View.OnClickListener{
    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    private EditText edtPhoneNumber,edtVerification,edtPassWord,edtPwdAgain;
    private CheckBox cbFollowRule;
    private Button btnSubmit,btnVerification;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_register);
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
        tvTitle.setText("注册");
        tvLeft.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);

        edtPhoneNumber = (EditText) findViewById(R.id.edt_phoneNumber);
        edtVerification = (EditText) findViewById(R.id.edt_verification);
        edtPassWord = (EditText) findViewById(R.id.edt_password);
        edtPwdAgain = (EditText) findViewById(R.id.edt_passwordagain);
        cbFollowRule = (CheckBox) findViewById(R.id.cb_followrule);
        btnVerification = (Button) findViewById(R.id.btn_verifition);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
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