package com.example.administrator.mymusicplayer.activity;

import android.widget.ListView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.base.MyBaseActivity;
import com.example.administrator.mymusicplayer.bean.SongBean;
import com.example.administrator.mymusicplayer.utils.MusicUtils;

import java.util.List;

import butterknife.Bind;

public class LocalMusicActivity extends MyBaseActivity {

    @Bind(R.id.local_music_lv)
    ListView mListView;

    @Override
    protected void initData() {
        List<SongBean> songList  =  MusicUtils.getMusicData(this, new MusicUtils.ScanCallback() {
            @Override
            public void scanProgress(int songCount) {
//                mTV.setText("已扫描"+songCount+"首");
            }

            @Override
            public void onComplete(int sumCount) {
//                mTV.setText("扫描完成，共"+sumCount+"首");
            }
        });
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_local_music;
    }
}
