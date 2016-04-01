package com.longding999.longding.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicListAdapter;
import com.longding999.longding.bean.LiveInfo;

import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/1 10:11
 * Desc:
 * *****************************************************************
 */
public class LiveTextAdapter extends BasicListAdapter<LiveInfo> {

    public LiveTextAdapter(List<LiveInfo> mList, Context mContext) {
        super(mList, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_live_text,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        LiveInfo liveInfo = mList.get(position);
        holder.getTvLiveBrif().setText(liveInfo.getLiveBrif());
        holder.getTvLiveTeacher().setText(liveInfo.getLiveTeacher());
        holder.getTvLiveTitle().setText(liveInfo.getLiveTitle());

        return convertView;
    }

    class ViewHolder{
        private TextView tvLiveTeacher;
        private TextView tvLiveBrif;
        private TextView tvLiveTitle;
        private ImageView ivLiveScreenShot;

        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }


        public TextView getTvLiveTitle() {
            if(tvLiveTitle == null){
                tvLiveTitle = (TextView) view.findViewById(R.id.tv_live_title);
            }
            return tvLiveTitle;
        }

        public TextView getTvLiveTeacher() {
            if(tvLiveTeacher == null){
                tvLiveTeacher = (TextView) view.findViewById(R.id.tv_live_teacher);
            }
            return tvLiveTeacher;
        }

        public TextView getTvLiveBrif() {
            if(tvLiveBrif == null){
                tvLiveBrif = (TextView) view.findViewById(R.id.tv_live_brief);
            }
            return tvLiveBrif;
        }


        public ImageView getIvLiveScreenShot() {
            if(ivLiveScreenShot == null){
                ivLiveScreenShot = (ImageView) view.findViewById(R.id.iv_live_screenshot);
            }
            return ivLiveScreenShot;
        }
    }
}
