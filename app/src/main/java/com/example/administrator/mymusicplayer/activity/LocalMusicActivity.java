package com.example.administrator.mymusicplayer.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.adapter.LocalMusicAdapter;
import com.example.administrator.mymusicplayer.base.MyBaseActivity;
import com.example.administrator.mymusicplayer.bean.SongBean;
import com.example.administrator.mymusicplayer.service.PlayService;
import com.example.administrator.mymusicplayer.utils.MusicUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class LocalMusicActivity extends MyBaseActivity {

    @Bind(R.id.local_music_lv)
    ListView mListView;
    private List<SongBean> mSongList;

    @Override
    protected void initData() {
        showQuickControl(true);
        mSongList = new ArrayList<>();
    }

    @Override
    protected void setData() {
        mSongList = MusicUtils.getInstance().getMusicData(LocalMusicActivity.this);
        LocalMusicAdapter adapter = new LocalMusicAdapter(this, mSongList);
        mListView.setAdapter(adapter);
        if (MainActivity.songList.size()==0)
            MainActivity.songList.addAll(mSongList);
    }

    @Override
    protected void setListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(LocalMusicActivity.this, PlayService.class);
                intent.setAction(PlayService.ACTION_PLAY_LIST);
                intent.putExtra("position",position);
                startService(intent);
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_local_music;
    }
}
