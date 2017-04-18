package com.example.administrator.mymusicplayer.bean;

/**
 * Created by lkq on 2017/4/18.
 */

public class SongBean {
    /**
     * 歌手
     */
    private String singer;
    /**
     * 歌曲名
     */
    private String songName;
    /**
     * 歌曲的地址
     */
    private String path;

    /**
     * 歌曲长度
     */
    private int duration;
    /**
     * 歌曲的大小
     */
    private long size;
    /**
     * 专辑名
     */
    private String album;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
