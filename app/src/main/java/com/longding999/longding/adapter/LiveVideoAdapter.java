package com.longding999.longding.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicListAdapter;
import com.longding999.longding.bean.LiveInfo;
import com.longding999.longding.bean.VideoListBean;

import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/3/31 18:25
 * Desc:
 * *****************************************************************
 */
public class LiveVideoAdapter extends BasicListAdapter<VideoListBean> {

    public LiveVideoAdapter(List<VideoListBean> mList, Context mContext) {
        super(mList, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_live_video,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        VideoListBean videoListBean = mList.get(position);
        holder.getTvLiveBrif().setText(videoListBean.getIntroduce());
        holder.getTvLiveName().setText(videoListBean.getRoomName());
        holder.getTvLiveTime().setText(videoListBean.getBeginDate());


        return convertView;
    }

    class ViewHolder{
        private TextView tvLiveCategory;
        private TextView tvLiveName;
        private TextView tvLiveBrif;
        private TextView tvLiveTime;
        private ImageView  ivLiveScreenShot;

        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        public TextView getTvLiveCategory() {
            if(tvLiveCategory == null){
                tvLiveCategory = (TextView) view.findViewById(R.id.tv_live_categroy);
            }
            return tvLiveCategory;
        }

        public TextView getTvLiveName() {
            if(tvLiveName == null){
                tvLiveName = (TextView) view.findViewById(R.id.tv_live_name);
            }
            return tvLiveName;
        }

        public TextView getTvLiveBrif() {
            if(tvLiveBrif == null){
                tvLiveBrif = (TextView) view.findViewById(R.id.tv_live_brief);
            }
            return tvLiveBrif;
        }

        public TextView getTvLiveTime() {
            if(tvLiveTime == null){
                tvLiveTime = (TextView) view.findViewById(R.id.tv_live_time);
            }
            return tvLiveTime;
        }

        public ImageView getIvLiveScreenShot() {
            if(ivLiveScreenShot == null){
                ivLiveScreenShot = (ImageView) view.findViewById(R.id.iv_live_screenshot);
            }
            return ivLiveScreenShot;
        }
    }
}
