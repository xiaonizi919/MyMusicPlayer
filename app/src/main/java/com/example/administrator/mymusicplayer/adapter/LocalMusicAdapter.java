package com.example.administrator.mymusicplayer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.activity.MainActivity;
import com.example.administrator.mymusicplayer.base.MyBaseAdapter;
import com.example.administrator.mymusicplayer.bean.SongBean;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class LocalMusicAdapter extends MyBaseAdapter<SongBean> {
    private int selectPosition = -1;
    private HashMap<String, Integer> letterIndexes;
    private String temp = "-1";

    public LocalMusicAdapter(Context mContext, List<SongBean> mData) {
        super(mContext, mData);
        letterIndexes = new HashMap<>();
        temp = "-1";
        Collections.sort(mData, new Comparator<SongBean>() {
            @Override
            public int compare(SongBean o1, SongBean o2) {
                if (((byte)(o1.getPinyin().toCharArray()[0])) > ((byte)(o2.getPinyin().toCharArray()[0]))){
                    return 1;
                }else if (((byte)(o1.getPinyin().toCharArray()[0])) < ((byte)(o2.getPinyin().toCharArray()[0]))){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        for (int i = 0 ; i < mData.size() ; i ++){
            if (!mData.get(i).getPinyin().substring(0,1).equals(temp)){
                temp = mData.get(i).getPinyin().substring(0,1);
                letterIndexes.put(mData.get(i).getPinyin().substring(0,1).toLowerCase(),i);
            }
        }
        if (MainActivity.songList.size()==0)
            MainActivity.songList.addAll(mData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_local_music, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (selectPosition==position){
            holder.song.setTextColor(Color.BLUE);
            holder.singer.setTextColor(Color.BLUE);
        }else{
            holder.song.setTextColor(Color.parseColor("#333333"));
            holder.singer.setTextColor(Color.parseColor("#333333"));
        }
        setText(mData.get(position).getSongName(), holder.song);
        setText(mData.get(position).getSinger(), holder.singer);
        return convertView;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    public int getLetterPosition(String s){
        Integer integer=letterIndexes.get(s.toLowerCase());
        return integer==null?-1:integer;
    }

    class ViewHolder {
        TextView song;
        TextView singer;

        ViewHolder(View view) {
            song = (TextView) view.findViewById(R.id.song_name);
            singer = (TextView) view.findViewById(R.id.song_singer);
        }
    }

}
