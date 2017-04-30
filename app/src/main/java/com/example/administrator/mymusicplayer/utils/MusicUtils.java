package com.example.administrator.mymusicplayer.utils;

/**
 * Created by lkq on 2017/4/18.
 */

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.example.administrator.mymusicplayer.bean.SongBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 音乐工具类,
 */
public class MusicUtils {

    private ScanCallback mScanCallback;
    private static MusicUtils mMusicUtils;

    private MusicUtils() {
    }

    public static MusicUtils getInstance() {
        if (null == mMusicUtils)
            mMusicUtils = new MusicUtils();
        return mMusicUtils;
    }

    public void setScanCallback(ScanCallback scanCallback) {
        mScanCallback = scanCallback;
    }

    public interface ScanCallback {
        void scanProgress(int songCount);

        void onComplete(int sumCount);
    }

    /**
     * 扫描系统里面的音频文件，返回一个list集合
     */
    public List<SongBean> getMusicData(Context context) {
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
                song.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                song.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                song.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
                song.setAlbumId(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                if (!TextUtils.isEmpty(song.getAlbumId()))
                    song.setAlbumArt(getAlbumArt(context, Integer.parseInt(song.getAlbumId())));
                if (song.getSize() > 1000 * 800) {
                    // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                    if (song.getSongName().contains("-")) {
                        String[] str = song.getSongName().split("-");
                        song.setSinger(str[0]);
                        song.setSongName(str[1]);
                    }
                    if (null != mScanCallback)
                        mScanCallback.scanProgress(count);
                    count++;
                    list.add(song);
                }
            }
            if (null != mScanCallback)
                mScanCallback.onComplete(count);
            // 释放资源
            cursor.close();
        }

        return list;
    }

    private String getAlbumArt(Context context, int album_id) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{"album_art"};
        Cursor cur = context.getContentResolver().query(
                Uri.parse(mUriAlbums + "/" + Integer.toString(album_id)),
                projection, null, null, null);
        String album_art = null;
        assert cur != null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }
        cur.close();
        cur = null;
        return album_art;
    }

    /**
     * 定义一个方法用来格式化获取到的时间
     */
    public String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;

        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }

    }
}
