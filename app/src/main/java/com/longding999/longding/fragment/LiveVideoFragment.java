package com.longding999.longding.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.longding999.longding.R;
import com.longding999.longding.VideoLiveActivity;
import com.longding999.longding.adapter.LiveVideoAdapter;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.bean.LiveInfo;

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
    private List<LiveInfo> mList;
    private LiveVideoAdapter mAdapter;

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
        mList = new ArrayList<>();
        mList.add(new LiveInfo(null,null,"原油","王老师","    以实际行情走势为导向，以蜡烛图交易信号为基础，稳扎稳打，步步为营","(12:00 - 14:00)"));
        mList.add(new LiveInfo(null,null,"白银","张老师","    以实际行情走势为导向，以蜡烛图交易信号为基础，稳扎稳打，步步为营","(14:00 - 16:00)"));
        mList.add(new LiveInfo(null,null,"原油","王老师","    以实际行情走势为导向，以蜡烛图交易信号为基础，稳扎稳打，步步为营","(12:00 - 14:00)"));
        mList.add(new LiveInfo(null,null,"白银","张老师","    以实际行情走势为导向，以蜡烛图交易信号为基础，稳扎稳打，步步为营","(14:00 - 16:00)"));
        mAdapter = new LiveVideoAdapter(mList,mActivity);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void setListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, VideoLiveActivity.class);
                mActivity.startActivity(intent);
            }
        });
    }
}
