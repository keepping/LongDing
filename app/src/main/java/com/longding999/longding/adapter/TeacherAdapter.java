package com.longding999.longding.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicListAdapter;
import com.longding999.longding.bean.TeacherBean;
import com.longding999.longding.bean.TeacherInfo;

import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/10 11:16
 * Desc: 老师页面适配器
 * *****************************************************************
 */
public class TeacherAdapter extends BasicListAdapter<TeacherBean> {

    public TeacherAdapter(List<TeacherBean> mList, Context mContext) {
        super(mList, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_teacher_list,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        TeacherBean teacherBean = mList.get(position);
        holder.getTvTeacherName().setText(teacherBean.getNickname());
        holder.getTvTeacherContent().setText(teacherBean.getIntroduce());

        return convertView;
    }

    class ViewHolder{
        private TextView tvTeacherName,tvTeacherContent;
        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        public TextView getTvTeacherName() {
            if(tvTeacherName == null){
                tvTeacherName = (TextView) view.findViewById(R.id.tv_teacherName);
            }
            return tvTeacherName;
        }

        public TextView getTvTeacherContent() {
            if(tvTeacherContent == null){
                tvTeacherContent = (TextView) view.findViewById(R.id.tv_teacherContent);
            }
            return tvTeacherContent;
        }
    }

}
