package com.longding999.longding.fragment;

import android.view.View;
import android.widget.ListView;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicFragment;

/**
 * ****************************************************************
 * Author:LCM
 * Date: 2016/3/31 17:37
 * Desc:
 * *****************************************************************
 */
public class LiveTextFragment extends BasicFragment {
    private ListView mListView;
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

    }

    @Override
    protected void setListeners() {

    }
}
