package com.example.administrator.mymusicplayer.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.administrator.mymusicplayer.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private final String TAG = this.getClass().getSimpleName();

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mData;

    public MyBaseAdapter(Context mContext, List<T> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //网络请求图片，并加载到控件上
    protected void loadImage(ImageView view, String path) {
        Picasso.with(mContext).load(path)
                .placeholder(R.mipmap.default_icon)
                .error(R.mipmap.player_error_normal).into(view);
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
