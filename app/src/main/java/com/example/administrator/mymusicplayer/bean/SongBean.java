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

    /**
     * 专辑id
     */
    private String albumId;

    /**
     * 专辑封面
     */
    private String albumArt;
    /**
     * 播放状态
     */
    private int state;
    /**
     * 缓存状态
     */
    private int bufferPercent;
    /**
     * 播放进度
     */
    private float progress;
    /**
     * 歌曲时间
     */
    private String totalTime;
    /**
     * 当前播放时间00：00格式
     */
    private String currTime;
    /**
     * 缓存状态
     */
    private int currPosition;

    private String pinyin;


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

    @Override
    public String toString() {
        return "SongBean{" +
                "singer='" + singer + '\'' +
                ", songName='" + songName + '\'' +
                ", path='" + path + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", album='" + album + '\'' +
                ", albumId='" + albumId + '\'' +
                ", albumArt='" + albumArt + '\'' +
                '}';
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    public int getState() {
        return state;
    }

    public int getBufferPercent() {
        return bufferPercent;
    }

    public void setBufferPercent(int bufferPercent) {
        this.bufferPercent = bufferPercent;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(int currPosition) {
        this.currPosition = currPosition;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
