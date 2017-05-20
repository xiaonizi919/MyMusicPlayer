package com.example.administrator.mymusicplayer.config;

/**
 * Created by liukq on 2017/5/1.
 */

public interface MyConfig {
    String position = "position";
    String progress = "progress";
    int REQUEST_STORAGE = 1;//申请存储权限的requestCode，其他权限依次向下排
    //播放模式
    enum Mode{
        MODE_LIST,
        MODE_RANDOM,
        MODE_SINGLE
    }
}
