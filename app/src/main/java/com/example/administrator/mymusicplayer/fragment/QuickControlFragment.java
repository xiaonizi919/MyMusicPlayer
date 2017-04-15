package com.example.administrator.mymusicplayer.fragment;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.base.MyBaseFragment;

/**
 * Created by liukq on 2017/4/15.
 * 底部常驻控制栏 相关逻辑在这里处理
 */

public class QuickControlFragment extends MyBaseFragment {

    private static QuickControlFragment mFragment;

    public static  QuickControlFragment newInstance(){
        return new QuickControlFragment();
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.layout_miniplayer;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
