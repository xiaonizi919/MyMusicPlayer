package com.example.administrator.mymusicplayer.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.mymusicplayer.R;

import java.util.List;

/**
 * Created by lkq on 2017/4/11.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private Context mContext;

    public MyPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        mContext = context;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getStringArray(R.array.main_tab)[position];
    }
}
