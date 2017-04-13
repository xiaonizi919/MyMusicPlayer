package com.example.administrator.mymusicplayer.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.base.MyBaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends MyBaseActivity {
    @Bind(R.id.tab_main)
    TabLayout mTabLayout;
    @Bind(R.id.vp_main)
    ViewPager mViewPager;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setListeners() {

    }
}
