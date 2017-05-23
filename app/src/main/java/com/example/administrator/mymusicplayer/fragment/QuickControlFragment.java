package com.example.administrator.mymusicplayer.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.activity.PlayActivity;
import com.example.administrator.mymusicplayer.base.MyBaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 底部常驻控制栏 相关逻辑在这里处理
 */

public class QuickControlFragment extends MyBaseFragment {
    @Bind(R.id.lin_mini_play)
    LinearLayout mLinPlay;

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

    @OnClick({R.id.lin_mini_play})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.lin_mini_play:
                startActivity(PlayActivity.class);
//                getActivity().overridePendingTransition(R.anim.in_from_bottom,R.anim.out_to_bottom);
                break;
        }
    }
}
