package com.example.administrator.mymusicplayer.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.base.MyBaseActivity;
import com.example.administrator.mymusicplayer.bean.SongBean;
import com.example.administrator.mymusicplayer.service.PlayService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 播放界面
 */
public class PlayActivity extends MyBaseActivity {
    @Bind(R.id.player_back)
    ImageView back;
    @Bind(R.id.player_song)
    TextView song;
    @Bind(R.id.player_singer)
    TextView singer;
    @Bind(R.id.player_mode)
    ImageView mode;
    @Bind(R.id.player_pre)
    ImageView pre;
    @Bind(R.id.player_play)
    ImageView play;
    @Bind(R.id.player_next)
    ImageView next;
    @Bind(R.id.player_more)
    ImageView more;
    @Bind(R.id.player_seek)
    SeekBar mSeekBar;
    @Bind(R.id.player_vp)
    ViewPager mViewPager;
    @Bind(R.id.player_curr_time)
    TextView currTime;
    @Bind(R.id.player_total_time)
    TextView totalTime;

    private int state;
    private Intent mIntent;

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        mIntent = new Intent(this, PlayService.class);
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setListeners() {

    }

    @OnClick({R.id.player_back, R.id.player_mode, R.id.player_pre, R.id.player_play, R.id.player_next, R.id.player_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.player_back://返回
                finish();
                break;
            case R.id.player_mode://循环模式

                break;
            case R.id.player_pre://前一首

                break;
            case R.id.player_play://播放/暂停
                if (state == PlayService.PLAYING) {
                    showToast("暂停");
                    mIntent.setAction(PlayService.ACTION_PAUSE);
                } else if (state == PlayService.PAUSED) {
                    showToast("继续播放");
                    mIntent.setAction(PlayService.ACTION_START);
                }
                startService(mIntent);
                break;
            case R.id.player_next://下一首

                break;
            case R.id.player_more://更多

                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(SongBean songBean) {
        if (null != songBean) {
            song.setText(songBean.getSongName());
            singer.setText(songBean.getSinger());
            mSeekBar.setProgress((int) songBean.getProgress());
            currTime.setText(songBean.getCurrTime());
            totalTime.setText(songBean.getTotalTime());
            state = songBean.getState();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_play;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
