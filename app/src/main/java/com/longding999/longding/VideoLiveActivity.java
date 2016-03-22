package com.longding999.longding;

import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/22 16:50
 * Desc:
 * *****************************************************************
 */
public class VideoLiveActivity extends BasicActivity implements OnPlayListener,View.OnClickListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> mFragments;
    private MyPagerAdapter mAdapter;
    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    private GSVideoView mGSVideoView;
    private Player mPlayer;

    private InitParam initParam;
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
        mGSVideoView = (GSVideoView)findViewById(R.id.gsVideoView);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tvLeft = (TextView)findViewById(R.id.tv_left);
        tvRight = (TextView)findViewById(R.id.tv_right);
        tvTitle = (TextView)findViewById(R.id.tv_title);
        imageLeft = (ImageView)findViewById(R.id.image_left);
        tvTitle.setText("视频直播");
        tvRight.setVisibility(View.GONE);
        tvLeft.setVisibility(View.GONE);
        imageLeft.setVisibility(View.GONE);

    }

    @Override
    protected void setListeners() {
        mGSVideoView.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new ChatFragment());
        mFragments.add(new TeacherFragment());
        mFragments.add(new DiurnalFragment());
        mFragments.add(new DisclaimerFragment());
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(),mFragments);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);


        mPlayer = new Player();
        initParam = new InitParam();
        initPlayer();

    }

    /**
     * 初始化播放器
     */
    private void initPlayer() {
        initParam.setDomain(Constant.VIDEO_DOMAIN);
        initParam.setNumber(Constant.VIDEO_NUMBER);
        initParam.setServiceType(ServiceType.ST_CASTLINE);
        mPlayer.join(VideoLiveActivity.this, initParam, this);
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
    public void doubleClick(){
        times.add(SystemClock.uptimeMillis());
        if (times.size() == 2) {
            if (times.get(times.size()-1)-times.get(0) < 500) {
                times.clear();
                Intent intent =new Intent(VideoLiveActivity.this, FullScreenActivity.class);
                startActivity(intent);
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
