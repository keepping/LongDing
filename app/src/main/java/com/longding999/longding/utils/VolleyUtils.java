package com.longding999.longding.utils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/10 11:34
 * Desc: Volley操作的工具类
 * *****************************************************************
 */
public class VolleyUtils {

    private static VolleyUtils volleyUtils;

    private RequestQueue mQueue;
    private ImageLoader mLoader;
    private ImageLoader.ImageCache mCache;

    public static RequestQueue getmQueue() {
        return getInstence().mQueue;
    }

    public static ImageLoader getmLoader() {
        return getInstence().mLoader;
    }

    public static ImageLoader.ImageCache getmCache() {
        return getInstence().mCache;
    }

    public static VolleyUtils getInstence(){
        if(volleyUtils == null){
            synchronized (VolleyUtils.class){
                if(volleyUtils == null){
                    volleyUtils = new VolleyUtils();
                }
            }
        }
        return volleyUtils;
    }

    private VolleyUtils(){
        mQueue = Volley.newRequestQueue(MyApplication.mContext);
        mCache = new MyImageCache();
        mLoader = new ImageLoader(mQueue,mCache);
    }

//    public static void LoadImage(ImageView view, String url){
//        ImageLoader loader = getmLoader();
//        ImageLoader.ImageListener imageListener = loader.getImageListener(view, R.mipmap.image_default, R.mipmap.image_default);
//        loader.get(url,imageListener);
//    }
}
