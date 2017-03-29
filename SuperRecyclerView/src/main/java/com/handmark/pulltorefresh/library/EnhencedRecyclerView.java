package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.handmark.pulltorefresh.library.util.Logger;

/**
 * 增强的ReyclerView，增加更多自定义控制
 *
 * @author Scott Smith 2017-03-27 22:00
 */
public class EnhencedRecyclerView extends RecyclerView {
    private int mScrollY = 0;
    private boolean isPullUp = false;

    public EnhencedRecyclerView(Context context) {
        super(context);
    }

    public EnhencedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        Logger.e("@@@@scrollY = " + getScrollY());
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
    }
}
