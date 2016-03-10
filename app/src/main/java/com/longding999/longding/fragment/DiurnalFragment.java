package com.longding999.longding.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.longding999.longding.R;
import com.longding999.longding.adapter.DiurnalAdapter;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.bean.DiurnalInfo;
import com.longding999.longding.utils.Constant;
import com.longding999.longding.utils.Logger;
import com.longding999.longding.utils.VolleyUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/******************************************************************
 * Author:LCM
 * Date: 2016/3/9 15:19
 * Desc: 日刊碎片
 * *****************************************************************
 */
public class DiurnalFragment extends BasicFragment {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;

    private List<DiurnalInfo> mList;
    private DiurnalAdapter mAdapter;

    private RequestQueue mQueue;

    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_diurnal, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        mListView = (ListView) view.findViewById(R.id.listView);
        return view;
    }


    @Override
    protected void initData() {
        mQueue = VolleyUtils.getmQueue();
        mList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.DIURNAL_INFO, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray jsonArray = new JSONArray(s);

                    for (int i = 0;i <jsonArray.length();i ++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String diurnaltitle = object.getString("diurnaltitle");
                        String diurnalContent = object.getString("diurnalContent");
                        mList.add(new DiurnalInfo(diurnaltitle,diurnalContent));
                    }
                    mAdapter = new DiurnalAdapter(mList,mActivity);
                    mListView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Logger.e(volleyError.getMessage());
            }
        });

        mQueue.add(stringRequest);

    }

    @Override
    protected void setListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }
}
