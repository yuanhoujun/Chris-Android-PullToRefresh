package com.handmark.pulltorefresh.library.util;

import android.util.Log;

import com.handmark.pulltorefresh.library.SuperRecyclerView;

/**
 * 请描述使用该类使用方法！！！
 *
 * @author Scott Smith 2017-03-25 17:51
 */
public class Logger {
    private static final String TAG = SuperRecyclerView.class.getSimpleName();
    private static final boolean DEBUG = true;

    public static final void e(String message) {
        if(DEBUG) {
            Log.e(TAG , message);
        }
    }
}
