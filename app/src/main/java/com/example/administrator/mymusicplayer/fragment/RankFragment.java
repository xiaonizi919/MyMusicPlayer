package com.example.administrator.mymusicplayer.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.activity.VideoActivity;
import com.example.administrator.mymusicplayer.adapter.MvAdapter;
import com.example.administrator.mymusicplayer.base.MyBaseFragment;
import com.example.administrator.mymusicplayer.bean.MvBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 排行榜
 */

public class RankFragment extends MyBaseFragment {
    @Bind(R.id.lv)
    ListView mListView;
    private List<MvBean> mList;
    private MvAdapter mAdapter;
    @Override
    protected int getLayoutRes() {
        return R.layout.layout_list_view;
    }

    @Override
    protected void initView() {
        mList=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mList.add(new MvBean());
        }

    }

    @Override
    protected void initData() {
        mAdapter=new MvAdapter(getContext(),mList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(VideoActivity.class);
            }
        });
    }
}
