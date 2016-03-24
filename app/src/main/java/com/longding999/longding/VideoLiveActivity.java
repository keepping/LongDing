package com.longding999.longding;

import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.gensee.common.ServiceType;
import com.gensee.entity.InitParam;
import com.gensee.entity.UserInfo;
import com.gensee.player.OnPlayListener;
import com.gensee.player.Player;
import com.gensee.view.GSVideoView;
import com.longding999.longding.adapter.MyPagerAdapter;
import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.fragment.ChatFragment;
import com.longding999.longding.fragment.DisclaimerFragment;
import com.longding999.longding.fragment.DiurnalFragment;
import com.longding999.longding.fragment.TeacherFragment;
import com.longding999.longding.utils.Constant;
import com.longding999.longding.view.ShareView;

import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/22 16:50
 * Desc: 视频直播页面
 * *****************************************************************
 */
public class VideoLiveActivity extends BasicActivity implements OnPlayListener, View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> mFragments;
    private MyPagerAdapter mAdapter;

    private GSVideoView mGSVideoView;
    private Player mPlayer;
    private LinearLayout linearLayout, rootLayout;

    private InitParam initParam;
    private boolean isLand = false;

    private ImageView ivVideoBack, ivVideoShare, ivVideoVideo, ivVideoSound, ivVideoMagnify;

    private boolean isShowButton = false;
    private boolean isaudio = true;
    private boolean isvideo = true;

    private PopupWindow shareWindow;
    private ShareView shareWechat,shareWcpyq,shareQQ,shareQzone,shareSns,shareSina;
    private ImageView ivShareClose;

    @Override
    protected void bindView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_videolive);

    }

    @Override
    protected void getIntents() {

    }

    @Override
    protected void initViews() {
        mGSVideoView = (GSVideoView) findViewById(R.id.gsVideoView);
        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ivVideoBack = (ImageView) findViewById(R.id.iv_video_back);
        ivVideoMagnify = (ImageView) findViewById(R.id.iv_video_magnify);
        ivVideoShare = (ImageView) findViewById(R.id.iv_video_share);
        ivVideoSound = (ImageView) findViewById(R.id.iv_video_sound);
        ivVideoVideo = (ImageView) findViewById(R.id.iv_video_video);
    }

    @Override
    protected void setListeners() {
        mGSVideoView.setOnClickListener(this);
        ivVideoVideo.setOnClickListener(this);
        ivVideoSound.setOnClickListener(this);
        ivVideoShare.setOnClickListener(this);
        ivVideoMagnify.setOnClickListener(this);
        ivVideoBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new ChatFragment());
        mFragments.add(new TeacherFragment());
        mFragments.add(new DiurnalFragment());
        mFragments.add(new DisclaimerFragment());
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);

        mPlayer = new Player();
        initParam = new InitParam();
        initPlayer();
        initPopWindow();

    }

    /**
     * 初始化播放器
     */
    private void initPlayer() {
        initParam.setDomain(Constant.VIDEO_DOMAIN);
        initParam.setNumber(Constant.VIDEO_NUMBER);
        initParam.setServiceType(ServiceType.ST_CASTLINE);
        mPlayer.join(VideoLiveActivity.this, initParam, this);
        mGSVideoView.setRenderMode(GSVideoView.RenderMode.RM_FILL_XY);
        mPlayer.setGSVideoView(mGSVideoView);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gsVideoView:
                if (!isShowButton) {
                    showVideoButton();
                } else {
                    hideVideButton();

                }
                 doubleClick();
                break;

            case R.id.iv_video_back:
                onBackPressed();
                hideVideButton();
                break;

            case R.id.iv_video_share:
                showPop(rootLayout, Gravity.BOTTOM,0,0);
                hideVideButton();
                break;

            case R.id.iv_video_magnify:
                showInFullScreen();
                hideVideButton();
                break;

            case R.id.iv_video_sound:
                setAudio();
                break;

            case R.id.iv_video_video:
                setVideo();
                break;

            case R.id.iv_shareclose:
                if(shareWindow.isShowing()){
                    shareWindow.dismiss();
                }
                break;

            //分享到qq好友
            case R.id.share_qq:
                shortToast("等待审核");
                break;

            //分享到QQ空间
            case R.id.share_qzone:
                shortToast("等待审核");
                break;

            //分享到新浪微博
            case R.id.share_sina:
                shortToast("等待审核");
                break;

            //分享到短信
            case R.id.share_sns:
                shortToast("等待审核");
                break;

            //分享到微信
            case R.id.share_wechat:
                shortToast("等待审核");
                break;

            //分享到微信朋友圈
            case R.id.share_wcpyq:
                shortToast("等待审核");
                break;

            default:
                break;
        }
    }


    private void setVideo(){
        if(isvideo){
            mPlayer.videoSet(true);
            isvideo = false;
        }else{
            mPlayer.videoSet(false);
            isvideo = true;
        }
    }
    /**
     * 开关视频声音
     */
    private void setAudio(){
        if(isaudio){
            mPlayer.audioSet(true);
            ivVideoSound.setImageResource(R.mipmap.video_sound_closed);
            isaudio = false;
        }else{
            mPlayer.audioSet(false);
            ivVideoSound.setImageResource(R.mipmap.video_sound_open);
            isaudio = true;
        }
    }

    /**
     * 显示视频播放控件
     */
    private void showVideoButton() {
        translationX(ivVideoMagnify, 200, 0);
        translationX(ivVideoShare, 200, 0);
        translationX(ivVideoSound, 200, 0);
        translationX(ivVideoVideo, 200, 0);
        translationX(ivVideoBack, -200, 0);
        isShowButton = true;
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    /**
     * 隐藏视频播放控件
     */
    private void hideVideButton() {
        translationX(ivVideoMagnify, 0, 200);
        translationX(ivVideoShare, 0, 200);
        translationX(ivVideoSound, 0, 200);
        translationX(ivVideoVideo, 0, 200);
        translationX(ivVideoBack, 0, -200);
        isShowButton = false;
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    /**
     * 属性动画，平移x轴
     *
     * @param view
     * @param x1
     * @param x2
     */
    private void translationX(View view, float x1, float x2) {
        ObjectAnimator.ofFloat(view, "translationX", x1, x2)
                .setDuration(500)
                .start();
    }


    private void showInFullScreen() {
        if (!isLand) {
            linearLayout.setVisibility(View.GONE);
            isLand = true;
            ivVideoShare.setVisibility(View.INVISIBLE);
            ivVideoSound.setVisibility(View.INVISIBLE);
            ivVideoVideo.setVisibility(View.INVISIBLE);
            ivVideoMagnify.setVisibility(View.INVISIBLE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
            isLand = false;
            ivVideoShare.setVisibility(View.VISIBLE);
            ivVideoSound.setVisibility(View.VISIBLE);
            ivVideoVideo.setVisibility(View.VISIBLE);
            ivVideoMagnify.setVisibility(View.VISIBLE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initPopWindow(){
        View view = View.inflate(VideoLiveActivity.this, R.layout.popupwindows_share, null);
        shareWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        shareWindow.setAnimationStyle(R.style.popwin_anim_style);
        shareQQ = (ShareView) view.findViewById(R.id.share_qq);
        shareQzone = (ShareView) view.findViewById(R.id.share_qzone);
        shareSina = (ShareView) view.findViewById(R.id.share_sina);
        shareSns = (ShareView) view.findViewById(R.id.share_sns);
        shareWechat = (ShareView) view.findViewById(R.id.share_wechat);
        shareWcpyq = (ShareView) view.findViewById(R.id.share_wcpyq);
        ivShareClose = (ImageView) view.findViewById(R.id.iv_shareclose);

        shareQzone.setOnClickListener(this);
        shareQQ.setOnClickListener(this);
        shareSina.setOnClickListener(this);
        shareSns.setOnClickListener(this);
        shareWechat.setOnClickListener(this);
        shareWcpyq.setOnClickListener(this);
        ivShareClose.setOnClickListener(this);
    }


    /**
     * 显示popWindow
     *
     * */
    public void showPop(View parent,int postion, int x, int y) {
        //设置popwindow显示位置
        shareWindow.showAtLocation(parent, postion, x, y);
        //获取popwindow焦点
        shareWindow.setFocusable(true);
        //设置popwindow如果点击外面区域，便关闭。
        shareWindow.setOutsideTouchable(true);

    }

    @Override
    protected void onPause() {
        mPlayer.leave();
        super.onPause();
    }

    @Override
    protected void onResume() {
        initPlayer();
        super.onResume();
    }

    @Override
    protected void onStop() {
        mPlayer.leave();
        mPlayer.release(this);
        super.onStop();
    }


    /**
     * 响应双击事件
     */
    List<Long> times = new ArrayList<>();

    public void doubleClick() {
        times.add(SystemClock.uptimeMillis());
        if (times.size() == 2) {
            if (times.get(times.size() - 1) - times.get(0) < 500) {
                times.clear();
                showInFullScreen();
            } else {
                times.remove(0);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (isLand) {
            linearLayout.setVisibility(View.VISIBLE);
            isLand = false;
            ivVideoShare.setVisibility(View.VISIBLE);
            ivVideoSound.setVisibility(View.VISIBLE);
            ivVideoVideo.setVisibility(View.VISIBLE);
            ivVideoMagnify.setVisibility(View.VISIBLE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
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
