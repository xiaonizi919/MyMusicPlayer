package com.example.administrator.mymusicplayer.utils.sharepre;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Administrator on 2017/5/6.
 */

public class ShareUtils {
    public final static String SHARED_KEY = "MusicPrefFile";

    public static final String KEY_FIRST_USE = "first_use";
    /**
     * 第一次调用本地音乐
     */
    public static boolean isFirstUse(Activity act) {
        return act.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE).getBoolean(KEY_FIRST_USE, true);
    }

    public static void putFirstUse(Activity act) {
        act.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE).edit().putBoolean(KEY_FIRST_USE, false).commit();
    }
}
