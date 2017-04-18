package com.example.administrator.mymusicplayer.utils;

/**
 * Created by lkq on 2017/4/18.
 */

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.administrator.mymusicplayer.bean.SongBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 音乐工具类,
 */
public class MusicUtils {

    public interface ScanCallback{
        void scanProgress(int songCount);
        void onComplete(int sumCount);
    }
    /**
     * 扫描系统里面的音频文件，返回一个list集合
     */
    public static List<SongBean> getMusicData(Context context,ScanCallback scanCallback) {
        List<SongBean> list = new ArrayList<SongBean>();
        // 媒体库查询语句（写一个工具类MusicUtils）
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        if (cursor != null) {
            int count = 0;
            while (cursor.moveToNext()) {
                SongBean song = new SongBean();
                song.setSongName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                song.setSinger(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                song.setPath( cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                song.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                song.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
                if (song.getSize() > 1000 * 800) {
                    // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                    if (song.getSongName().contains("-")) {
                        String[] str = song.getSongName().split("-");
                        song.setSinger(str[0]);
                        song.setSongName(str[1]);
                    }
                    scanCallback.scanProgress(count);
                    count++;
                    list.add(song);
                }
            }
            scanCallback.onComplete(count);
            // 释放资源
            cursor.close();
        }

        return list;
    }

    /**
     * 定义一个方法用来格式化获取到的时间
     */
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;

        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }

    }
}
