package com.longding999.longding.fragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicFragment;

/******************************************************************
 * Author:LCM
 * Date: 2016/3/9 15:21
 * Desc: 免责声明
 * *****************************************************************
 */
public class DisclaimerFragment extends BasicFragment {
    private TextView tvOrderSend; //开仓时间
    private  TextView tvIssuser; //发布人
    private TextView tvCategory;//种类
    private TextView tvGoods; //商品
    private  TextView tvPrice;
    private TextView tvStopLoss; //止损价
    private TextView tvStopProfit; //止赢价
    private TextView tvSprecial; //特殊说明
    private CheckBox cbFavour; //赞

    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_disclaimer, null);
        tvOrderSend = (TextView) view.findViewById(R.id.tv_ordersend);
        tvIssuser = (TextView) view.findViewById(R.id.tv_issuser);
        tvCategory = (TextView) view.findViewById(R.id.tv_category);
        tvGoods = (TextView) view.findViewById(R.id.tv_goods);
        tvPrice = (TextView) view.findViewById(R.id.tv_price);
        tvStopLoss = (TextView) view.findViewById(R.id.tv_stopLoss);
        tvStopProfit = (TextView) view.findViewById(R.id.tv_stopProfit);
        tvSprecial = (TextView) view.findViewById(R.id.tv_special);
        cbFavour = (CheckBox) view.findViewById(R.id.cb_favour);
        return view;
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void setListeners() {

        cbFavour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int i = Integer.parseInt(cbFavour.getText().toString());
                if(isChecked){
                   cbFavour.setText((i+1)+"");
                }else{
                    cbFavour.setText((i-1)+"");
                }
            }
        });

    }
}
