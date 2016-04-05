package com.longding999.longding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.bean.UserInfo;
import com.longding999.longding.utils.DbHelper;
import com.longding999.longding.utils.Logger;
import com.longding999.longding.utils.SharedHelper;

import org.xutils.DbManager;
import org.xutils.db.Selector;
import org.xutils.ex.DbException;

import java.util.List;

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

    private DbManager dbManager;

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
        imageLeft.setImageResource(R.mipmap.iv_close);

        edtPhoneNumber = (EditText) findViewById(R.id.edt_phoneNumber);
        edtPassWord = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvForgetPwd = (TextView) findViewById(R.id.tv_forgetpwd);
    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        dbManager = DbHelper.getInstance().getDbManger();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_left:
                onBackPressed();
                break;

            case R.id.tv_right:
                Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_login:
                login();
                break;

            default:
                break;
        }
    }


    private void login() {
        String phoneNumber = edtPhoneNumber.getText().toString();
        String passWord = edtPassWord.getText().toString();
        if(phoneNumber.isEmpty()||passWord.isEmpty()){
            shortToast("手机或密码不能为空!");
        }else{
            try {
                List<UserInfo> userPhone = dbManager.selector(UserInfo.class).where("userPhone", "=", phoneNumber).findAll();
                if(userPhone==null){
                    shortToast("账号错误!");
                }else{
                    UserInfo userInfo = userPhone.get(0);
                    if(passWord.equals(userInfo.getUserPwd())){
                        shortToast("登录成功!");

                        SharedHelper.saveBoolean(SharedHelper.LOGIN,true);
                        SharedHelper.saveString(SharedHelper.ID,userInfo.get_id()+"");

                        Intent intent = new Intent();
                        intent.putExtra("_id",userInfo.get_id());
                        setResult(1001,intent);
                        finish();
                    }else{
                        shortToast("密码错误!");
                    }
                }
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }
}
