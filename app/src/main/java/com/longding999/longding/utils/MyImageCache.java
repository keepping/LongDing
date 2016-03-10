package com.longding999.longding.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Author:LCM
 * Date:2016/3/10 11:40
 * Desc:
 *      Volley图片缓存
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
public class MyImageCache implements ImageLoader.ImageCache{

    private LruCache<String,Bitmap> mCache;

    public MyImageCache() {
        long maxSize = Runtime.getRuntime().maxMemory() / 8;

        mCache = new LruCache<String,Bitmap>((int) maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

    }

    @Override
    public Bitmap getBitmap(String s) {
        return mCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        mCache.put(s,bitmap);
    }
}
