package com.example.administrator.mymusicplayer.bean;


import android.os.Parcel;
import android.os.Parcelable;

public class SongBean implements Parcelable{
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

    /**
     * 是否在播放列表内
     */
    private boolean isInPlayList;
    /**
     * 来源 分为1:本地音乐 2:网络音乐等
     */
    private String source;

    public SongBean() {
    }

    public SongBean(String singer, String songName, String path,String source) {
        this.singer = singer;
        this.songName = songName;
        this.path = path;
        this.source = source;
    }

    public SongBean(String singer, String songName, String path, int duration, long size, String album, String albumId, String albumArt, int state, int bufferPercent, float progress, String totalTime, String currTime, int currPosition, String pinyin, boolean isInPlayList, String source) {
        this.singer = singer;
        this.songName = songName;
        this.path = path;
        this.duration = duration;
        this.size = size;
        this.album = album;
        this.albumId = albumId;
        this.albumArt = albumArt;
        this.state = state;
        this.bufferPercent = bufferPercent;
        this.progress = progress;
        this.totalTime = totalTime;
        this.currTime = currTime;
        this.currPosition = currPosition;
        this.pinyin = pinyin;
        this.isInPlayList = isInPlayList;
        this.source = source;
    }

    protected SongBean(Parcel in) {
        singer = in.readString();
        songName = in.readString();
        path = in.readString();
        duration = in.readInt();
        size = in.readLong();
        album = in.readString();
        albumId = in.readString();
        albumArt = in.readString();
        state = in.readInt();
        bufferPercent = in.readInt();
        progress = in.readFloat();
        totalTime = in.readString();
        currTime = in.readString();
        currPosition = in.readInt();
        pinyin = in.readString();
        isInPlayList = in.readByte() != 0;
        source = in.readString();
    }

    public static final Creator<SongBean> CREATOR = new Creator<SongBean>() {
        @Override
        public SongBean createFromParcel(Parcel in) {
            return new SongBean(in);
        }

        @Override
        public SongBean[] newArray(int size) {
            return new SongBean[size];
        }
    };

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
                ", pinyin='" + pinyin + '\'' +
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

    public boolean isInPlayList() {
        return isInPlayList;
    }

    public void setInPlayList(boolean inPlayList) {
        isInPlayList = inPlayList;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(singer);
        dest.writeString(songName);
        dest.writeString(path);
        dest.writeInt(duration);
        dest.writeLong(size);
        dest.writeString(album);
        dest.writeString(albumId);
        dest.writeString(albumArt);
        dest.writeInt(state);
        dest.writeInt(bufferPercent);
        dest.writeFloat(progress);
        dest.writeString(totalTime);
        dest.writeString(currTime);
        dest.writeInt(currPosition);
        dest.writeString(pinyin);
        dest.writeByte((byte) (isInPlayList ? 1 : 0));
        dest.writeString(source);
    }
}
