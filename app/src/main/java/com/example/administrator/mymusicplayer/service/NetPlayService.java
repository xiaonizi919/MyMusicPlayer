package com.example.administrator.mymusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class NetPlayService extends Service {

    //创建需要操作的属性
    private MediaPlayer player;//声明多媒体播放对象

    public NetPlayService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //完成多媒体播放对象的实例化
        if (player == null) {
            player = new MediaPlayer();
        }
        player.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            int type = intent.getIntExtra("type", 0);
            switch (type) {
                case 1://播放
                    try {
                        player.reset();
                        player.setDataSource(intent.getStringExtra("musicpath"));
                        player.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.start();
                    break;
                case 2://暂停
                    player.pause();
                    break;
                case 3://停止
                    player.stop();
                    stopSelf();//关闭当前的service
                    break;
            }
        }
        return START_STICKY;//粘性服务
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();//释放资源
        player = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
