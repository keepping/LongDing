package com.longding999.longding.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.listener.GlobalOnItemClickManager;
import com.longding999.longding.utils.Logger;
import com.longding999.longding.view.EmotionInputDetector;

import java.util.ArrayList;
import java.util.List;

/******************************************************************
 * Author:LCM
 * Date: 2016/3/9 15:17
 * Desc: 聊天碎片
 * *****************************************************************
 */
public class ChatFragment extends BasicFragment implements View.OnClickListener {
    private ListView mListView;



    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_chat, null);

        mListView = (ListView) view.findViewById(R.id.chat_list);


        return view;
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void setListeners() {

    }


    @Override
    public void onClick(View v) {

    }


}
