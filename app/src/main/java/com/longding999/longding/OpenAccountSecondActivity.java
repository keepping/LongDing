package com.longding999.longding;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.view.ClearEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/6 13:24
 * Desc:
 * *****************************************************************
 */
public class OpenAccountSecondActivity extends BasicActivity implements View.OnClickListener{

    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;


    private ClearEditText edtPhoneNunber;
    private Button btnNext;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_openaccount_second);
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

        edtPhoneNunber = (ClearEditText) findViewById(R.id.edt_phoneNumber);
        btnNext = (Button) findViewById(R.id.btn_next);

    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        btnNext.setOnClickListener(this);
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


            case R.id.btn_next:
                if(check()){
                    Intent intent = new Intent(this,OpenAccountThirdActivity.class);
                    startActivityForResult(intent,3000);
                }
                break;

        }
    }


    private boolean check(){
        String trim = edtPhoneNunber.getText().toString().trim();
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        if(trim.isEmpty()){
            shortToast("请输入手机号");
            return false;
        }else if(!p.matcher(trim).matches()){
            shortToast("请输入正确的手机号");
            return false;
        }else {

            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 3000&&resultCode==3001){
            setResult(3001);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
