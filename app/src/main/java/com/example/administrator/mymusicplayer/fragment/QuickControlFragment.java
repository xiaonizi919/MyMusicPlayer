package com.example.administrator.mymusicplayer.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.activity.PlayActivity;
import com.example.administrator.mymusicplayer.base.MyBaseFragment;
import com.example.administrator.mymusicplayer.bean.SongBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 底部常驻控制栏 相关逻辑在这里处理
 */

public class QuickControlFragment extends MyBaseFragment {
    @Bind(R.id.lin_mini_play)
    LinearLayout mLinPlay;
    @Bind(R.id.mini_geshou)
    TextView mSinger;
    @Bind(R.id.mini_name)
    TextView mSongName;
    @Bind(R.id.mini_play)
    ImageView mPlay;
    @Bind(R.id.mini_next)
    ImageView mNext;

    private int state;
    private SongBean songBean;

    public static  QuickControlFragment newInstance(){
        return new QuickControlFragment();
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.layout_miniplayer;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initListener() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(SongBean songBean) {
        state = 1;
        mPlay.setImageResource(R.mipmap.pause_unselected);
        if (null != songBean) {
            mSongName.setText(songBean.getSongName());
            mSinger.setText(songBean.getSinger());
        }
    }

    @OnClick({R.id.lin_mini_play,R.id.mini_play,R.id.mini_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.lin_mini_play:
                startActivity(PlayActivity.class);
//                getActivity().overridePendingTransition(R.anim.in_from_bottom,R.anim.out_to_bottom);
                break;
            case R.id.mini_play:
                if (state == 1){
                    state = 0;
                    mPlay.setImageResource(R.mipmap.play_unselected);
                }else {
                    state = 1;
                    mPlay.setImageResource(R.mipmap.pause_unselected);
                }
                break;
            case R.id.mini_next:

                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
