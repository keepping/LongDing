package com.longding999.longding.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.longding999.longding.TeacherInfoActivity;
import com.longding999.longding.adapter.TeacherAdapter;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.bean.TeacherBean;
import com.longding999.longding.bean.TeacherInfo;
import com.longding999.longding.bean.TeacherListBean;
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
 * Date: 2016/3/9 15:18
 * Desc: 老师 碎片
 * *****************************************************************
 */
public class TeacherFragment extends BasicFragment {
    private SwipeRefreshLayout mRefreshLayout;
    private ListView mListView;

    private TeacherAdapter mAdapter;
    private RequestQueue mQueue;
    private List<TeacherBean> mList;

    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_teacher, null);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        mListView = (ListView) view.findViewById(R.id.listView);
        return view;
    }

    @Override
    protected void initData() {
        mQueue = VolleyUtils.getmQueue();
        mList = new ArrayList<>();
        mAdapter = new TeacherAdapter(mList, mActivity);
        mListView.setAdapter(mAdapter);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.TEACHER_INFO, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                TeacherListBean teacherListBean = JSON.parseObject(s, TeacherListBean.class);
                List<TeacherBean> teacher = teacherListBean.getTeacher();
                mList.clear();
                mList.addAll(teacher);
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
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                mRefreshLayout.setRefreshing(false);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, TeacherInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("teacherId", mList.get(position).getTid() + "");
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
//                shortToast("点击了第"+(position+1)+"个老师");
            }
        });
    }
}
