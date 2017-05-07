package com.example.administrator.mymusicplayer.application;

import android.app.Application;

import com.example.administrator.mymusicplayer.db.DatabaseUtils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //必须先初始化
        DatabaseUtils.initHelper(this, "localmusic.db");
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
