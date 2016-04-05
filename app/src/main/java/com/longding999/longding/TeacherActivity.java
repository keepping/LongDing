package com.longding999.longding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.longding999.longding.adapter.TeacherAdapter;
import com.longding999.longding.basic.BasicActivity;
import com.longding999.longding.bean.TeacherInfo;
import com.longding999.longding.utils.Constant;
import com.longding999.longding.utils.Logger;
import com.longding999.longding.utils.VolleyUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/4/5 10:46
 * Desc: 老师页面
 * *****************************************************************
 */
public class TeacherActivity extends BasicActivity implements View.OnClickListener{

    private TextView tvTitle,tvLeft,tvRight;
    private ImageView imageLeft;

    private ListView mListView;
    private TeacherAdapter mAdapter;
    private RequestQueue mQueue;
    private List<TeacherInfo> mList;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_teacher);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void getIntents() {

    }

    @Override
    protected void initViews() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        imageLeft = (ImageView) findViewById(R.id.image_left);
        tvTitle.setText("老师");
        tvLeft.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);

        mListView = (ListView) findViewById(R.id.listView);

    }

    @Override
    protected void setListeners() {
        imageLeft.setOnClickListener(this);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TeacherActivity.this, TeacherInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("teacherId",mList.get(position).getId());
                intent.putExtras(bundle);
                startActivity(intent);
//                shortToast("点击了第"+(position+1)+"个老师");
            }
        });
    }

    @Override
    protected void initData() {
        mQueue = VolleyUtils.getmQueue();
        mList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.TEACHER_INFO, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
//                Logger.e(s);

                try {
                    JSONArray jsonArray = new JSONArray(s);
//                    Logger.e(jsonArray.toString());
                    for (int i = 0;i <jsonArray.length();i ++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("Id");
                        String teachername = object.getString("teachername");
                        String teachercontent = object.getString("teachercontent");
                        String detailcontent = object.getString("detailcontent");
                        mList.add(new TeacherInfo(id,teachername,teachercontent,detailcontent));
                    }
                    mAdapter = new TeacherAdapter(mList,TeacherActivity.this);
                    mListView.setAdapter(mAdapter);
//                     Logger.e(mList.toString());
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_left:
                onBackPressed();
                break;
        }
    }
}
