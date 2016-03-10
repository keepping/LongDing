package com.longding999.longding.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gensee.common.ServiceType;
import com.gensee.entity.InitParam;
import com.gensee.entity.UserInfo;
import com.gensee.player.OnPlayListener;
import com.gensee.player.Player;
import com.gensee.view.GSVideoView;
import com.longding999.longding.R;
import com.longding999.longding.adapter.MyPagerAdapter;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/9 13:42
 * Desc: 视频直播碎片
 * *****************************************************************
 */
public class VideoLiveFragment extends BasicFragment implements OnPlayListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> mFragments;
    private MyPagerAdapter mAdapter;
    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    private GSVideoView mGSVideoView;
    private Player mPlayer;

    private  InitParam initParam;
    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_videolive, null);
        mGSVideoView = (GSVideoView) view.findViewById(R.id.gsVideoView);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tvLeft = (TextView) view.findViewById(R.id.tv_left);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        imageLeft = (ImageView) view.findViewById(R.id.image_left);
        tvTitle.setText("视频直播");
        tvRight.setVisibility(View.GONE);
        tvLeft.setVisibility(View.GONE);
        imageLeft.setVisibility(View.GONE);
        return view;
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new ChatFragment());
        mFragments.add(new TeacherFragment());
        mFragments.add(new DiurnalFragment());
        mFragments.add(new DisclaimerFragment());
        mAdapter = new MyPagerAdapter(getChildFragmentManager(),mFragments);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);


        mPlayer = new Player();
        initParam = new InitParam();
        initPlayer();
    }

    private void initPlayer() {
        initParam.setDomain(Constant.VIDEO_DOMAIN);
        initParam.setNumber(Constant.VIDEO_NUMBER);
        initParam.setServiceType(ServiceType.ST_CASTLINE);
        mPlayer.join(getActivity(), initParam, this);
        mPlayer.setGSVideoView(mGSVideoView);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){
            mPlayer.leave();
//            mPlayer.release(getActivity());
        }else{
            initPlayer();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    protected void setListeners() {

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

    @Override
    public void onPause() {
        mPlayer.leave();
        mPlayer.release(getActivity());
        super.onPause();
    }
}