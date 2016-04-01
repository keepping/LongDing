package com.longding999.longding.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicListAdapter;
import com.longding999.longding.bean.ScheduleDateInfo;

import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/1 16:16
 * Desc:
 * *****************************************************************
 */
public class ScheduleDateAdapter extends BasicListAdapter<ScheduleDateInfo> {
    public ScheduleDateAdapter(List<ScheduleDateInfo> mList, Context mContext) {
        super(mList, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_schedule_date,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ScheduleDateInfo scheduleDateInfo = mList.get(position);
        holder.getTvTime().setText(scheduleDateInfo.getTime());
        holder.getTvTeacher().setText(scheduleDateInfo.getTeacher());
        return convertView;
    }



    class  ViewHolder{
        private TextView tvTime;
        private TextView tvTeacher;

        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        public TextView getTvTime() {
            if(tvTime == null){
                tvTime = (TextView) view.findViewById(R.id.tv_time);
            }
            return tvTime;
        }

        public TextView getTvTeacher() {
            if(tvTeacher == null){
                tvTeacher = (TextView) view.findViewById(R.id.tv_teacher);
            }
            return tvTeacher;
        }
    }
}
