package com.longding999.longding.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longding999.longding.R;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/24 16:17
 * Desc:
 * *****************************************************************
 */
public class ShareView extends LinearLayout {

    private TextView tvdesc;
    private ImageView ivIvon;

    private Bitmap mSrc;
    private String mText;

    public ShareView(Context context) {
        this(context, null);
    }

    public ShareView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShareView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ShareView, defStyleAttr, defStyleAttr);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.ShareView_src:
                    mSrc = BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(attr,0));
                    break;

                case R.styleable.ShareView_text:
                    mText = typedArray.getString(attr);
                    break;

                default:
                    break;
            }
        }


        initViews(context);
    }

    private void initViews(Context context) {
        View.inflate(context, R.layout.share_icon_layout, this);
        tvdesc = (TextView) findViewById(R.id.tv_desc);
        ivIvon = (ImageView) findViewById(R.id.iv_icon);

        if(mSrc != null){
            ivIvon.setImageBitmap(mSrc);
        }

        if (mText != null){
            tvdesc.setText(mText);
        }
    }

    public void setImage(Bitmap bitmap){
        ivIvon.setImageBitmap(bitmap);
    }

    public void setdesc(String text){
        tvdesc.setText(text);
    }
}
