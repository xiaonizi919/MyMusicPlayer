package com.example.administrator.mymusicplayer.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.base.MyBaseAdapter;
import com.example.administrator.mymusicplayer.bean.SongBean;

import java.util.List;

public class NetMusicAdapter extends MyBaseAdapter<SongBean> {

    private int selectPosition = -1;
    private List<SongBean> mData;
    private Context mContext;

    public NetMusicAdapter(Context mContext, List<SongBean> mData) {
        super(mContext, mData);
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_net_music, null);
            holder = new NetMusicAdapter.ViewHolder(convertView);
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

    class ViewHolder {
        TextView song;
        TextView singer;

        ViewHolder(View view) {
            song = (TextView) view.findViewById(R.id.net_song_name);
            singer = (TextView) view.findViewById(R.id.net_song_singer);
        }
    }
}
