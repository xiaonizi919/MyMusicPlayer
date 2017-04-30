package com.example.administrator.mymusicplayer.activity;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.adapter.MyPagerAdapter;
import com.example.administrator.mymusicplayer.base.MyBaseActivity;
import com.example.administrator.mymusicplayer.bean.SongBean;
import com.example.administrator.mymusicplayer.fragment.MineFragment;
import com.example.administrator.mymusicplayer.fragment.RankFragment;
import com.example.administrator.mymusicplayer.fragment.SingsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends MyBaseActivity {
    @Bind(R.id.tab_main)
    TabLayout mTabLayout;
    @Bind(R.id.vp_main)
    ViewPager mViewPager;
    @Bind(R.id.iv_search)
    ImageView mSearch;
    public static List<SongBean> songList;

    @Override
    protected void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MineFragment());
        fragments.add(new SingsFragment());
        fragments.add(new RankFragment());
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), this, fragments);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        showQuickControl(true);
        songList=new ArrayList<>();
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.iv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                // TODO: 2017/4/15 搜索
                break;
        }
    }

    private boolean isExit;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit=false;
        }
    };
    @Override
    public void onBackPressed() {
        if (isExit) {
            super.onBackPressed();
        } else{
            showToast("再点击一次退出应用");
            mHandler.sendEmptyMessageDelayed(0,2000);
            isExit=true;
        }
    }
}
