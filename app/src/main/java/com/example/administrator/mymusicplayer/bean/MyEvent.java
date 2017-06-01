package com.example.administrator.mymusicplayer.bean;

public class MyEvent {
    private int tarLocalPos=-1;//切换选中项
    private int tarNetPos=-1;//在线音乐选中项

    public int getTarLocalPos() {
        return tarLocalPos;
    }

    public void setTarLocalPos(int tarLocalPos) {
        this.tarLocalPos = tarLocalPos;
    }

    public int getTarNetPos() {
        return tarNetPos;
    }

    public void setTarNetPos(int tarNetPos) {
        this.tarNetPos = tarNetPos;
    }
}
