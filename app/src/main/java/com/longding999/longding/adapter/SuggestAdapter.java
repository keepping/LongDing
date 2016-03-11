package com.longding999.longding.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicListAdapter;
import com.longding999.longding.bean.SuggestInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/11 16:05
 * Desc: 专家建议页面适配器
 * *****************************************************************
 */
public class SuggestAdapter extends BasicListAdapter<SuggestInfo> {

    public SuggestAdapter(List<SuggestInfo> mList, Context mContext) {
        super(mList, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_suggest_list,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        SuggestInfo suggestInfo = mList.get(position);
        holder.getTvCreateTime().setText(parseLongToString(suggestInfo.getCreateTime()));
        holder.getTvCreateUserName().setText(suggestInfo.getCreateUserName());
        holder.getTvGoods().setText(suggestInfo.getGoods()+"价格");
        holder.getTvOpenPrice().setText(suggestInfo.getOpenprice());
        holder.getTvCateGory().setText(suggestInfo.getCategory());
        holder.getTvStopProfit().setText(suggestInfo.getStopprofit());
        holder.getTvStopLoss().setText(suggestInfo.getStoploss());

        return convertView;
    }



    class ViewHolder{
        private TextView tvCreateTime,tvCreateUserName,tvGoods,tvOpenPrice,tvCateGory,tvStopProfit,tvStopLoss;

        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        public TextView getTvCreateTime() {
            if(tvCreateTime == null){
                tvCreateTime = (TextView) view.findViewById(R.id.tv_createTime);
            }
            return tvCreateTime;
        }

        public TextView getTvCreateUserName() {
            if(tvCreateUserName == null){
                tvCreateUserName = (TextView) view.findViewById(R.id.tv_createUserName);
            }
            return tvCreateUserName;
        }

        public TextView getTvGoods() {
            if(tvGoods == null){
                tvGoods = (TextView) view.findViewById(R.id.tv_goods);
            }
            return tvGoods;
        }

        public TextView getTvOpenPrice() {
            if(tvOpenPrice == null){
                tvOpenPrice = (TextView) view.findViewById(R.id.tv_openprice);
            }
            return tvOpenPrice;
        }

        public TextView getTvCateGory() {
            if(tvCateGory == null){
                tvCateGory = (TextView) view.findViewById(R.id.tv_category);
            }
            return tvCateGory;
        }

        public TextView getTvStopProfit() {
            if(tvStopProfit == null){
                tvStopProfit = (TextView) view.findViewById(R.id.tv_stopProfit);
            }
            return tvStopProfit;
        }

        public TextView getTvStopLoss() {
            if(tvStopLoss == null){
                tvStopLoss = (TextView) view.findViewById(R.id.tv_stopLoss);
            }
            return tvStopLoss;
        }
    }

    /**
     * 将字符串转换为时间
     * @param createTime
     * @return
     */
    public String parseLongToString(long createTime){

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        Date date = new Date(createTime);
        String s = sdf.format(date);
        return s;
    }

}
