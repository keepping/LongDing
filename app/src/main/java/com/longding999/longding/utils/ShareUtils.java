package com.longding999.longding.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.longding999.longding.MainActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/24 18:21
 * Desc: 三方分享工具类
 * *****************************************************************
 */
public class ShareUtils {
    private Context mContext;
    private UMShareAPI mShareAPI = null;


    public ShareUtils(Context context) {
        mContext = context;
        mShareAPI = UMShareAPI.get(context);
    }

    public void doOauthVerify(SHARE_MEDIA shareMedia) {
        mShareAPI.doOauthVerify((Activity) mContext, shareMedia, umAuthListener);
    }


    public void ShareAction(SHARE_MEDIA shareMedia, String text, UMImage image, String url) {
        new ShareAction((Activity) mContext).setPlatform(shareMedia).setCallback(umShareListener)
                .withText(text)
                .withMedia(image)
                .withTargetUrl(url)
                .share();
    }
    public void ShareAction(SHARE_MEDIA shareMedia, String text, UMImage image) {
        new ShareAction((Activity) mContext).setPlatform(shareMedia).setCallback(umShareListener)
                .withText(text)
                .withMedia(image)
                .share();
    }

    public void ShareAction(SHARE_MEDIA shareMedia, String text, String url) {
        new ShareAction((Activity) mContext).setPlatform(shareMedia).setCallback(umShareListener)
                .withText(text)
                .withTargetUrl(url)
                .share();
    }




    /**
     * 分享回调接口
     */
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {

            Toast.makeText(mContext, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mContext, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mContext, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    /**
     * 授权回调接口
     */
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(mContext, "Authorize succeed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(mContext, "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mContext, "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
}
