package com.longding999.longding.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longding999.longding.AboutActivity;
import com.longding999.longding.R;
import com.longding999.longding.UserSetActivity;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.bean.UserInfo;
import com.longding999.longding.utils.DateParseUtils;
import com.longding999.longding.utils.DbHelper;
import com.longding999.longding.utils.Logger;
import com.longding999.longding.utils.SharedHelper;
import com.makeramen.roundedimageview.RoundedImageView;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/4 15:59
 * Desc: 我的 碎片
 * *****************************************************************
 */
public class MineFragment extends BasicFragment implements View.OnClickListener {
    private TextView tvUserName,tvUserRank;
    private RoundedImageView ivUserIcon;
    private LinearLayout layoutUserInfo;

    private LinearLayout layoutService,layoutNews,layoutChangePwd,layoutCleanCache,layoutAbout;
    private TextView tvLogOut;

    private int _id = -1;
    private DbManager dbManager;

    private MineCallBack callBack;

    @Override
    protected void initBundle() {
        callBack = (MineCallBack) mActivity;
    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_mine, null);
        tvUserName = (TextView) view.findViewById(R.id.tv_userName);
        tvUserRank = (TextView) view.findViewById(R.id.tv_userRank);
        ivUserIcon = (RoundedImageView) view.findViewById(R.id.iv_usericon);
        layoutUserInfo = (LinearLayout) view.findViewById(R.id.layout_userInfo);

        layoutService = (LinearLayout) view.findViewById(R.id.layout_service);
        layoutNews = (LinearLayout) view.findViewById(R.id.layout_news);
        layoutChangePwd = (LinearLayout) view.findViewById(R.id.layout_changePwd);
        layoutCleanCache = (LinearLayout) view.findViewById(R.id.layout_cleanCache);
        layoutAbout = (LinearLayout) view.findViewById(R.id.layout_about);

        tvLogOut = (TextView) view.findViewById(R.id.tv_logout);

        return view;
    }

    @Override
    protected void initData() {
        if(SharedHelper.getBoolean(SharedHelper.LOGIN,false)){
            _id = Integer.parseInt(SharedHelper.getString(SharedHelper.ID,"-1"));
            Logger.i("fragment中获取的_id:"+_id);
        }
        dbManager = DbHelper.getInstance().getDbManger();
        if(_id !=-1){
            refreshViewById(_id);
        }
    }

    @Override
    protected void setListeners() {
        tvLogOut.setOnClickListener(this);
        layoutUserInfo.setOnClickListener(this);
        layoutAbout.setOnClickListener(this);

    }


    /**
     * 根据用户Id刷新界面
     * @param _id
     */
    public void refreshViewById(int _id){
        try {
            this._id = _id;
            UserInfo userInfo  = dbManager.selector(UserInfo.class).where("_id", "=", _id).findFirst();
            tvUserName.setText("昵称:"+userInfo.getUserName());
            tvUserRank.setText("等级：VIP"+userInfo.getUserRank());
            Logger.e("地址："+userInfo.getUserIcon());
            ivUserIcon.setImageBitmap(BitmapFactory.decodeFile(userInfo.getUserIcon()));

        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_logout:
                if(callBack != null) {
                    SharedHelper.saveBoolean(SharedHelper.LOGIN, false);
                    SharedHelper.saveString(SharedHelper.ID, "-1");
                    callBack.onLogOut();
                }
                break;


            case R.id.layout_userInfo:
                Intent intent = new Intent(mActivity, UserSetActivity.class);
                intent.putExtra("_id",_id);
                mActivity.startActivityForResult(intent,2000);
                break;

            case R.id.layout_about:
                Intent intentabout = new Intent(mActivity, AboutActivity.class);
                mActivity.startActivity(intentabout);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.e("fragment中onActivityResult");
        refreshViewById(_id);
        super.onActivityResult(requestCode, resultCode, data);

    }

    public interface MineCallBack{
        void onLogOut();
    }
}
