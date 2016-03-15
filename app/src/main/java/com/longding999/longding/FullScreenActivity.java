package com.longding999.longding;

import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gensee.common.ServiceType;
import com.gensee.entity.InitParam;
import com.gensee.entity.UserInfo;
import com.gensee.player.OnPlayListener;
import com.gensee.player.Player;
import com.gensee.view.GSVideoView;
import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/14 13:16
 * Desc: 全屏播放视频
 * *****************************************************************
 */
public class FullScreenActivity extends BasicActivity implements OnPlayListener,View.OnClickListener{
    private GSVideoView mGSVideoView;

    private InitParam initParam;
    private Player mPlayer;
    @Override
    protected void bindView() {

        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen);

    }

    @Override
    protected void getIntents() {

    }

    @Override
    protected void initViews() {
        mGSVideoView = (GSVideoView) findViewById(R.id.gsVideoView);

    }

    @Override
    protected void setListeners() {
        mGSVideoView.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initParam = new InitParam();
        mPlayer = new Player();
        initPlayer();
    }


    /**
     * 初始化播放器
     */
    public void initPlayer() {
        initParam.setDomain(Constant.VIDEO_DOMAIN);
        initParam.setNumber(Constant.VIDEO_NUMBER);
        initParam.setServiceType(ServiceType.ST_CASTLINE);
        mPlayer.join(FullScreenActivity.this, initParam, this);
        mPlayer.setGSVideoView(mGSVideoView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gsVideoView:
                doubleClick();
                break;

            default:
                break;
        }
    }

    /**
     * 响应双击事件
     */
    List<Long> times = new ArrayList<>();
    public void doubleClick(){
        times.add(SystemClock.uptimeMillis());
        if (times.size() == 2) {
            if (times.get(times.size()-1)-times.get(0) < 500) {
                times.clear();
                onBackPressed();
            } else {
                times.remove(0);
            }
        }
    }


    @Override
    public void onJoin(int i) {

    }

    @Override
    public void onUserJoin(UserInfo userInfo) {

    }

    @Override
    public void onUserLeave(UserInfo userInfo) {

    }

    @Override
    public void onUserUpdate(UserInfo userInfo) {

    }

    @Override
    public void onRosterTotal(int i) {

    }

    @Override
    public void onReconnecting() {

    }

    @Override
    public void onLeave(int i) {

    }

    @Override
    public void onCaching(boolean b) {

    }

    @Override
    public void onErr(int i) {

    }

    @Override
    public void onDocSwitch(int i, String s) {

    }

    @Override
    public void onVideoBegin() {

    }

    @Override
    public void onVideoEnd() {

    }

    @Override
    public void onAudioLevel(int i) {

    }

    @Override
    public void onPublish(boolean b) {

    }

    @Override
    public void onSubject(String s) {

    }

    @Override
    public void onPageSize(int i, int i1, int i2) {

    }

    @Override
    public void onPublicMsg(long l, String s) {

    }


}
