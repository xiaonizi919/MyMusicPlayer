package com.example.administrator.mymusicplayer.application;

import android.app.Application;
import android.support.compat.BuildConfig;

import org.greenrobot.eventbus.EventBus;

public class MyApplication extends Application {
    public static EventBus mEventBus;

    @Override
    public void onCreate() {
        super.onCreate();
        initEventBus();
    }

    private void initEventBus() {
        mEventBus = EventBus.builder().throwSubscriberException(BuildConfig.DEBUG).installDefaultEventBus();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
