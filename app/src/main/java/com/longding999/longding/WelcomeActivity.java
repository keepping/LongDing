package com.longding999.longding;


import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.utils.SDCardUtils;
import com.longding999.longding.view.GifView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/15 13:31
 * Desc: 启动页
 * *****************************************************************
 */
public class WelcomeActivity extends BasicActivity {

    private GifView mGifView;
    private ImageView mImageView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    mImageView.setVisibility(View.GONE);
                    mGifView.setVisibility(View.VISIBLE);
                    mGifView.setPaused(false);
                    break;

                case 2:
                    mGifView.setPaused(true);
                    mGifView.setEnabled(true);
                    break;

                default:
                    break;
            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void bindView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);



    }

    @Override
    protected void getIntents() {

    }

    @Override
    protected void initViews() {
        mImageView = (ImageView) findViewById(R.id.imageView);
        mGifView = (GifView) findViewById(R.id.gifView);
    }

    @Override
    protected void setListeners() {
        mGifView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               shortToast("dianji");
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        mGifView.setEnabled(false);
        mGifView.setVisibility(View.GONE);
        mGifView.setPaused(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(1);

                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(2);
            }
        }).start();
    }
}
