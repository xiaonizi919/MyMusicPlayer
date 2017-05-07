package com.example.administrator.mymusicplayer.utils.sharepre;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.mymusicplayer.config.MyConfig;

import static com.example.administrator.mymusicplayer.config.MyConfig.Mode.MODE_LIST;

/**
 * Created by Administrator on 2017/5/6.
 */

public class ShareUtils {
    private final static String SHARED_KEY = "MusicPrefFile";

    private static final String KEY_FIRST_USE = "first_use";
    private static final String PLAY_MODE = "playMode";

    /**
     * 第一次调用本地音乐
     */
    public static boolean isFirstUse(Activity act) {
        return act.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE).getBoolean(KEY_FIRST_USE, true);
    }

    public static void putFirstUse(Activity act) {
        act.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE).edit().putBoolean(KEY_FIRST_USE, false).commit();
    }

    public static MyConfig.Mode getPlayMode(Activity act) {
        int modeInt = act.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE).getInt(PLAY_MODE, 1);
        switch (modeInt) {
            case 1:
                return MODE_LIST;
            case 2:
                return MyConfig.Mode.MODE_RANDOM;
            case 3:
                return MyConfig.Mode.MODE_RANDOM;
            default:
                return MODE_LIST;
        }
    }
    public static void putPlayMode(Activity act,MyConfig.Mode mode){
        int modeInt;
        switch (mode){
            case MODE_LIST:
                modeInt=1;
                break;
            case MODE_RANDOM:
                modeInt=2;
                break;
            case MODE_SINGLE:
                modeInt=3;
                break;
            default:
                modeInt=1;
                break;
        }
        act.getSharedPreferences(SHARED_KEY,Context.MODE_PRIVATE).edit().putInt(PLAY_MODE,modeInt).apply();
    }
}
