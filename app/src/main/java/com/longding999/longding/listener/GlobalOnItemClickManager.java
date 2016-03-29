package com.longding999.longding.listener;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dss886 on 15/9/22.
 */
public class GlobalOnItemClickManager {

    private static GlobalOnItemClickManager instance;
    private EditText mEditText;
    private Context mContext;
    private AssetManager assetManager;
    private Bitmap bitmap;

    public static GlobalOnItemClickManager getInstance() {
        if (instance == null) {
            instance = new GlobalOnItemClickManager();
        }
        return instance;
    }

    public void attachToEditText(Context context,EditText editText) {
        mContext = context;
        mEditText = editText;

        assetManager = context.getAssets();

    }

    public AdapterView.OnItemClickListener getOnItemClickListener(final int emojiType,final  int page) {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringBuilder sb = new StringBuilder();
                switch (emojiType) {
                    case 0:
//                        String path = "file:///android_asset/ems/emoji_1f6" +position + ".png";

                        try {
                            InputStream is = assetManager.open("emoji_1f6" + (page*21+position) + ".png");
                            bitmap = BitmapFactory.decodeStream(is);
                            Log.e("TEST",bitmap.getWidth()+"");
                            //  根据Bitmap对象创建ImageSpan对象
                            ImageSpan imageSpan = new ImageSpan(mContext, bitmap);
                            //  创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
                            SpannableString spannableString = new SpannableString("emoji_1f6");
                            //  用ImageSpan对象替换face
                            spannableString.setSpan(imageSpan, 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            //  将随机获得的图像追加到EditText控件的最后
                            mEditText.append(spannableString);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                        break;
                    case 1:
                        sb.append("[ema").append(String.valueOf(position)).append("]");
                        break;
                    case 2:
                        sb.append("[emb").append(String.valueOf(position)).append("]");
                        break;
                    case 3:
                        sb.append("[emc").append(String.valueOf(position)).append("]");
                        break;
                }
            }
        };
    }
}
