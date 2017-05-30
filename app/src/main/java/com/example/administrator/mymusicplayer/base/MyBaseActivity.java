package com.example.administrator.mymusicplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.api.MusicService;
import com.example.administrator.mymusicplayer.fragment.QuickControlFragment;

import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class MyBaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    /**生命周期log*/
    protected boolean showLifeLog=false;
    /**底部控制栏*/
    private QuickControlFragment mFragment;

    protected MusicService mMusicService;

    // 如果需要，在此方法中恢复状态 选择实现
    protected void initState(Bundle savedInstanceState) {
    }

    protected abstract void initData();

    protected abstract void setData();

    protected abstract void setListeners();

    /**获取布局资源id*/
    protected abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowFeature();
        initState(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initRetrofit();
        initData();
        setData();
        setListeners();
    }

    // 可能全屏或者没有ActionBar等
    private void setWindowFeature() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    //获取api接口对象
    private void initRetrofit() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(MusicService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mMusicService= mRetrofit.create(MusicService.class);
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

    /** 吐司*/
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int res) {
        Toast.makeText(this, getResources().getString(res), Toast.LENGTH_SHORT).show();
    }

    /**
     * 在每个activity的底部添加FrameLayout用来显示底部控制栏
     <FrameLayout
     android:id="@+id/bottom_container"
     android:layout_width="match_parent"
     android:layout_height="50dp"/>
     *
     * @param show 显示或关闭底部播放控制栏
     */
    protected void showQuickControl(boolean show) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (show) {
            if (mFragment == null) {
                mFragment = QuickControlFragment.newInstance();
                ft.add(R.id.bottom_container, mFragment).commitAllowingStateLoss();
            } else {
                ft.show(mFragment).commitAllowingStateLoss();
            }
        } else {
            if (mFragment != null)
                ft.hide(mFragment).commitAllowingStateLoss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }



}
