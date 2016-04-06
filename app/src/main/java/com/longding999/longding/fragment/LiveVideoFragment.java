package com.longding999.longding.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.longding999.longding.R;
import com.longding999.longding.VideoLiveActivity;
import com.longding999.longding.adapter.LiveVideoAdapter;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.bean.LiveInfo;
import com.longding999.longding.bean.VideoListBean;
import com.longding999.longding.utils.Constant;
import com.longding999.longding.utils.Logger;
import com.longding999.longding.utils.VolleyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/3/31 17:37
 * Desc:
 * *****************************************************************
 */
public class LiveVideoFragment extends BasicFragment {
    private ListView mListView;
    private List<VideoListBean> mList;
    private LiveVideoAdapter mAdapter;

    private RequestQueue mQueue;

    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_live_text_video, null);
        mListView = (ListView) view.findViewById(R.id.listView);
        return view;
    }

    @Override
    protected void initData() {
        mQueue = VolleyUtils.getmQueue();
        mList = new ArrayList<>();
        mAdapter = new LiveVideoAdapter(mList,mActivity);
        mListView.setAdapter(mAdapter);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.VIDEO_LIVE_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                List<VideoListBean> videoListBeen = JSON.parseArray(s, VideoListBean.class);
                mList.clear();
                mList.addAll(videoListBeen);
                mAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Logger.e(volleyError.getMessage());
                shortToast("网络请求失败");
            }
        });

        mQueue.add(stringRequest);
    }

    @Override
    protected void setListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    Intent intent = new Intent(mActivity, VideoLiveActivity.class);
                    mActivity.startActivity(intent);
                }
            }
        });
    }
}
