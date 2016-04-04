package com.longding999.longding.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/4 10:39
 * Desc: 重写ListView 自动计算Item项高度
 * *****************************************************************
 */
public class CalculHeightListView extends ListView {

    public CalculHeightListView(Context context) {
        super(context);
    }
    public CalculHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CalculHeightListView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
