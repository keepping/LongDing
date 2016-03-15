package com.longding999.longding;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.longding999.longding.basic.BasicActivity;



/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/15 13:31
 * Desc:
 * *****************************************************************
 */
public class WelcomeActivity extends BasicActivity {



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

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

    }

    @Override
    protected void setListeners() {
//        mWebView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

    }

    @Override
    protected void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
}
