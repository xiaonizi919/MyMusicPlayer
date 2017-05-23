package com.example.administrator.mymusicplayer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mymusicplayer.R;
import com.example.administrator.mymusicplayer.base.MyBaseActivity;

public class MyTitleBar extends LinearLayout {
    private final String TAG = "MyTitleBar";
    private ImageView back, menu;
    private TextView title;
    private boolean showMenu;
    private String titleText;

    private onMenuClickListener mOnMenuClickListener;

    public MyTitleBar(Context context) {
        this(context, null);
    }

    public MyTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initView(context);
    }

    private void initView(final Context context) {
        setOrientation(HORIZONTAL);
        View rootView = View.inflate(context, R.layout.layout_title_bar, null);
        addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        title = (TextView) rootView.findViewById(R.id.title_bar_title);
        back = (ImageView) rootView.findViewById(R.id.title_bar_back);
        menu = (ImageView) rootView.findViewById(R.id.title_bar_menu);
        title.setText(titleText);
        menu.setVisibility(showMenu ? VISIBLE : GONE);
        //返回
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof MyBaseActivity) {
                    ((MyBaseActivity) context).finish();
                }
            }
        });
        //菜单事件
        menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnMenuClickListener)
                    mOnMenuClickListener.onMenuClick(menu);
            }
        });

    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
        if (typedArray != null) {
            titleText = typedArray.getString(R.styleable.MyTitleBar_titleText);
            showMenu = typedArray.getBoolean(R.styleable.MyTitleBar_showMenu, false);
            typedArray.recycle();
        }
    }

    public void setShowMenu(boolean showMenu) {
        menu.setVisibility(showMenu?VISIBLE:GONE);
    }

    public void setTitleText(String titleText) {
        title.setText(titleText);
    }

    public void setOnMenuClickListener(onMenuClickListener onMenuClickListener) {
        mOnMenuClickListener = onMenuClickListener;
    }

    public interface onMenuClickListener {
        void onMenuClick(View view);
    }
}
