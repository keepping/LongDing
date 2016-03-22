package com.longding999.longding.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.VideoLiveActivity;
import com.longding999.longding.adapter.ImagePageAdapter;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/22 11:47
 * Desc: 第一个Tab导航页
 * *****************************************************************
 */
public class NavigationFragment extends BasicFragment implements View.OnClickListener{
    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    private ImageView ivTextLiew,ivVipLive,ivVideoLive;

    private ViewPager mViewPager;
    private ImagePageAdapter mAdapter;
    private List<ImageView> imageViews;
    private int currentItem = Integer.MAX_VALUE/2;

    /**
     * 请求更新显示的View。
     */
    protected static final int MSG_UPDATE_IMAGE  = 1;
    /**
     * 请求暂停轮播。
     */
    protected static final int MSG_KEEP_SILENT   = 2;
    /**
     * 请求恢复轮播。
     */
    protected static final int MSG_BREAK_SILENT  = 3;
    /**
     * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
     * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
     * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
     */
    protected static final int MSG_PAGE_CHANGED  = 4;

    //轮播间隔时间
    protected static final long MSG_DELAY = 3000;



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    mViewPager.setCurrentItem(currentItem);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_navigation, null);
        tvLeft = (TextView) view.findViewById(R.id.tv_left);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        imageLeft = (ImageView) view.findViewById(R.id.image_left);
        tvTitle.setText("钦龙");
        tvRight.setVisibility(View.GONE);
        tvLeft.setVisibility(View.GONE);
        imageLeft.setVisibility(View.GONE);

        ivTextLiew = (ImageView) view.findViewById(R.id.iv_textLive);
        ivVipLive = (ImageView) view.findViewById(R.id.iv_vipLive);
        ivVideoLive = (ImageView) view.findViewById(R.id.iv_videoLive);

        mViewPager = (ViewPager) view.findViewById(R.id.banner_ViewPager);

        return view;
    }

    @Override
    protected void initData() {
        imageViews = new ArrayList<>();

        ImageView imageView1 = new ImageView(mActivity);
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView1.setImageResource(R.mipmap.root1_0);

        ImageView imageView2 = new ImageView(mActivity);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setImageResource(R.mipmap.root1_1);

        ImageView imageView3 = new ImageView(mActivity);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView3.setImageResource(R.mipmap.root1_2);

        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        mAdapter = new ImagePageAdapter(imageViews);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setCurrentItem(currentItem);//默认在中间，使用户看不到边界
        //开始轮播效果
        handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);



    }

    @Override
    protected void setListeners() {
        ivVideoLive.setOnClickListener(this);
        ivVipLive.setOnClickListener(this);
        ivTextLiew.setOnClickListener(this);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //配合Adapter的currentItem字段进行设置。
            @Override
            public void onPageSelected(int arg0) {
                Logger.e("PageSelected");
                handler.sendMessage(Message.obtain(handler, MSG_PAGE_CHANGED, arg0, 0));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            //覆写该方法实现轮播效果的暂停和恢复
            @Override
            public void onPageScrollStateChanged(int arg0) {
                switch (arg0) {
                    case ViewPager.SCROLL_STATE_DRAGGING:

                        handler.sendEmptyMessage(MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:

                        handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_videoLive:
                Intent videoIntent = new Intent(mActivity, VideoLiveActivity.class);
                mActivity.startActivity(videoIntent);
                break;

            case R.id.iv_vipLive:
                shortToast("即将开启");
                break;

            case R.id.iv_textLive:
                shortToast("即将开启");
                break;

        }
    }
}
