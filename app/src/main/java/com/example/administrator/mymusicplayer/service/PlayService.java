package com.example.administrator.mymusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.mymusicplayer.activity.MainActivity;
import com.example.administrator.mymusicplayer.bean.MyEvent;
import com.example.administrator.mymusicplayer.bean.SongBean;
import com.example.administrator.mymusicplayer.config.MyConfig;
import com.example.administrator.mymusicplayer.fragment.SingsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.mymusicplayer.config.MyConfig.position;


public class PlayService extends Service implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener {
    private final String TAG = this.getClass().getSimpleName();

    public static final MediaPlayer mp = new MediaPlayer();//唯一的mediaPlayer
    //用于intent启动服务时设置的action
    public static final String ACTION_PRE = "ACTION_PRE";
    public static final String ACTION_NEXT = "ACTION_NEXT";
    public static final String ACTION_PLAY_LIST = "ACTION_PLAY_LOCAL";
    public static final String ACTION_PLAY_NET = "ACTION_PLAY_NET";
    public static final String ACTION_PAUSE = "ACTION_PAUSE";
    public static final String ACTION_START = "ACTION_START";
    public static final String ACTION_TRACKING = "ACTION_TRACKING";

    public static int STATE;//播放状态
    public static final int PLAYING = 4;
    public static final int STOP = 1;
    public static final int PAUSED = 2;
    public static final int TRACKING = 3;

    private int bufferPercent = 0;

    private List<SongBean> musicList;//音乐列表

    private SongBean currSong;//当前播放的音乐

    private boolean flag = true;//播放是否完成的标志

    public static int mCurrPosition = -1;
    private int trackProgress;
    private String mAction;
    public static int playType=-1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TAG", "onStartCommand222222222");
        mAction = intent.getAction();
        switch (intent.getAction()) {
            case ACTION_PRE://上一首
                playNext(1);
                break;
            case ACTION_NEXT://下一首
                playNext(2);
                break;
            case ACTION_PLAY_LIST://播放列表内音乐
                STATE = PLAYING;
                getListFormDb();
                mCurrPosition = intent.getIntExtra(position, 0);
                playMusic(musicList.get(mCurrPosition));
                break;
            case ACTION_PLAY_NET://播放在线音乐
                STATE = PLAYING;
                getListFormNet();
                mCurrPosition = intent.getIntExtra(position, 0);
                playMusic(musicList.get(mCurrPosition));
                break;
            case ACTION_PAUSE://暂停
                STATE = PAUSED;
                mp.pause();
                break;
            case ACTION_START://继续播放
                STATE = PLAYING;
                mp.start();
                break;
            case ACTION_TRACKING://拖动状态
                STATE = TRACKING;
                trackProgress = intent.getIntExtra(MyConfig.progress, 0);
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //播放本地音乐
    private void playMusic(SongBean songBean) {
        currSong = songBean;
        flag = false;
        Log.e(TAG, "playMusic: type="+mAction+",name:" + songBean.getSongName());
//        mp.reset();
//        try {
//            mp.setDataSource(songBean.getPath());
//            mp.prepare();
//            flag = true;
//            STATE = PLAYING;
//            mp.start();
//            updateSeekBar();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 播放上一首或者下一首
     *
     * @param flag 1为上一首，2为下一首
     */
    private void playNext(int flag) {
        if (flag == 1) {
            mCurrPosition = (mCurrPosition + musicList.size() - 1) % musicList.size();//上一首的position
            Toast.makeText(this, "上一首", Toast.LENGTH_SHORT).show();
        } else {
            mCurrPosition = (mCurrPosition + 1) % musicList.size();//下一首的position
            Toast.makeText(this, "下一首", Toast.LENGTH_SHORT).show();
        }
        playMusic(musicList.get(mCurrPosition));
        MyEvent myEvent = new MyEvent();
        if (mAction.equals(ACTION_PLAY_LIST))
            myEvent.setTarLocalPos(mCurrPosition);
        else
            myEvent.setTarNetPos(mCurrPosition);
        EventBus.getDefault().post(myEvent);//发送广播

    }

    @Override
    public void onCreate() {
        Log.e("TAG", "onCreate11111111");
        EventBus.getDefault().register(this);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置播放类型为音乐
        mp.setOnBufferingUpdateListener(this);//缓冲进度
        mp.setOnCompletionListener(this);
        STATE = STOP;
    }

    /**
     * 从数据库中或者音乐列表和播放列表
     */
    private void getListFormDb() {
        if (null != musicList) {//置空
            musicList.clear();
            musicList = null;
        }
        musicList = new ArrayList<>();
        musicList.addAll(MainActivity.songList);
        playType=1;
    }

    /**
     * 获取在线播放list
     */
    private void getListFormNet() {
        if (null != musicList) {
            musicList.clear();
            musicList = null;
        }
        musicList = new ArrayList<>();
        musicList.addAll(SingsFragment.mList);
        playType=2;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //mediaPlayer缓冲监听
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    //mediaPlayer准备完成监听
    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    public void updateSeekBar() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //flag=true;
                while (flag) {
                    if (mp.getCurrentPosition() < mp.getDuration()) {
                        int music_length = mp.getDuration();
                        int curr = mp.getCurrentPosition();
                        float progress = (float) (curr * 100.0 / music_length);
                        currSong.setTotalTime(toTime(music_length));
                        currSong.setCurrTime(toTime(curr));
                        currSong.setState(STATE);
                        currSong.setBufferPercent(bufferPercent);
                        currSong.setProgress(progress);
                        currSong.setCurrPosition(curr);
                        if (STATE == TRACKING) {//拖动
                            mp.seekTo(trackProgress);
                            STATE = PLAYING;
                            currSong.setState(STATE);
                            currSong.setProgress(trackProgress);
                            currSong.setCurrTime(toTime(mp.getCurrentPosition()));
                            currSong.setCurrPosition(mp.getCurrentPosition());
                        }
                        EventBus.getDefault().post(currSong);//发送广播
                    } else {
                        playNext(2);//播放下一首
                        flag = false;
                    }
                }

            }
        }).start();
    }

    private String toTime(int time) {
        int minute = time / 1000 / 60;
        int s = time / 1000 % 60;
        String mm;
        String ss;
        if (minute < 10)
            mm = "0" + minute;
        else
            mm = minute + "";
        if (s < 10)
            ss = "0" + s;
        else
            ss = "" + s;
        return mm + ":" + ss;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void event(String pause) {
        STATE = PAUSED;
        mp.pause();
    }
}
