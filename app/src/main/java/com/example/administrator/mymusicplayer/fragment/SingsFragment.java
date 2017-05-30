package com.example.administrator.mymusicplayer.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.activity.MainActivity;
import com.example.administrator.mymusicplayer.activity.PlayActivity;
import com.example.administrator.mymusicplayer.adapter.NetMusicAdapter;
import com.example.administrator.mymusicplayer.base.MyBaseFragment;
import com.example.administrator.mymusicplayer.bean.SongBean;
import com.example.administrator.mymusicplayer.config.MyConfig;
import com.example.administrator.mymusicplayer.config.NetConfig;
import com.example.administrator.mymusicplayer.service.PlayService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 歌单
 */

public class SingsFragment extends MyBaseFragment implements AdapterView.OnItemClickListener{

    @Bind(R.id.lv)
    ListView mListView;

    private List<SongBean> mList;
    private NetMusicAdapter musicAdapter;

    private int curIndex=-1,tarIndex;

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_list_view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add(new SongBean("张碧晨&杨宗纬","凉凉", NetConfig.LIANG_LIANG,"2"));
        mList.add(new SongBean("朱亚文&王丽坤","漂洋过海来看你", NetConfig.PIAO_YANG_GUOHAI,"2"));
        mList.add(new SongBean("李玉刚","刚好遇见你", NetConfig.GANG_HAO_YU_JIAN_NI,"2"));
        mList.add(new SongBean("张信哲","信仰", NetConfig.XIN_YANG,"2"));
        musicAdapter = new NetMusicAdapter(getContext(),mList);
        mListView.setAdapter(musicAdapter);
    }

    @Override
    protected void initListener() {
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        startPlay(1,mList.get(position).getPath());
        if (curIndex == -1){
            if (MainActivity.songList.size() > 0) {
                MainActivity.songList.clear();
            }
            MainActivity.songList.addAll(mList);
            EventBus.getDefault().post(PlayService.TO_UPDATE_MUSICLIST);
            Log.e("TAG",MainActivity.songList.toString());
        }
        tarIndex = position;
        if (curIndex == tarIndex){
            startActivity(PlayActivity.class);
        }else {
            Intent intent = new Intent(getActivity(), PlayService.class);
            intent.setAction(PlayService.ACTION_PLAY_LIST);
            intent.putExtra(MyConfig.position, position);
            getActivity().startService(intent);
            musicAdapter.setSelectPosition(position);
            startActivity(PlayActivity.class);
            curIndex = tarIndex;
        }
    }
}
