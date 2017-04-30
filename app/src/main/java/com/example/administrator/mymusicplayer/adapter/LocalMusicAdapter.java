package com.example.administrator.mymusicplayer.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.base.MyBaseAdapter;
import com.example.administrator.mymusicplayer.bean.SongBean;

import java.util.List;

/**
 * Created by liukq on 2017/4/29.
 */

public class LocalMusicAdapter extends MyBaseAdapter<SongBean> {
    public LocalMusicAdapter(Context mContext, List<SongBean> mData) {
        super(mContext, mData);
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
        if (!TextUtils.isEmpty(mData.get(position).getSinger()))
            holder.singer.setText(mData.get(position).getSinger());
        if (!TextUtils.isEmpty(mData.get(position).getSongName()))
            holder.song.setText(mData.get(position).getSongName());
        return convertView;
    }

    class ViewHolder {
        TextView song;
        TextView singer;

        ViewHolder(View view) {
            song= (TextView) view.findViewById(R.id.song_name);
            singer= (TextView) view.findViewById(R.id.song_singer);
        }
    }

}
