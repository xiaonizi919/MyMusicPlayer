package com.example.administrator.mymusicplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

public abstract class MyBaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

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
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
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

    /**
     * 吐司
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int res) {
        Toast.makeText(this, getResources().getString(res), Toast.LENGTH_SHORT).show();
    }

}
