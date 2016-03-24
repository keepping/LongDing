package com.longding999.longding.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.longding999.longding.HistorySugActivity;
import com.longding999.longding.R;
import com.longding999.longding.adapter.SuggestAdapter;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.bean.SuggestInfo;
import com.longding999.longding.utils.Constant;
import com.longding999.longding.utils.DateParseUtils;
import com.longding999.longding.utils.DbHelper;
import com.longding999.longding.utils.Logger;
import com.longding999.longding.utils.VolleyUtils;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/3/9 13:44
 * Desc: 专家建议页面
 * *****************************************************************
 */
public class SuggestFragment extends BasicFragment {
    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    private DbManager dbManager;
    private RequestQueue mQueue;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;

    private List<SuggestInfo> mList;
    private SuggestAdapter mAdapter;

    @Override
    protected void initBundle() {
    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_expertopinion, null);
        tvLeft = (TextView) view.findViewById(R.id.tv_left);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        imageLeft = (ImageView) view.findViewById(R.id.image_left);
        tvTitle.setText("今日建议");
        tvRight.setText("历史建议");
        tvLeft.setVisibility(View.GONE);
        imageLeft.setVisibility(View.GONE);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        mListView = (ListView) view.findViewById(R.id.listView);
        return view;
    }


    @Override
    protected void initData() {
        dbManager = DbHelper.getInstance().getDbManger();
        mQueue = VolleyUtils.getmQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.SUGGEST_INFO, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Logger.e(s);
                new AsyncTask<String,Void,List<SuggestInfo>>(){

                    @Override
                    protected List<SuggestInfo> doInBackground(String... params) {
                        String s1 = params[0];
                        JSONArray jsonArray = JSONArray.parseArray(s1);
                        List<SuggestInfo> list = new ArrayList<>();
                        for (int i = 0; i < jsonArray.size();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Integer id = jsonObject.getInteger("id");
                            String createTime = jsonObject.getString("createTime");
                            Integer createUserID = jsonObject.getInteger("createUserID");
                            String createUserName = jsonObject.getString("createUserName");
                            String comefrom = jsonObject.getString("comefrom");
                            String category = jsonObject.getString("category");
                            String goods = jsonObject.getString("goods");
                            String openprice = jsonObject.getString("openprice");
                            String stopprofit = jsonObject.getString("stopprofit");
                            String stoploss = jsonObject.getString("stoploss");
                            String ps = jsonObject.getString("ps");
                            long date = DateParseUtils.parseStringToLong(createTime);
                            list.add(new SuggestInfo(id,date,DateParseUtils.parseLongToString(date),createUserID,createUserName,comefrom,category,goods,openprice,stopprofit,stoploss,ps));
                        }
                        return list;
                    }

                    @Override
                    protected void onPostExecute(List<SuggestInfo> suggestInfos) {
                        try {
                            dbManager.save(suggestInfos);
                            Logger.e("存入数据大小："+suggestInfos.size());
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        super.onPostExecute(suggestInfos);
                    }
                }.execute(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
//        mQueue.add(stringRequest);
        setmAdapter();

    }

    @Override
    protected void setListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, HistorySugActivity.class);
                mActivity.startActivity(intent);
            }
        });
    }


    /**
     * 设置适配器
     */
    public void setmAdapter(){
        long currentTimeMillis = System.currentTimeMillis();
        String currentTime = DateParseUtils.parseLongToString(currentTimeMillis);
        Logger.e("currentTime"+currentTime);

        try {
            List<SuggestInfo> suggestInfos = dbManager.selector(SuggestInfo.class).where("createDate", "=", "2016-01-27").findAll();
            if(suggestInfos == null){
                shortToast("木有数据啊！");
            }else {
                Logger.e(suggestInfos.size() + "");
                mAdapter = new SuggestAdapter(suggestInfos, mActivity);
                mListView.setAdapter(mAdapter);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }


}
