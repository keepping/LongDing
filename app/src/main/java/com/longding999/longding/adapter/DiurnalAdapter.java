package com.longding999.longding.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicListAdapter;
import com.longding999.longding.bean.DiurnalInfo;

import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/10 15:00
 * Desc: 日刊界面适配器
 * *****************************************************************
 */
public class DiurnalAdapter extends BasicListAdapter<DiurnalInfo> {

    public DiurnalAdapter(List<DiurnalInfo> mList, Context mContext) {
        super(mList, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_diurnal_list,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        DiurnalInfo diurnalInfo = mList.get(position);
        holder.getTvDiurnalTitle().setText(diurnalInfo.getDiurnaltitle());
        holder.getTvDiurnalContent().setText(diurnalInfo.getDiurnalContent());
        return convertView;
    }

    class ViewHolder{
        private TextView tvDiurnalTitle,tvDiurnalContent;
        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        public TextView getTvDiurnalTitle() {
            if(tvDiurnalTitle == null){
                tvDiurnalTitle = (TextView) view.findViewById(R.id.tv_diurnaltitle);
            }
            return tvDiurnalTitle;
        }

        public TextView getTvDiurnalContent() {
            if(tvDiurnalContent == null){
                tvDiurnalContent = (TextView) view.findViewById(R.id.tv_diurnalcontent);
            }
            return tvDiurnalContent;
        }
    }
}
