package com.longding999.longding.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.UserSetActivity;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.bean.UserInfo;
import com.longding999.longding.utils.DbHelper;
import com.longding999.longding.utils.FastBlur;
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
 * Date: 2016/3/16 10:31
 * Desc: 用户信息碎片
 * *****************************************************************
 */
public class UserInfoFragment extends BasicFragment implements View.OnClickListener{
    private RelativeLayout headerUserInfo;
    private TextView tvUserName,tvUserLevel;
    private RoundedImageView ivUserIcon;
    private ImageView ivUserSet;

    private TextView tvUserUserName,tvuserRank,tvUserPhone,tvUserGander,tvUserBirthDay,tvUserLocation,tvUserQQ;

    private Button btnLogOut;

    private int _id = -1;
    private DbManager dbManager;

    private UserInfoCallBack callBack;

    @Override
    protected void initBundle() {
        callBack = (UserInfoCallBack) mActivity;

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_userinfo, null);
        headerUserInfo = (RelativeLayout) view.findViewById(R.id.header_userinfo);
        tvUserName = (TextView) view.findViewById(R.id.tv_username);
        tvUserLevel = (TextView) view.findViewById(R.id.tv_userlevel);
        ivUserIcon = (RoundedImageView) view.findViewById(R.id.iv_usericon);
        ivUserSet = (ImageView) view.findViewById(R.id.iv_userset);

        tvUserUserName = (TextView) view.findViewById(R.id.tv_userusername);
        tvuserRank = (TextView) view.findViewById(R.id.tv_userrank);
        tvUserPhone = (TextView) view.findViewById(R.id.tv_userphone);
        tvUserGander = (TextView) view.findViewById(R.id.tv_usergander);
        tvUserBirthDay = (TextView) view.findViewById(R.id.tv_userbirthday);
        tvUserLocation = (TextView) view.findViewById(R.id.tv_userlocation);
        tvUserQQ = (TextView) view.findViewById(R.id.tv_userQQ);

        btnLogOut = (Button) view.findViewById(R.id.btn_logout);
        return view;
    }

    @Override
    protected void initData() {
        if(SharedHelper.getBoolean(SharedHelper.LOGIN,false)){
            _id = Integer.parseInt(SharedHelper.getString(SharedHelper.ID,"-1"));
            Logger.i("fragment中获取的_id:"+_id);
        }
        dbManager = DbHelper.getInstance().getDbManger();
        doBlur(headerUserInfo);
        if(_id !=-1){
            refreshViewById(_id);
        }

    }

    @Override
    protected void setListeners() {
        ivUserSet.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);

    }

    public  void refreshFragment(int _id){
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

            tvUserUserName.setText(userInfo.getUserName());
            tvUserName.setText("昵称:"+userInfo.getUserName());
            tvUserLevel.setText("等级:VIP"+userInfo.getUserRank());
            tvuserRank.setText("VIP"+userInfo.getUserRank());
            tvUserQQ.setText(userInfo.getUserQQ());
            tvUserPhone.setText(userInfo.getUserPhone());
            tvUserBirthDay.setText(parseLongToString(userInfo.getUserBirthDay()));
            if(userInfo.getUserGander()==0){
                tvUserGander.setText("男");
            }else{
                tvUserGander.setText("女");
            }
            tvUserLocation.setText(userInfo.getUserLocation());
            AssetManager assetManager = mActivity.getAssets();
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


    /**
     * 时间转字符串
     * @param time
     * @return
     */
    public String parseLongToString(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        return sdf.format(date);
    }


    /**
     * 高斯模糊算法
     *
     * @param bkg
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void blur(Bitmap bkg, View view) {
        float radius = 2;
        float scaleFactor = 32;

        Bitmap overlay = Bitmap.createBitmap(
                (int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop() / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);
        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        view.setBackgroundDrawable(new BitmapDrawable(getResources(), overlay));
    }


    /**
     * 模糊背景
     */
    private void doBlur(final View view) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                view.buildDrawingCache();
                Bitmap bmp = view.getDrawingCache();

                blur(bmp, view);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_userset:
                Intent intent = new Intent(mActivity, UserSetActivity.class);
                mActivity.startActivity(intent);
                break;

            case R.id.btn_logout:
                if(callBack != null) {
                    SharedHelper.saveBoolean(SharedHelper.LOGIN, false);
                    SharedHelper.saveString(SharedHelper.ID, "-1");
                    callBack.onLogOut();
                }
                break;

            default:
                break;
        }
    }

    public interface UserInfoCallBack{
        void onLogOut();
    }
}
