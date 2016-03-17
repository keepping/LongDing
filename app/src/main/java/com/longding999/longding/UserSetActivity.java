package com.longding999.longding;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.bean.UserInfo;
import com.longding999.longding.utils.DateParseUtils;
import com.longding999.longding.utils.DbHelper;
import com.longding999.longding.utils.Logger;
import com.longding999.longding.utils.SharedHelper;
import com.makeramen.roundedimageview.RoundedImageView;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    private int _id;

    private RoundedImageView ivUserIcon;
    private TextView tvUserName,tvUserRank,tvUserPhone,tvUserQQ,tvUserLocation,tvUserBirthday,tvUserGander;
    private LinearLayout layoutUserIcon,layoutUserName,layoutUserRank,layoutUserPhone,layoutUserQQ,layoutUserLocation,layoutUserBirthday,layoutUserGander,layoutUserPwd;

    private DbManager dbManager;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_userset);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void getIntents() {
        Intent intent = getIntent();
        _id = intent.getIntExtra("_id",-1);
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

        ivUserIcon = (RoundedImageView) findViewById(R.id.iv_usericon);
        tvUserBirthday = (TextView) findViewById(R.id.tv_userbirthday);
        tvUserGander = (TextView) findViewById(R.id.tv_usergander);
        tvUserPhone = (TextView) findViewById(R.id.tv_userphone);
        tvUserName = (TextView) findViewById(R.id.tv_username);
        tvUserRank = (TextView) findViewById(R.id.tv_userrank);
        tvUserQQ = (TextView) findViewById(R.id.tv_userQQ);
        tvUserLocation = (TextView) findViewById(R.id.tv_userlocation);

        layoutUserBirthday = (LinearLayout) findViewById(R.id.layout_userbirthday);
        layoutUserGander = (LinearLayout) findViewById(R.id.layout_usergander);
        layoutUserIcon = (LinearLayout) findViewById(R.id.layout_usericon);
        layoutUserLocation = (LinearLayout) findViewById(R.id.layout_userlocation);
        layoutUserName = (LinearLayout) findViewById(R.id.layout_username);
        layoutUserPhone = (LinearLayout) findViewById(R.id.layout_userphone);
        layoutUserQQ = (LinearLayout) findViewById(R.id.layout_userQQ);
        layoutUserPwd = (LinearLayout) findViewById(R.id.layout_userpwd);
        layoutUserRank = (LinearLayout) findViewById(R.id.layout_userrank);
    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        tvLeft.setOnClickListener(this);

        layoutUserRank.setOnClickListener(this);
        layoutUserPwd.setOnClickListener(this);
        layoutUserPhone.setOnClickListener(this);
        layoutUserQQ.setOnClickListener(this);
        layoutUserLocation.setOnClickListener(this);
        layoutUserBirthday.setOnClickListener(this);
        layoutUserGander.setOnClickListener(this);
        layoutUserIcon.setOnClickListener(this);
        layoutUserName.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        dbManager = DbHelper.getInstance().getDbManger();
        refreshViewById(_id);

    }

    /**
     * 根据用户Id刷新界面
     * @param _id
     */
    private void refreshViewById(int _id){
        try {
            List<UserInfo> id = dbManager.selector(UserInfo.class).where("_id", "=", _id).findAll();
            UserInfo userInfo = id.get(0);

            tvUserName.setText(userInfo.getUserName());
            tvUserRank.setText("VIP"+userInfo.getUserRank());
            tvUserQQ.setText(userInfo.getUserQQ());
            tvUserPhone.setText(userInfo.getUserPhone());
            tvUserBirthday.setText(DateParseUtils.parseLongToString(userInfo.getUserBirthDay()));
            if(userInfo.getUserGander()==0){
                tvUserGander.setText("男");
            }else{
                tvUserGander.setText("女");
            }
            tvUserLocation.setText(userInfo.getUserLocation());
            AssetManager assetManager = getAssets();
            InputStream is = assetManager.open("user.jpg");
            //以下注释掉的代码不靠谱.若采用,会有异常
            //InputStream is = assetManager.open("file:///android_asset/Fresh_01.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            ivUserIcon.setImageBitmap(bitmap);


        } catch (DbException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }







    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_left:
                onBackPressed();
                break;

            case R.id.tv_left:
                break;

            case R.id.layout_userbirthday:
                changeBirthday();
                break;

            case R.id.layout_usergander:
                changeGrander();
                break;

            case R.id.layout_usericon:
                changeIcon();
                break;

            case R.id.layout_userlocation:
                changeLocation();
                break;

            case R.id.layout_username:
                changeUserName();
                break;

            case R.id.layout_userpwd:
                changeUserPwd();
                break;

            case R.id.layout_userQQ:
                changeUserQQ();
                break;

            case R.id.layout_userphone:
                shortToast("点击了用户手机！");
                break;

            case R.id.layout_userrank:
                shortToast("点击了用户等级！");
                break;

            default:
                break;
        }
    }

    /**
     * 修改用户性别
     */
    private void changeGrander() {

    }

    /**
     * 修改用户qq
     */
    private void changeUserQQ() {

    }

    /**
     * 修改用户密码
     */
    private void changeUserPwd() {

    }

    /**
     * 修改用户昵称
     */
    private void changeUserName() {

    }

    /**
     * 修改用户常驻地
     */
    private void changeLocation() {

    }

    /**
     * 修改用户头像
     */
    private void changeIcon() {

    }

    /**
     * 修改出生日期
     */
    private void changeBirthday() {

    }
}
