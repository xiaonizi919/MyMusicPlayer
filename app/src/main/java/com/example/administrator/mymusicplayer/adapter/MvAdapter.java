package com.example.administrator.mymusicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.base.MyBaseAdapter;
import com.example.administrator.mymusicplayer.bean.MvBean;

import java.util.List;

public class MvAdapter extends MyBaseAdapter<MvBean> {
    public MvAdapter(Context mContext, List<MvBean> mData) {
        super(mContext, mData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_mv,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder{
        TextView title;
        ImageView icon;

        public ViewHolder(View view) {
            icon= (ImageView) view.findViewById(R.id.iv_icon);
            title= (TextView) view.findViewById(R.id.tv_title);
        }
    }
}
