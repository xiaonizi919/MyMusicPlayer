package com.example.administrator.mymusicplayer.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public abstract class MyBaseFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutRes(), null);
        ButterKnife.bind(this, view);
        initView();
        initData();
        initListener();
        return view;
    }

    /**
     * 打开activity test
     * @param _class
     */
    protected void startActivity(Class<?> _class) {
        startActivity(new Intent(getActivity(), _class));
    }

    protected void startActivityForData(Class<?> _class, String data) {
        Intent intent = new Intent(getActivity(), _class);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    protected void startActivityForDataFlag(Class<?> _class, String data, int flag) {
        Intent intent = new Intent(getActivity(), _class);
        intent.putExtra("data", data);
        intent.setFlags(flag);
        startActivity(intent);
    }

    /**
     * 绑定布局文件
     */
    protected abstract int getLayoutRes();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据/网络请求
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 吐司
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int res) {
        Toast.makeText(getActivity(), getActivity().getResources().getString(res), Toast.LENGTH_SHORT).show();
    }

    /**
     * 发送消息
     *
     * @param event event需集成BaseEvent
     */
    public final <EVENT extends BaseEvent> void sendMessage(@NonNull EVENT event) {
        EventBus.getDefault().post(event);
    }

}
