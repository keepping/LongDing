package com.longding999.longding.basic;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/10 11:10
 * Desc: 抽取ListView适配器的基类
 * *****************************************************************
 */
public abstract class BasicListAdapter<T> extends BaseAdapter {
    protected List<T> mList;
    protected LayoutInflater mInflater;

    public BasicListAdapter(List<T> mList , Context mContext) {
        this.mList = mList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
