package com.longding999.longding.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.longding999.longding.R;
import com.longding999.longding.basic.BasicFragment;
import com.longding999.longding.listener.GlobalOnItemClickManager;
import com.longding999.longding.view.EmotionInputDetector;

import java.util.ArrayList;
import java.util.List;

/******************************************************************
 * Author:LCM
 * Date: 2016/3/9 15:17
 * Desc: 聊天碎片
 * *****************************************************************
 */
public class ChatFragment extends BasicFragment implements View.OnClickListener{
    private EmotionInputDetector mDetector;
    private ListView mListView;

    private ImageView imageEmotionButton;
    private EditText edtText;
    private Button btnReply;
    private LinearLayout emotionLayout;

    private Button btnEmotionClassic,btnEmotionMokey;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private int currentTabIndex = 0;
    private List<Fragment> fragmentList;


    @Override
    protected void initBundle() {

    }

    @Override
    protected View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_chat, null);
        imageEmotionButton = (ImageView) view.findViewById(R.id.iv_emoji_button);
        edtText = (EditText) view.findViewById(R.id.edt_text);
        btnReply = (Button) view.findViewById(R.id.btn_reply);
        mListView = (ListView) view.findViewById(R.id.chat_list);
        emotionLayout = (LinearLayout) view.findViewById(R.id.emotion_layout);

        btnEmotionClassic = (Button) view.findViewById(R.id.btn_emotion_classic);
        btnEmotionMokey = (Button) view.findViewById(R.id.btn_emotion_monkey);

        mDetector = EmotionInputDetector.with(mActivity)
                .bindToEditText(edtText)
                .bindToEmotionButton(imageEmotionButton)
                .bindToContent(mListView)
                .setEmotionView(emotionLayout)
                .build();

        return view;
    }


    @Override
    protected void initData() {
        fm = getChildFragmentManager();
        fragmentList = new ArrayList<>();
        fragmentList.add(new ClassicFragment());
        fragmentList.add(new MokeyFragment());
        fm.beginTransaction().add(R.id.framelayout, fragmentList.get(currentTabIndex)).commit();
        GlobalOnItemClickManager globalOnItemClickListener = GlobalOnItemClickManager.getInstance();
        globalOnItemClickListener.attachToEditText(mActivity,edtText);

    }

    @Override
    protected void setListeners() {
        btnEmotionClassic.setOnClickListener(this);
        btnEmotionMokey.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_emotion_classic:
                showFragment(0);
                break;

            case R.id.btn_emotion_monkey:
                showFragment(1);
                break;

            default:
                break;
        }
    }


    /**
     * 根据选中radiobutton下表显示fragment
     *
     * @param targetTabIndex
     */
    public void showFragment(int targetTabIndex) {
        ft = fm.beginTransaction();
        if (targetTabIndex != currentTabIndex) {
            Fragment currentFragment = fragmentList.get(currentTabIndex);
            Fragment targetFragment = fragmentList.get(targetTabIndex);

            if (!targetFragment.isAdded()) {
                ft.add(R.id.framelayout, targetFragment).hide(currentFragment).commitAllowingStateLoss();
            } else {
                ft.show(targetFragment).hide(currentFragment).commitAllowingStateLoss();
            }

            currentTabIndex = targetTabIndex;

        }

    }
}
