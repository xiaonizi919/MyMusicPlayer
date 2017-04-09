package com.example.administrator.mymusicplayer.application;

import android.app.Application;

import com.example.administrator.mymusicplayer.api.MusicService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    /**
     * 全局的api接口
     */
    public static MusicService mMusicService;
    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(MusicService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mMusicService= mRetrofit.create(MusicService.class);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
