package com.example.administrator.mymusicplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;

public abstract  class MyBaseActivity extends AppCompatActivity {

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
        findViews();
        initData();
        setData();
        setListeners();
    }

    // 可能全屏或者没有ActionBar等
    private void setWindowFeature() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 例
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
