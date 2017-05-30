package com.example.administrator.mymusicplayer.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.activity.VideoActivity;
import com.example.administrator.mymusicplayer.adapter.MvAdapter;
import com.example.administrator.mymusicplayer.base.MyBaseFragment;
import com.example.administrator.mymusicplayer.bean.MvBean;
import com.example.administrator.mymusicplayer.config.NetConfig;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * 排行榜
 */

public class RankFragment extends MyBaseFragment {
    @Bind(R.id.lv)
    ListView mListView;
    private List<MvBean.RetObjBean> mList;
    private MvAdapter mAdapter;
    private MvBean mvBean;



    @Override
    protected int getLayoutRes() {
        return R.layout.layout_list_view;
    }

    @Override
    protected void initView() {
        mList=new ArrayList<>();

    }

    @Override
    protected void initData() {
        mAdapter=new MvAdapter(getContext(),mList);
        mListView.setAdapter(mAdapter);
        getVideoData();
    }

    private void getVideoData() {

        OkHttpUtils.get()
                .url(NetConfig.BASE_VIDEO_PATH)
                .addParams("videoType", NetConfig.VIDEO_Type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mvBean = new Gson().fromJson(response,MvBean.class);
                        mList.addAll(mvBean.getRetObj());
                        mAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    protected void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivityForData(VideoActivity.class,mList.get(position).getHdUrl());
            }
        });
    }


}
