package com.longding999.longding;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.utils.SDCardUtils;
import com.longding999.longding.utils.SharedHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/25 14:41
 * Desc: 第一次启动页
 * *****************************************************************
 */
public class SplashActivity extends BasicActivity {
    private ViewPager mViewPager;
    private Button btnWElcome;
    private List<View> mList;
    private int[] imagIds = new int[]{R.mipmap.guidepage1,R.mipmap.guidepage2,R.mipmap.guidepage3};

    @Override
    protected void bindView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(SharedHelper.getBoolean(SharedHelper.ISFIRSTIN,false)){
            Intent intent = new Intent(SplashActivity.this,WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void getIntents() {

    }

    @Override
    protected void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.splash_ViewPager);
        btnWElcome = (Button) findViewById(R.id.btn_welcome);
        btnWElcome.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void setListeners() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if(position == 2){
                    btnWElcome.setVisibility(View.VISIBLE);
                    translationY(btnWElcome,200,0);
                }else{
                    btnWElcome.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnWElcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedHelper.saveBoolean(SharedHelper.ISFIRSTIN,true);
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < imagIds.length; i++) {
            ImageView imgView = new ImageView(this);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            imgView.setImageResource(imagIds[i]);
            mList.add(imgView);
        }
        mViewPager.setAdapter(new MyAdapter(mList));

        AssetManager assetManager = getAssets();
        File cacheDir = getApplicationContext().getCacheDir();
        try {
            InputStream is = assetManager.open("user.jpg");
            boolean icon = SDCardUtils.writeAssets(cacheDir.getAbsolutePath()+"/icon", "user.jpg", is);
//            Logger.e("保存图片："+icon);
//            Logger.e("保存地址："+cacheDir.getAbsolutePath()+"/icon/user.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void translationY(View view, float y1, float y2) {
        ObjectAnimator.ofFloat(view, "translationY", y1, y2)
                .setDuration(500)
                .start();
    }

    //自定义适配器
    class MyAdapter extends PagerAdapter {
        private List<View> list = null;

        public MyAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

    }
}
