package com.longding999.longding;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.gensee.common.ServiceType;
import com.gensee.entity.InitParam;
import com.gensee.entity.UserInfo;
import com.gensee.player.OnPlayListener;
import com.gensee.player.Player;
import com.gensee.view.GSVideoView;
import com.longding999.longding.adapter.MyPagerAdapter;
import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.basic.BasicAppCompatActivity;
import com.longding999.longding.basic.BasicFragmentActivity;
import com.longding999.longding.fragment.ChatFragment;
import com.longding999.longding.fragment.ClassicFragment;
import com.longding999.longding.fragment.DisclaimerFragment;
import com.longding999.longding.fragment.DiurnalFragment;
import com.longding999.longding.fragment.MokeyFragment;
import com.longding999.longding.fragment.TeacherFragment;
import com.longding999.longding.listener.GlobalOnItemClickManager;
import com.longding999.longding.utils.Constant;
import com.longding999.longding.utils.ShareUtils;
import com.longding999.longding.view.EmotionInputDetector;
import com.longding999.longding.view.ShareView;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/22 16:50
 * Desc: 视频直播页面
 * *****************************************************************
 */
public class VideoLiveActivity extends BasicFragmentActivity implements OnPlayListener, View.OnClickListener {
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

    private ShareUtils mShareUtils;
    private UMImage shareImage;
    private String  shareUrl;


    private LinearLayout layoutReply;
    private LinearLayout layoutConent;
    private EmotionInputDetector mDetector;
    private ImageView imageEmotionButton;
    private EditText edtText;
    private Button btnReply;
    private LinearLayout emotionLayout;

    private Button btnEmotionClassic, btnEmotionMokey;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private int currentTabIndex = 0;
    private List<Fragment> fragmentList;

    private InputMethodManager mInputManager;

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

        layoutReply = (LinearLayout) findViewById(R.id.layout_reply);
        layoutConent = (LinearLayout) findViewById(R.id.layout_content);

        imageEmotionButton = (ImageView) findViewById(R.id.iv_emoji_button);
        edtText = (EditText) findViewById(R.id.edt_text);
        btnReply = (Button) findViewById(R.id.btn_reply);
        emotionLayout = (LinearLayout) findViewById(R.id.emotion_layout);

        btnEmotionClassic = (Button) findViewById(R.id.btn_emotion_classic);
        btnEmotionMokey = (Button) findViewById(R.id.btn_emotion_monkey);

        mDetector = EmotionInputDetector.with(VideoLiveActivity.this)
                .bindToEditText(edtText)
                .bindToEmotionButton(imageEmotionButton)
                .bindToContent(layoutConent)
                .setEmotionView(emotionLayout)
                .build();


    }

    @Override
    protected void setListeners() {
        mGSVideoView.setOnClickListener(this);
        ivVideoVideo.setOnClickListener(this);
        ivVideoSound.setOnClickListener(this);
        ivVideoShare.setOnClickListener(this);
        ivVideoMagnify.setOnClickListener(this);
        ivVideoBack.setOnClickListener(this);
        btnEmotionClassic.setOnClickListener(this);
        btnEmotionMokey.setOnClickListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position ==0){
                    layoutReply.setVisibility(View.VISIBLE);
                }else{
                    layoutReply.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        mShareUtils = new ShareUtils(VideoLiveActivity.this);
        shareImage = new UMImage(VideoLiveActivity.this,"http://www.umeng.com/images/pic/social/integrated_3.png");
        shareUrl = "http://liveqianlong.com";
        initPlayer();
        initPopWindow();


        fm = getSupportFragmentManager();
        fragmentList = new ArrayList<>();
        fragmentList.add(new ClassicFragment());
        fragmentList.add(new MokeyFragment());
        fm.beginTransaction().add(R.id.framelayout, fragmentList.get(currentTabIndex)).commit();
        GlobalOnItemClickManager globalOnItemClickListener = GlobalOnItemClickManager.getInstance();
        globalOnItemClickListener.attachToEditText(this, edtText);
        mInputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
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
                showPop(rootLayout, Gravity.TOP,0,0);
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
                if(shareWindow.isShowing()){
                    shareWindow.dismiss();
                }
                mShareUtils.ShareAction(SHARE_MEDIA.QQ,"龙鼎直播",shareUrl);
                break;

            //分享到QQ空间
            case R.id.share_qzone:
                if(shareWindow.isShowing()){
                    shareWindow.dismiss();
                }
                mShareUtils.ShareAction(SHARE_MEDIA.QZONE,"龙鼎直播",shareUrl);
                break;

            //分享到新浪微博
            case R.id.share_sina:
                if(shareWindow.isShowing()){
                    shareWindow.dismiss();
                }
//                mShareUtils.ShareAction(SHARE_MEDIA.SINA,"龙鼎直播",shareUrl);
                shortToast("正在审核");
                break;

            //分享到短信
            case R.id.share_sns:
                if(shareWindow.isShowing()){
                    shareWindow.dismiss();
                }
                mShareUtils.ShareAction(SHARE_MEDIA.SMS,"龙鼎直播",shareUrl);
                break;

            //分享到微信
            case R.id.share_wechat:
                if(shareWindow.isShowing()){
                    shareWindow.dismiss();
                }
                mShareUtils.ShareAction(SHARE_MEDIA.WEIXIN,"龙鼎直播",shareUrl);
                break;

            //分享到微信朋友圈
            case R.id.share_wcpyq:
                if(shareWindow.isShowing()){
                    shareWindow.dismiss();
                }
                mShareUtils.ShareAction(SHARE_MEDIA.WEIXIN_CIRCLE,"龙鼎直播",shareUrl);
                break;


            case R.id.btn_emotion_classic:
                showFragment(0);
                break;

            case R.id.btn_emotion_monkey:
                showFragment(1);
                break;

            default:
                break;
        }
    }
    /**
     * 根据选中radiobutton下表显示fragment
     *
     * @param targetTabIndex
     */
    public void showFragment(int targetTabIndex) {
        ft = fm.beginTransaction();
        if (targetTabIndex != currentTabIndex) {
            Fragment currentFragment = fragmentList.get(currentTabIndex);
            Fragment targetFragment = fragmentList.get(targetTabIndex);

            if (!targetFragment.isAdded()) {
                ft.add(R.id.framelayout, targetFragment).hide(currentFragment).commitAllowingStateLoss();
            } else {
                ft.show(targetFragment).hide(currentFragment).commitAllowingStateLoss();
            }

            currentTabIndex = targetTabIndex;

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get( this ).onActivityResult( requestCode, resultCode, data);

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
       // rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
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
