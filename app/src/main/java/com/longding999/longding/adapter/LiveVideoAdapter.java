package com.longding999.longding.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.basic.BasicListAdapter;
import com.longding999.longding.bean.LiveVideoInfo;

import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/3/31 18:25
 * Desc:
 * *****************************************************************
 */
public class LiveVideoAdapter extends BasicListAdapter<LiveVideoInfo> {

    public LiveVideoAdapter(List<LiveVideoInfo> mList, Context mContext) {
        super(mList, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    class ViewHolder{
        private TextView tvLiveCategory;
        private TextView tvLiveTeacher;
        private TextView tvLiveBrif;
        private TextView tvLiveTime;
        private ImageView  ivLiveScreenShot;

        private View viwe;

        public ViewHolder(View viwe) {
            this.viwe = viwe;
        }

        public TextView getTvLiveCategory() {

            return tvLiveCategory;
        }

        public TextView getTvLiveTeacher() {
            return tvLiveTeacher;
        }

        public TextView getTvLiveBrif() {
            return tvLiveBrif;
        }

        public TextView getTvLiveTime() {
            return tvLiveTime;
        }

        public ImageView getIvLiveScreenShot() {
            return ivLiveScreenShot;
        }
    }
}
