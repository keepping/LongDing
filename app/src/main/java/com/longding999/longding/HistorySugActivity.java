package com.longding999.longding;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.longding999.longding.adapter.SuggestAdapter;
import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.bean.SuggestInfo;
import com.longding999.longding.utils.DbHelper;
import com.longding999.longding.utils.Logger;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * *****************************************************************
 * Author:LCM
 * Date: 2016/3/14 9:51
 * Desc: 历史建议页面
 * *****************************************************************
 */
public class HistorySugActivity extends BasicActivity implements View.OnClickListener{
    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    private LinearLayout linearLayout;
    private Button btnFilterTime;
    private ListView mListView;

    private PopupWindow mPopupWindow;

    private TextView tvEnsure,tvCancel;
    private DatePicker datePicker;

    private DbManager dbManager;

    private SuggestAdapter mAdapter;


    @Override
    protected void bindView() {
        setContentView(R.layout.activity_history_sug);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void getIntents() {

    }

    @Override
    protected void initViews() {
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        imageLeft = (ImageView) findViewById(R.id.image_left);
        tvTitle.setText("历史建议");
        tvLeft.setText("今日建议");
        tvRight.setVisibility(View.GONE);
        imageLeft.setVisibility(View.GONE);

        btnFilterTime = (Button) findViewById(R.id.btn_filter_time);
        mListView = (ListView) findViewById(R.id.listView);


        initPopupWindow();
    }

    /**
     * 初始化popupWindows
     */
    private void initPopupWindow() {
        View popupView = View.inflate(HistorySugActivity.this, R.layout.popwindows_filter_time, null);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        tvCancel = (TextView) popupView.findViewById(R.id.tv_cancel);
        tvEnsure = (TextView) popupView.findViewById(R.id.tv_ensure);
        datePicker = (DatePicker) popupView.findViewById(R.id.datepicker);

    }

    /**
     * 显示popWindow
     *
     * */
    public void showPop(View parent,int postion, int x, int y) {
        //设置popwindow显示位置
        mPopupWindow.showAtLocation(parent, postion, x, y);
        //获取popwindow焦点
        mPopupWindow.setFocusable(true);
        //设置popwindow如果点击外面区域，便关闭。
        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.update();
//        if (mPopupWindow.isShowing()) {
//
//        }

    }

    @Override
    protected void setListeners() {
        tvLeft.setOnClickListener(this);
        btnFilterTime.setOnClickListener(this);
        tvEnsure.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        dbManager = DbHelper.getInstance().getDbManger();
        setmAdapter(2016,1,27);
        try {
            List<SuggestInfo> suggestInfoList = dbManager.findAll(SuggestInfo.class);
            long minTime = suggestInfoList.get(0).getCreateTime();
            long maxTime = suggestInfoList.get(suggestInfoList.size() - 1).getCreateTime();
            datePicker.setMaxDate(maxTime);
            datePicker.setMinDate(minTime);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置适配器
     */
    public void setmAdapter(int year,int month,int dayOfMonth){
        StringBuilder builder = new StringBuilder();
        builder.append(year+"-");
        if(month<10){
            builder.append("0"+month+"-");
        }else{
            builder.append(month+"-");
        }

        if(dayOfMonth < 10){
            builder.append("0"+dayOfMonth);
        }else{
            builder.append(dayOfMonth);
        }

        try {
            List<SuggestInfo> suggestInfos = dbManager.selector(SuggestInfo.class).where("createDate", "=",builder.toString()).findAll();
            Logger.e(suggestInfos.size()+"");
            mAdapter = new SuggestAdapter(suggestInfos,HistorySugActivity.this);
            mListView.setAdapter(mAdapter);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_filter_time:
                showPop(linearLayout, Gravity.BOTTOM,0,0);
                break;

            case R.id.tv_left:
                onBackPressed();
                break;

            case R.id.tv_cancel:
                if(mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                }
                break;

            case R.id.tv_ensure:
                int year = datePicker.getYear();
                int month = datePicker.getMonth()+1;
                int dayOfMonth = datePicker.getDayOfMonth();
//                shortToast(year+"-"+month+"-"+dayOfMonth);
                setmAdapter(year,month,dayOfMonth);
                if(mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                }
                break;

            default:
                break;
        }
    }
}
