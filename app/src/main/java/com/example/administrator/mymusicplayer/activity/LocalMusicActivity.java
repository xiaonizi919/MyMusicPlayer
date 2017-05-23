package com.example.administrator.mymusicplayer.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.adapter.LocalMusicAdapter;
import com.example.administrator.mymusicplayer.base.MyBaseActivity;
import com.example.administrator.mymusicplayer.bean.SongBean;
import com.example.administrator.mymusicplayer.config.MyConfig;
import com.example.administrator.mymusicplayer.db.DatabaseUtils;
import com.example.administrator.mymusicplayer.service.PlayService;
import com.example.administrator.mymusicplayer.utils.MusicUtils;
import com.example.administrator.mymusicplayer.utils.sharepre.ShareUtils;
import com.example.administrator.mymusicplayer.widget.SideLetterBar;
import com.example.administrator.mymusicplayer.widget.alertview.AlertView;
import com.example.administrator.mymusicplayer.widget.alertview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.example.administrator.mymusicplayer.db.DatabaseUtils.getHelper;

/**
 * LocalMusicActivity:本地的音乐列表
 * 首次使用：需要读取本地的音乐资源并保存到数据库中
 * 非首次使用：直接从数据库中读取资源
 */
public class LocalMusicActivity extends MyBaseActivity {

    @Bind(R.id.local_music_lv)
    ListView mListView;
    @Bind(R.id.side_letter_bar)
    SideLetterBar mSideLetterBar;
    private List<SongBean> mSongList;
    @Bind(R.id.tv_letter_overlay)
    TextView mOverlay;
    @Bind(R.id.btn_scan)
    Button mButton;

    private AlertView mAlertView;
    private int longClickPosition = -1;
    private LocalMusicAdapter mAdapter;

    @Override
    protected void initData() {
        showQuickControl(true);
        mSongList = new ArrayList<>();
        mSideLetterBar.setOverlay(mOverlay);
    }

    @Override
    protected void setData() {
        if (ShareUtils.isFirstUse(this)) {
            mButton.setVisibility(View.VISIBLE);
            mSideLetterBar.setVisibility(View.GONE);
        } else {
            mButton.setVisibility(View.GONE);
            mSongList = DatabaseUtils.getHelper().queryAll(SongBean.class);
            mAdapter = new LocalMusicAdapter(this, mSongList);
            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void setListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LocalMusicActivity.this, PlayService.class);
                intent.setAction(PlayService.ACTION_PLAY_LIST);
                intent.putExtra(MyConfig.position, position);
                startService(intent);
                mAdapter.setSelectPosition(position);
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClickPosition = position;
                mAlertView.show();
                return true;
            }
        });
        mAlertView = new AlertView(null, "确定删除?", "取消", null, new String[]{"确定"}, this, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if (position == -1)
                    mAlertView.dismiss();
                else {//删除
                    showToast("删除" + longClickPosition);
                }
            }
        }).setCancelable(true);
        mSideLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mAdapter.getLetterPosition(letter);
                if (position != -1)
                    mListView.setSelection(position);
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_local_music;
    }

    @OnClick({R.id.btn_scan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scan:
                mButton.setEnabled(false);
                mSongList = MusicUtils.getInstance().getMusicData(LocalMusicActivity.this);
                mButton.setVisibility(View.GONE);
                mAdapter = new LocalMusicAdapter(this, mSongList);
                mListView.setAdapter(mAdapter);
                mSideLetterBar.setVisibility(View.VISIBLE);
                getHelper().saveAll(mSongList);
                ShareUtils.putFirstUse(this);
                break;
        }
    }

}
