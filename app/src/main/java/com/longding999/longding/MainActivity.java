package com.longding999.longding;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.longding999.longding.basic.BasicFragmentActivity;
import com.longding999.longding.fragment.ExpertOpinionFragment;
import com.longding999.longding.fragment.TextLiveFragment;
import com.longding999.longding.fragment.VideoLiveFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主布局
 */
public class MainActivity extends BasicFragmentActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup mRadioGroup;
    private int currentTabIndex = 0;
    private List<Fragment> fragmentList;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private RadioButton lastRadioButton;


    @Override
    protected void bindView() {
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    @Override
    protected void getIntents() {
    }

    @Override
    protected void initViews() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        lastRadioButton = (RadioButton) mRadioGroup.getChildAt(0);
    }

    @Override
    protected void setListeners() {
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {
        fm = getSupportFragmentManager();

        fragmentList = new ArrayList<>();
        fragmentList.add(new VideoLiveFragment());
        fragmentList.add(new TextLiveFragment());
        fragmentList.add(new ExpertOpinionFragment());
        fm.beginTransaction().add(R.id.frameLayout,fragmentList.get(currentTabIndex)).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount()-1;i++){
            RadioButton rb = (RadioButton) group.getChildAt(i);
            if(rb.getId() == checkedId){
//                shortToast("选中第"+i);
                lastRadioButton = rb;
                showFragment(i);
            }
        }
        if(checkedId == R.id.rb_mine){
//            shortToast("选中我的,可惜还没有...");
//            lastRadioButton.setChecked(true);
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivityForResult(intent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lastRadioButton.setChecked(true);
    }

    /**
     * 根据选中radiobutton下表显示fragment
     * @param targetTabIndex
     */
    public void showFragment(int targetTabIndex){
        ft = fm.beginTransaction();
        if(targetTabIndex != currentTabIndex) {
            Fragment currentFragment = fragmentList.get(currentTabIndex);
            Fragment targetFragment = fragmentList.get(targetTabIndex);

            if (!targetFragment.isAdded()) {
                ft.add(R.id.frameLayout, targetFragment).hide(currentFragment).commit();
            } else {
                ft.show(targetFragment).hide(currentFragment).commit();
            }
            currentTabIndex = targetTabIndex;
        }

    }
}
