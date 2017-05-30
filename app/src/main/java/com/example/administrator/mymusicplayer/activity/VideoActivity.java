package com.example.administrator.mymusicplayer.activity;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.base.MyBaseActivity;
import com.example.administrator.mymusicplayer.service.PlayService;
import com.shuyu.gsyvideoplayer.listener.StandardVideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;

public class VideoActivity extends MyBaseActivity {
    @Bind(R.id.video_player)
    StandardGSYVideoPlayer mVideoPlayer;
    private OrientationUtils mOrientationUtils;
    @Bind(R.id.activity_video)
    RelativeLayout mRelativeLayout;

    private boolean isPlay;
    private boolean isFull;
    private String url;

    @Override
    protected void initData() {

        EventBus.getDefault().post(PlayService.ACTION_PAUSE);
        url = getIntent().getStringExtra("data");

        mOrientationUtils = new OrientationUtils(this, mVideoPlayer);
        //初始化不打开外部的旋转
        mOrientationUtils.setEnable(false);
        mVideoPlayer.setIsTouchWiget(false);
        //打开自动旋转
        mVideoPlayer.setRotateViewAuto(true);
        mVideoPlayer.setLockLand(false);
        mVideoPlayer.setShowFullAnimation(false);
        //非全屏下，不显示title
        mVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //打开非全屏下触摸效果
        mVideoPlayer.setIsTouchWiget(true);
        //全屏
        mVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                mOrientationUtils.resolveByClick();
                //第一个true隐藏actionBar，第二个隐藏statusBar
                mVideoPlayer.startWindowFullscreen(VideoActivity.this, true, true);
            }
        });
        mVideoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toNormal();
            }
        });
        mVideoPlayer.setStandardVideoAllCallBack(new StandardVideoAllCallBack() {
            @Override
            public void onClickStartThumb(String url, Object... objects) {

            }

            @Override
            public void onClickBlank(String url, Object... objects) {

            }

            @Override
            public void onClickBlankFullscreen(String url, Object... objects) {

            }

            @Override
            public void onPrepared(String url, Object... objects) {
                mOrientationUtils.setEnable(true);
                isPlay=true;
            }

            @Override
            public void onClickStartIcon(String url, Object... objects) {

            }

            @Override
            public void onClickStartError(String url, Object... objects) {

            }

            @Override
            public void onClickStop(String url, Object... objects) {

            }

            @Override
            public void onClickStopFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickResume(String url, Object... objects) {

            }

            @Override
            public void onClickResumeFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbar(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbarFullscreen(String url, Object... objects) {

            }

            @Override
            public void onAutoComplete(String url, Object... objects) {

            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                if (null!=mOrientationUtils)
                    mOrientationUtils.backToProtVideo();
            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onEnterSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekVolume(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekPosition(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekLight(String url, Object... objects) {

            }

            @Override
            public void onPlayError(String url, Object... objects) {

            }
        });
    }


    private void toNormal() {
        isFull = false;
        mOrientationUtils.setEnable(false);
        int delay = mOrientationUtils.backToProtVideo();
        new Handler().postDelayed(new Runnable() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(mRelativeLayout);
                setViewHeight(mVideoPlayer, ViewGroup.LayoutParams.MATCH_PARENT,
                        350);
            }
        }, delay);
    }

    @Override
    protected void setData() {
        //设置播放url，第一个url，第二个开始缓存，第三个使用默认缓存路径，第四个设置title
        mVideoPlayer.setUp(url,true,null,"");
        //立即播放
        mVideoPlayer.startPlayLogic();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_video;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!mVideoPlayer.isIfCurrentIsFullscreen()) {
                    mVideoPlayer.startWindowFullscreen(this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (mVideoPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
            }
        }
    }

    private  void setViewHeight(View view, int width, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (null == layoutParams)
            return;
        layoutParams.width = width;
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoPlayer.onVideoResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mVideoPlayer.onVideoPause();
    }

    @Override
    public void onBackPressed() {
        if (mOrientationUtils != null) {
            mOrientationUtils.backToProtVideo();
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        mVideoPlayer.onVideoPause();
        super.onBackPressed();
    }
}
