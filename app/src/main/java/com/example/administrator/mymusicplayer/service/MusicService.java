package com.example.administrator.mymusicplayer.service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;

import com.example.administrator.mymusicplayer.bean.SongBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by liukq on 2017/4/30.
 */

public class MusicService extends Service implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener {
    public static final String DOWNLOAD_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+"/downloads/";
    public static final String ACTION_STOPPING="ACTION_STOPPING";
    public static final String ACTION_PLAYING="ACTION_PLAYING";
    public static final String ACTION_TEST="ACTION_TEST";
    public static final String ACTION_START_PLAY="ACTION_START_PLAY";
    public static final String ACTION_PAUSE_PLAY="ACTION_PAUSE_PLAY_MUSIC";
    public static final String ACTION_START_DOWNLOAD="ACTION_START_DOWNLOAD";
    public static final String ACTION_PAUSE_DOWNLOAD="ACTION_PAUSE_DOWNLOAD";
    public static final String ACTION_UPDATE="ACTION_UPDATE";
    public static final String ACTION_FINISHED="ACTION_FINISHED";
    public static final String ACTION_UPDATE_TIME="ACTION_UPDATE_TIME";
    public static final String ACTION_PLAY_CURR_MUSIC="ACTION_PLAY_CURR_MUSIC";
    public static final String ACTION_PLAY_ONLINE_MUSIC="ACTION_PLAY_ONLINE_MUSIC";
    public static final String ACTION_PLAY_LOCAL_MUSIC="ACTION_PLAY_LOCAL_MUSIC";
    public static final String ACTION_PLAY_PLAYLIST_MUSIC="ACTION_PLAY_PLAYLIST_MUSIC";
    public static final String ACTION_DELETE_LOCALMUSIC="ACTION_DELET_LOCALMUSIC";
    public static final String ACTION_DELETE_PLAYLIST_MUSIC="ACTION_DELETE_PLAYLIST_MUSIC";
    public static final String ACTION_PLAY_NEXT="ACTION_PLAY_NEXT";
    public static final String ACTION_PLAY_ALL_LOCALMUSIC="ACTION_PLAY_ALL_LOCALMUSIC";
    public static final String ACTION_PLAY_ALL_ONLINEMUSIC="ACTION_PLAY_ALL_ONLINEMUSIC";
    public static final String ACTION_CLEAR_ALL_PLAYLIST="ACTION_CLEAR_ALL_PLAYLIST";
    public static final String ACTION_CHANGE_CIRCULATE_MODE="ACTION_CHANGE_CIRCULATE_MODE";
    public static final int ADD_TO_PLAYLIST=1;
    public static final int DELETE_FROM_PLAYLIST=2;
    public static final int MUSICPLAYER_STOP=0;
    public static final int MUSICPLAYER_PLAYING=3;
    public static final int MUSICPLAYER_PAUSED=2;
    public static final int FROM_LOCALMUSIC=0;
    public static final int FROM_ONLINEMUSIC=1;

    public static final int LIST_CIRCULATE=0;
    public static final int SINGLE_REPEAT=1;
    public static final int SHAFFULE_PLAY=2;
    public static int UPDATE_PLAY_LIST_FROM;
    public static int MUSICPLAYER_STATE;
    public static int MUSICPLAYER_CIRCULATE_MODE;
    private SQLiteDatabase db;
    private ArrayList<SongBean> localMusic_list=new ArrayList<>();
    private ArrayList<SongBean>	onlineMusic_list=new ArrayList<SongBean>();
    private ArrayList<SongBean> play_list=new ArrayList<SongBean>();
    private SongBean SongBean;
    private SongBean currSongBean=new SongBean();//当前播放的歌曲
    private SongBean nextSongBean=new SongBean();//待播放的下一首
    private SongBean deleteSongBean=new SongBean();
    private int position;
    private int currPosition;
    private String SongBeanname;
    private String singer;
    private String SongBeanUrl;
    private String currTime;
    private String totalTime;
    public static final MediaPlayer mp=new MediaPlayer();
    private int i=1;
    private boolean flag=true;
    private int bufferPercent=0;
    private Intent timeIntent;
    private Handler handler=new Handler();
    private int mCurrentProgress;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    @Override
    public void onCreate() {
        // TODO 自动生成的方法存根
        loadLocalMusicData();
        loadOnlineMusicData();
        loadPlaylistData();

        //loadPlaylistData();
        timeIntent=new Intent(ACTION_UPDATE_TIME);
        MUSICPLAYER_STATE=MUSICPLAYER_STOP;
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setOnBufferingUpdateListener(this);			//缓冲进度
        mp.setOnCompletionListener(this);
    }

    private void loadPlaylistData() {
        // TODO 自动生成的方法存根
        play_list.clear();
        System.out.println("清空play_list"+play_list.size());
        db= openOrCreateDatabase("music.db", MODE_PRIVATE, null);
        Cursor cursor=db.rawQuery("select * from playlisttb ",null);
        if (cursor!=null) {
            while (cursor.moveToNext()) {
                SongBean SongBean =new SongBean();
                SongBean.setSongName(cursor.getString(cursor.getColumnIndex("SongBeanname")));
                SongBean.setSinger(cursor.getString(cursor.getColumnIndex("singer")));
                SongBean.setPath(cursor.getString(cursor.getColumnIndex("SongBeanurl")));
                play_list.add(SongBean);
                System.out.println("往MusicService的play_list添加了歌曲"+SongBean.getSongName());
            }
        }
        cursor.close();
        db.close();
        System.out.println("已往play_list添加数据"+play_list.size());
    }
    private void loadOnlineMusicData() {
        // TODO 自动生成的方法存根
        db= openOrCreateDatabase("music.db", MODE_PRIVATE, null);
        Cursor cursor=db.rawQuery("select * from onlinemusictb ",null);
        if (cursor!=null) {
            while (cursor.moveToNext()) {
                SongBean SongBean =new SongBean();
                SongBean.setSongName(cursor.getString(cursor.getColumnIndex("SongBeanname")));
                SongBean.setSinger(cursor.getString(cursor.getColumnIndex("singer")));
                SongBean.setPath(cursor.getString(cursor.getColumnIndex("SongBeanurl")));
                onlineMusic_list.add(SongBean);
            }
        }
        cursor.close();
        db.close();
    }
    private void loadLocalMusicData() {
        // TODO 自动生成的方法存根
        localMusic_list.clear();
        db= openOrCreateDatabase("music.db", MODE_PRIVATE, null);
        Cursor cursor=db.rawQuery("select * from localmusictb ",null);
        if (cursor!=null) {
            while (cursor.moveToNext()) {
                SongBean SongBean =new SongBean();
                SongBean.setSongName(cursor.getString(cursor.getColumnIndex("SongBeanname")));
                SongBean.setSinger(cursor.getString(cursor.getColumnIndex("singer")));
                SongBean.setPath(cursor.getString(cursor.getColumnIndex("SongBeanurl")));
                localMusic_list.add(SongBean);
            }
        }
        cursor.close();
        db.close();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO 自动生成的方法存根
        if(intent.getAction().equals(ACTION_TEST)){
            System.out.println("测试");
        }else if(intent.getAction().equals(ACTION_PLAY_CURR_MUSIC)){
            System.out.println("播放当前播放栏的音乐");
            MUSICPLAYER_STATE=MUSICPLAYER_PLAYING;
            loadCurrMusic();
        }else if(intent.getAction().equals(ACTION_PLAY_LOCAL_MUSIC)){
            position=intent.getIntExtra("position", 0);
            SongBean SongBean=localMusic_list.get(position);
            playMusic(SongBean);
        }else if(intent.getAction().equals(ACTION_PLAY_ONLINE_MUSIC)){
            position=intent.getIntExtra("position", 0);
            SongBean SongBean=onlineMusic_list.get(position);
            playMusic(SongBean);
        }else if(intent.getAction().equals(ACTION_PLAY_PLAYLIST_MUSIC)){
            loadPlaylistData();
            position=intent.getIntExtra("position", 0);
            SongBean SongBean=play_list.get(position);
            playMusic(SongBean);
        }else if(intent.getAction().equals(ACTION_DELETE_LOCALMUSIC)){
            loadLocalMusicData();
        }else if(intent.getAction().equals(ACTION_START_PLAY)){
            System.out.println("开始播放");
            MUSICPLAYER_STATE=MUSICPLAYER_PLAYING;
            mp.start();
            flag=true;
        }else if(intent.getAction().equals(ACTION_PAUSE_PLAY)){
            MUSICPLAYER_STATE=MUSICPLAYER_PAUSED;
            mp.pause();
            flag=false;
            addToPlayBarInfo();
        }else if(intent.getAction().equals(ACTION_PLAY_NEXT)){
            playNext();
        }else if(intent.getAction().equals(ACTION_PLAY_ALL_LOCALMUSIC)){
            SongBean SongBean=localMusic_list.get(0);
            System.out.println("播放全部本地歌曲，第一首"+SongBean.getSongName());
            playMusic(SongBean);
        }else if(intent.getAction().equals(ACTION_PLAY_ALL_ONLINEMUSIC)){
            SongBean SongBean=onlineMusic_list.get(0);
            System.out.println("播放全部网络歌曲，第一首"+SongBean.getSongName());
            playMusic(SongBean);
        }else if(intent.getAction().equals(ACTION_CLEAR_ALL_PLAYLIST)){
            play_list.clear();
            System.out.println("MusicService里面的play_list已经清空，当前size为"+play_list.size());
        }else if(intent.getAction().equals(ACTION_CHANGE_CIRCULATE_MODE)){
            System.out.println("随着播放模式的改变，下一首播放的歌曲也要做出调整");
            System.out.println("当前播放的歌曲为"+currSongBean.getSongName());
            getNextSongBean(currSongBean);
        }else if(intent.getAction().equals(ACTION_DELETE_PLAYLIST_MUSIC)){
            String SongBeanUrl=intent.getStringExtra("SongBeanUrl");
            System.out.println("删除歌曲的SongBeanUrl为"+SongBeanUrl);
            System.out.println("待播放歌曲的SongBeanUrl为"+nextSongBean.getPath());
            if(SongBeanUrl.equals(nextSongBean.getPath())){
                System.out.println("删中了下一首待播歌曲");
                //因为nextSongBean还有用，所以
                //nextSongBean=getNewNextSongBean(nextSongBean);
                getNextSongBean(nextSongBean);
                System.out.println("得到下一首待播歌曲后，再删除Servic中的play_list相关歌曲，删除前列表size"+play_list.size());
                //play_list.remove(nextSongBean);
                System.out.println("删后列表size"+play_list.size());
            }
            loadPlaylistData();
        }

        return super.onStartCommand(intent, flags, startId);
    }


    private void loadCurrMusic() {
        // TODO 自动生成的方法存根
        db= openOrCreateDatabase("music.db", MODE_PRIVATE, null);
        Cursor cursor=db.rawQuery("select * from playerbarinfotb where _id=1",null);
        if(cursor.moveToNext()){
            SongBean=new SongBean();
            SongBeanname=cursor.getString(cursor.getColumnIndex("SongBeanname"));
            singer=cursor.getString(cursor.getColumnIndex("singer"));
            SongBeanUrl=cursor.getString(cursor.getColumnIndex("SongBeanurl"));
            currTime=cursor.getString(cursor.getColumnIndex("currtime"));
            totalTime=cursor.getString(cursor.getColumnIndex("totaltime"));
            int progress=cursor.getInt(cursor.getColumnIndex("seekbarprogress"));
            int music_length=cursor.getInt(cursor.getColumnIndex("seekbarmax"));
            MUSICPLAYER_CIRCULATE_MODE=cursor.getInt(cursor.getColumnIndex("circulate"));
            System.out.println("MusicService获取的循环模式为"+MUSICPLAYER_CIRCULATE_MODE);
            SongBean.setSongName(SongBeanname);
            SongBean.setSinger(singer);
            SongBean.setPath(SongBeanUrl);
            playMusicWithProgress(SongBean, progress);
        }else{
            //如果没有当前未完成的播放任务要继续，就播放Play_list里的第一首
            loadPlaylistData();
            position=0;
            SongBean=play_list.get(position);

        }
    }
    private void playMusicWithProgress(SongBean SongBean,int progress){
        currSongBean=SongBean;
        SongBeanname=currSongBean.getSongName();
        singer=currSongBean.getSinger();
        SongBeanUrl=currSongBean.getPath();
        System.out.println("播放歌曲:"+SongBeanname+" "+singer+" "+SongBeanUrl);
        flag=false;
        mp.reset();
        try {
            mp.setDataSource(SongBeanUrl);
            mp.prepare();
            mp.seekTo(progress);
            getNextSongBean(currSongBean);
            mp.start();
            flag=true;
            MUSICPLAYER_STATE=MUSICPLAYER_PLAYING;
            updateSeekBar();
            //getNextSongBean(SongBean);
        } catch (IllegalArgumentException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playMusic(SongBean SongBean) {
        // TODO 自动生成的方法存根
        currSongBean=SongBean;
        SongBeanname=SongBean.getSongName();
        singer=SongBean.getSinger();
        SongBeanUrl=SongBean.getPath();
        System.out.println("播放歌曲:"+SongBeanname+" "+singer+" "+SongBeanUrl);
        flag=false;
        mp.reset();
        try {
            mp.setDataSource(SongBeanUrl);
            mp.prepare();
            getNextSongBean(SongBean);
            mp.start();
            flag=true;
            MUSICPLAYER_STATE=MUSICPLAYER_PLAYING;
            updateSeekBar();
            //getNextSongBean(SongBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getNextSongBean(SongBean SongBean){
        switch (MUSICPLAYER_CIRCULATE_MODE) {
            case LIST_CIRCULATE:
                System.out.println("当前传入的歌曲为"+SongBean.getSongName());
                System.out.println("当前play_list的size:"+play_list.size());
                //loadPlaylistData();
                //如果play_list小于2 那就等于当前播放歌曲
                if(play_list.size()==0){
                    nextSongBean=currSongBean;
                    System.out.println("play_list没有歌，所以下一首待播放的还是当前歌曲"+nextSongBean.getSongName());
                }else if(play_list.size()==1){
                    nextSongBean=play_list.get(0);
                    System.out.println("play_list只有一首歌，目前播放的是播放列表里的最后一首，所以应该播放第一首"+nextSongBean.getSongName());
                }else{
                    System.out.println("传入歌曲的SongBeanUrl:"+SongBean.getPath());
                    for(int i=0;i<play_list.size();i++){
                        System.out.println("play_list里的歌曲SongBeanUrl:"+play_list.get(i).getPath());
                        if(SongBean.getPath().equals(play_list.get(i).getPath())){
                            if((i+1)<play_list.size()){
                                nextSongBean=play_list.get(i+1);
                            }else{
                                nextSongBean=play_list.get(0);
                            }
                            System.out.println("下一首待播放的歌曲为"+nextSongBean.getSongName());
                            break;
                        }else{
                            nextSongBean=play_list.get(0);
                        }
                    }
                    System.out.println("play_list的size大于3，下一首待播放的歌曲为:"+nextSongBean.getPath());
                }
                break;
            case SINGLE_REPEAT:
                nextSongBean=SongBean;
                break;
            case SHAFFULE_PLAY:
                Random random=new Random();
                position=random.nextInt(play_list.size()-1);
                System.out.println("随机播放——即将播放"+play_list.get(position).getSongName());
                nextSongBean=play_list.get(position);
                break;

            default:
                break;
        }
    }

    public SongBean getNewNextSongBean(SongBean SongBean){
        int newNextSongBeanPosition=0;
        String SongBeanUrl=SongBean.getPath();
        for(int i=0;i<play_list.size();i++){
            if(SongBeanUrl.equals(play_list.get(i).getPath())){
                //符合以上条件 就是需要删除的歌曲
                newNextSongBeanPosition=i;
            }
        }
        return play_list.get(newNextSongBeanPosition);
    }

    public void playNext(){
        if(MUSICPLAYER_STATE==MUSICPLAYER_STOP){
            //播放当前状态栏的音乐
            loadCurrMusic();
        }
		/*
		loadPlaylistData();
		for(int i=0;i<play_list.size();i++){
			if(SongBeanUrl.equals(play_list.get(i).getPath())){
				position=i;
				System.out.println("此时播放"+play_list.get(position).getSongName());
				
			}
		}
		*/
        else{
            switch (MUSICPLAYER_CIRCULATE_MODE) {
                case LIST_CIRCULATE:
                    playMusic(nextSongBean);
                    System.out.println("列表循环，调用下一首");
			/*
				position++;
			if(position<play_list.size()){
				System.out.println("循环播放——即将播放"+play_list.get(position).getSongName());
			}else {
				position=0;
				System.out.println("循环播放——即将播放"+play_list.get(position).getSongName());
			}
			*/
                    break;
                case SINGLE_REPEAT:
                    playMusic(nextSongBean);
			/*
			position++;
			if(position<play_list.size()){
				System.out.println("循环播放——即将播放"+play_list.get(position).getSongName());
			}else {
				position=0;
				System.out.println("循环播放——即将播放"+play_list.get(position).getSongName());
			}
			*/
                    break;
                case SHAFFULE_PLAY:
                    Random random=new Random();
                    position=random.nextInt(play_list.size()-1);
                    System.out.println("随机播放——即将播放"+play_list.get(position).getSongName());
                    SongBean=play_list.get(position);
                    playMusic(SongBean);
                    break;
                default:
                    break;
            }

        }
        //SongBean=play_list.get(position);
        //playMusic(SongBean);
    }

    public void onDestory(){
        super.onDestroy();
        System.out.println("MusicService被停止");
    }
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        // TODO 自动生成的方法存根
        this.bufferPercent=percent*mp.getDuration()/100;
        int mCurrentProgress =  100* mp.getCurrentPosition() / mp.getDuration();
    }

    public void updateSeekBar(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    // TODO 自动生成的 catch 块
                    e1.printStackTrace();
                }
                //flag=true;
                while(flag){
                    if(mp.getCurrentPosition()<mp.getDuration()){
                        int progress=mp.getCurrentPosition();
                        int music_length=mp.getDuration();
                        int curr=mp.getCurrentPosition();
                        totalTime=toTime(music_length);
                        currTime=toTime(progress);
                        timeIntent.putExtra("MUSICPLAYER_STATE",MUSICPLAYER_STATE);
                        timeIntent.putExtra("bufferPercent", bufferPercent);
                        timeIntent.putExtra("progress", progress);
                        timeIntent.putExtra("music_length", music_length);
                        timeIntent.putExtra("totalTime", totalTime);
                        timeIntent.putExtra("currTime", currTime);
                        timeIntent.putExtra("SongBeanname", SongBeanname);
                        timeIntent.putExtra("singer", singer);
                        timeIntent.putExtra("curr", curr);
                        timeIntent.putExtra("SongBeanUrl", SongBeanUrl);
                        sendBroadcast(timeIntent);
                    }else{
                        flag = false;
                    }
                }

            }
        }).start();

    }

    private String toTime(int time){
        int minute = time / 1000 / 60;
        int s = time / 1000 % 60;
        String mm = null;
        String ss = null;
        if(minute<10)mm = "0" + minute;
        else mm = minute + "";
        if(s <10)ss = "0" + s;
        else ss = "" + s;
        return mm + ":" + ss;
    }

    private void addToPlayBarInfo() {
        // TODO 自动生成的方法存根
        db= openOrCreateDatabase("music.db", MODE_PRIVATE, null);
        db.execSQL("create table if not exists playerbarinfotb (_id integer primary key autoincrement, "
                + "SongBeanname text not null,singer text not null,SongBeanurl text not null, seekbarmax integer not null,seekbarprogress integer not null,currtime text not null,totaltime text not null,circulate text not null)");
        ContentValues values=new ContentValues();
        values.put("SongBeanname", SongBeanname);
        values.put("singer", singer);
        values.put("SongBeanurl", SongBeanUrl);
        values.put("seekbarmax", mp.getDuration());
        values.put("seekbarprogress", mp.getCurrentPosition());
        values.put("currtime", currTime);
        values.put("totaltime", totalTime);
        values.put("circulate",MUSICPLAYER_CIRCULATE_MODE);
        db.update("playerbarinfotb", values, "_id=?", new String[]{"1"});
        values.clear();
        db.close();
    }

    /*
     * 该方法是为了获得当前播放的歌曲在播放列表中的位置
     */
    public int getCurrPosition(SongBean SongBean){
        loadPlaylistData();
        for(int i=0;i<play_list.size();i++){
            if(SongBeanUrl.equals(play_list.get(i).getPath())){
                currPosition=i;
                System.out.println(play_list.get(currPosition).getSongName()+"播放完毕");
            }
        }
        return 0;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // TODO 自动生成的方法存根
		/*
		loadPlaylistData();
		for(int i=0;i<play_list.size();i++){
			if(SongBeanUrl.equals(play_list.get(i).getPath())){
				currPosition=i;
				System.out.println(play_list.get(currPosition).getSongName()+"播放完毕");
			}
		}
		switch (MUSICPLAYER_CIRCULATE_MODE) {
		case LIST_CIRCULATE:
				currPosition++;
			if(currPosition<play_list.size()){
				System.out.println("循环播放——即将播放"+play_list.get(currPosition).getSongName());
			}else {
				currPosition=0;
				System.out.println("循环播放——即将播放"+play_list.get(currPosition).getSongName());
			}
			break;
		case SINGLE_REPEAT:
			System.out.println("单曲循环——即将播放"+play_list.get(currPosition).getSongName());
			break;
		case SHAFFULE_PLAY:
			Random random=new Random();
			currPosition=random.nextInt(play_list.size()-1)+1;
			System.out.println("随机播放——即将播放"+play_list.get(currPosition).getSongName());
			break;
		default:
			break;
		}
		SongBean=play_list.get(currPosition);
		*/
        playMusic(nextSongBean);
    }


}
