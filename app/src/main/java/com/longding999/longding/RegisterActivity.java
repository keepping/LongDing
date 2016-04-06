package com.longding999.longding;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.bean.UserInfo;
import com.longding999.longding.utils.DbHelper;
import com.longding999.longding.view.ClearEditText;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.Date;

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

    private ClearEditText edtPhoneNumber,edtVerification,edtPassWord,edtPwdAgain;
    private CheckBox cbFollowRule;
    private Button btnSubmit,btnVerification;

    private DbManager dbManager;

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

        edtPhoneNumber = (ClearEditText) findViewById(R.id.edt_phoneNumber);
        edtVerification = (ClearEditText) findViewById(R.id.edt_verification);
        edtPassWord = (ClearEditText) findViewById(R.id.edt_password);
        edtPwdAgain = (ClearEditText) findViewById(R.id.edt_passwordagain);
        cbFollowRule = (CheckBox) findViewById(R.id.cb_followrule);
        btnVerification = (Button) findViewById(R.id.btn_verifition);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

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

            case R.id.btn_submit:
                submit();
                break;

            default:
                break;
        }
    }

    private void submit() {
        String path = getApplicationContext().getCacheDir().getAbsolutePath() + "/icon/user.jpg";
        try {
            dbManager.save(new UserInfo(path,"PC9527","666",new Date().getTime(),0 ,"666","黄山","111111",1));
        } catch (DbException e) {
            e.printStackTrace();
        }
        onBackPressed();
    }
}
