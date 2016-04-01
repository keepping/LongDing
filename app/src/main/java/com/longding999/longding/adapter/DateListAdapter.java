package com.longding999.longding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.longding999.longding.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/1 17:01
 * Desc:
 * *****************************************************************
 */
public class DateListAdapter extends BaseAdapter {
    private List<String> mList;
    private LayoutInflater mInflater;

    public DateListAdapter(Context context){
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = new ArrayList<>();
        mList.add("3月28日  星期一");
        mList.add("3月29日  星期二");
        mList.add("3月30日  星期三");
        mList.add("3月31日  星期四");
        mList.add("4月1日  星期五");
        mList.add("4月2日  星期六");
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item_date_listview,null);
        }
        TextView tvDate = (TextView) convertView.findViewById(R.id.tv_date);
        tvDate.setText(mList.get(position));
        return convertView;
    }
}
