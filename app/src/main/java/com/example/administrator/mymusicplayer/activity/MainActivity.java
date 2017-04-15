package com.example.administrator.mymusicplayer.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.adapter.MyPagerAdapter;
import com.example.administrator.mymusicplayer.base.MyBaseActivity;
import com.example.administrator.mymusicplayer.fragment.MineFragment;
import com.example.administrator.mymusicplayer.fragment.RankFragment;
import com.example.administrator.mymusicplayer.fragment.SingsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MyBaseActivity {
    @Bind(R.id.tab_main)
    TabLayout mTabLayout;
    @Bind(R.id.vp_main)
    ViewPager mViewPager;
    @Bind(R.id.iv_search)
    ImageView mSearch;
    private List<Fragment> mFragments;
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
        mFragments=new ArrayList<>();
        mFragments.add(new MineFragment());
        mFragments.add(new SingsFragment());
        mFragments.add(new RankFragment());
    }

    @Override
    protected void setData() {
        MyPagerAdapter pagerAdapter=new MyPagerAdapter(getSupportFragmentManager(),this,mFragments);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        showQuickControl(true);
    }

    @Override
    protected void setListeners() {

    }

    @OnClick({R.id.iv_search})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_search:
                // TODO: 2017/4/15 搜索
                break;
        }
    }
}
