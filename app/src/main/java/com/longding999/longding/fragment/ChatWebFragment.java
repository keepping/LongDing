package com.longding999.longding.fragment;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicFragment;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/6 9:53
 * Desc: 聊天页面
 * *****************************************************************
 */
public class ChatWebFragment extends BasicFragment {

    private WebView mWebView;
    private WebSettings mWebSettings;
    private URL murl;

    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_chatweb, null);
        mWebView = (WebView) view.findViewById(R.id.webView);
        return view;
    }

    @Override
    protected void initData() {

        mWebView.loadUrl("http://live.longding999.com/zhibo/newright");
        mWebSettings = mWebView.getSettings();
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mWebSettings.setJavaScriptEnabled(true);


    }

    @Override
    protected void setListeners() {

    }

}
