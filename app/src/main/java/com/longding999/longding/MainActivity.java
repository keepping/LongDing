package com.longding999.longding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.longding999.longding.basic.BasicFragmentActivity;
import com.longding999.longding.fragment.LiveFragment;
import com.longding999.longding.fragment.MineFragment;
import com.longding999.longding.fragment.SuggestFragment;
import com.longding999.longding.fragment.HomeFragment;
import com.longding999.longding.fragment.MarketFragment;
import com.longding999.longding.fragment.UserInfoFragment;
import com.longding999.longding.utils.MyApplication;
import com.longding999.longding.utils.SharedHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 主布局
 */
public class MainActivity extends BasicFragmentActivity implements RadioGroup.OnCheckedChangeListener, MineFragment.MineCallBack{
    private RadioGroup mRadioGroup;
    private int currentTabIndex = 0;
    private List<Fragment> fragmentList;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private RadioButton lastRadioButton;

//    private UserInfoFragment userInfoFragment;
    private MineFragment mineFragment;


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
        if(MyApplication.isLOLLIPOP) {
            for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
                RadioButton rb = (RadioButton) mRadioGroup.getChildAt(i);
                rb.setBackgroundResource(R.drawable.ripple_bg);
            }
        }
    }

    @Override
    protected void setListeners() {
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {
        fm = getSupportFragmentManager();

        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new LiveFragment());
        fragmentList.add(new MarketFragment());
//        userInfoFragment = new UserInfoFragment();
//        fragmentList.add(userInfoFragment);
        mineFragment = new MineFragment();
        fragmentList.add(mineFragment);
        fm.beginTransaction().add(R.id.frameLayout, fragmentList.get(currentTabIndex)).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_mine) {
            if (!SharedHelper.getBoolean(SharedHelper.LOGIN, false)) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, 1000);
//                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
            }
        }
        for (int i = 0; i < group.getChildCount(); i++) {
            RadioButton rb = (RadioButton) group.getChildAt(i);
            if (rb.getId() == checkedId) {
                showFragment(i);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000 && resultCode == 1001) {
            int id = data.getIntExtra("_id", -1);
            mineFragment.refreshViewById(id);
        } else if(requestCode == 2000 && resultCode == 2001){
            mineFragment.onActivityResult(requestCode, resultCode,data);
        }else {
            lastRadioButton.setChecked(true);
        }

        super.onActivityResult(requestCode, resultCode, data);
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
                ft.add(R.id.frameLayout, targetFragment).hide(currentFragment).commitAllowingStateLoss();
            } else {
                ft.show(targetFragment).hide(currentFragment).commitAllowingStateLoss();
            }

            currentTabIndex = targetTabIndex;

        }

    }

    /**
     *退出登录回调接口
     */
    @Override
    public void onLogOut() {
        lastRadioButton.setChecked(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
