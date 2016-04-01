package com.longding999.longding.fragment;

import android.view.View;
import android.widget.ListView;

import com.longding999.longding.R;
import com.longding999.longding.adapter.LiveTextAdapter;
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
public class LiveTextFragment extends BasicFragment {
    private ListView mListView;
    private List<LiveInfo> mList;
    private LiveTextAdapter mAdapter;

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
        mList.add(new LiveInfo("油此盈利",null,null,"王老师","    以实际行情走势为导向，以蜡烛图交易信号为基础，稳扎稳打，步步为营",null));
        mList.add(new LiveInfo("油此盈利",null,null,"张老师","    以实际行情走势为导向，以蜡烛图交易信号为基础，稳扎稳打，步步为营",null));
        mAdapter = new LiveTextAdapter(mList,mActivity);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void setListeners() {

    }
}
