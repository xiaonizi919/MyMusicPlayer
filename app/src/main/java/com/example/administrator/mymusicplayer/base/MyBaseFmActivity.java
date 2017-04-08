package com.example.administrator.mymusicplayer.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Window;

import com.example.administrator.mymusicplayer.R;

import java.util.Stack;

/**
 * 添加Fragment的Activity
 */
public  abstract class MyBaseFmActivity extends FragmentActivity {

    public int mFragmentType = 0;// 0默认的，1一律 addFragment
    private Stack<Fragment> mFragmentList = new Stack<Fragment>();
    public Activity mActivity;

    // 初始化UI，setContentView等
    protected abstract void initContentView(Bundle savedInstanceState);

    protected abstract void findViews();

    protected abstract void initData();

    protected abstract void setData();

    protected abstract void setListeners();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowFeature();
        initContentView(savedInstanceState);
        mActivity = this;
        findViews();
        initData();
        setData();
        setListeners();
    }
    // 可能全屏或者没有ActionBar等
    private void setWindowFeature() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 例
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onStart() {
        super.onStart();
    }


    // 添加fragment
    public void transactionAdd(Fragment fragment, String tag, int containerid,boolean baddBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager() .beginTransaction();
        transaction.setCustomAnimations(R.anim.in_from_right,
                R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
        transaction.add(containerid, fragment, tag);
        if (baddBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    // 替换fragment
    public void transactionReplace(Fragment fragment, String tag,
                                   int containerid, boolean baddBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.in_from_right,
                R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
        transaction.replace(containerid, fragment);
        if (baddBackStack)
            transaction.addToBackStack(tag);
        transaction.commitAllowingStateLoss();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        FragmentManager m = getSupportFragmentManager();
        if (m.getBackStackEntryCount() > 0 ) {
            String name = m.getBackStackEntryAt(m.getBackStackEntryCount()-1).getName();
            if (keyCode == KeyEvent.KEYCODE_BACK){
                m.popBackStack();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
