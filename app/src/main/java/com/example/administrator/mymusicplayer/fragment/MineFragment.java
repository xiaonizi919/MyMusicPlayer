package com.example.administrator.mymusicplayer.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.base.MyBaseFragment;
import com.example.administrator.mymusicplayer.utils.MusicUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by liukq on 2017/4/15.
 * 我的
 */

public class MineFragment extends MyBaseFragment {
    @Bind(R.id.text)
    TextView test;
    @Bind(R.id.btn_scan)
    Button mButton;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
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

    @OnClick({R.id.btn_scan})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_scan:
                scanMusic();
                break;
        }
    }

    private void scanMusic() {
        MusicUtils.getMusicData(getActivity(), new MusicUtils.ScanCallback() {
            @Override
            public void scanProgress(int songCount) {
                test.setText("已扫描"+songCount+"首");
            }

            @Override
            public void onComplete(int sumCount) {
                test.setText("扫描完成，共"+sumCount+"首");
            }
        });
    }
}
