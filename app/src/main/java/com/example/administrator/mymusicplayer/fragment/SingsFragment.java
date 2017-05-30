package com.example.administrator.mymusicplayer.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.activity.PlayActivity;
import com.example.administrator.mymusicplayer.adapter.NetMusicAdapter;
import com.example.administrator.mymusicplayer.base.MyBaseFragment;
import com.example.administrator.mymusicplayer.bean.MyEvent;
import com.example.administrator.mymusicplayer.bean.SongBean;
import com.example.administrator.mymusicplayer.config.MyConfig;
import com.example.administrator.mymusicplayer.config.NetConfig;
import com.example.administrator.mymusicplayer.service.PlayService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 歌单
 */

public class SingsFragment extends MyBaseFragment implements AdapterView.OnItemClickListener {

    @Bind(R.id.lv)
    ListView mListView;

    public static List<SongBean> mList;
    private NetMusicAdapter musicAdapter;

    private int curIndex = -1, tarIndex;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                musicAdapter.setSelectPosition(curIndex);
            }
        }
    };


    @Override
    protected int getLayoutRes() {
        return R.layout.layout_list_view;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (curIndex != -1&&PlayService.playType==2) {
            musicAdapter.setSelectPosition(curIndex);
        }
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add(new SongBean("张碧晨&杨宗纬", "凉凉", NetConfig.LIANG_LIANG, "2"));
        mList.add(new SongBean("朱亚文&王丽坤", "漂洋过海来看你", NetConfig.PIAO_YANG_GUOHAI, "2"));
        mList.add(new SongBean("李玉刚", "刚好遇见你", NetConfig.GANG_HAO_YU_JIAN_NI, "2"));
        mList.add(new SongBean("张信哲", "信仰", NetConfig.XIN_YANG, "2"));
        musicAdapter = new NetMusicAdapter(getContext(), mList);
        mListView.setAdapter(musicAdapter);
    }

    @Override
    protected void initListener() {
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tarIndex = position;
        if (curIndex == tarIndex) {
            startActivity(PlayActivity.class);
        } else {
            Intent intent = new Intent(getActivity(), PlayService.class);
            intent.setAction(PlayService.ACTION_PLAY_NET);
            intent.putExtra(MyConfig.position, position);
            getActivity().startService(intent);
            musicAdapter.setSelectPosition(position);
            startActivity(PlayActivity.class);
            curIndex = tarIndex;
        }
    }

    @Subscribe
    public void event(MyEvent myEvent) {
        if (myEvent.getTarNetPos() != -1&&PlayService.playType==2) {
            curIndex = myEvent.getTarNetPos();
            handler.sendEmptyMessage(1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
