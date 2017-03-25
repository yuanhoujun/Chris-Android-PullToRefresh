package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;

import com.handmark.pulltorefresh.library.internal.LoadingLayout;

/**
 * 请描述使用该类使用方法！！！
 *
 * @author Scott Smith 2017-03-24 15:24
 */
public abstract class BaseHeaderLayout extends LoadingLayout {
    public BaseHeaderLayout(Context context, PullToRefreshBase.Mode mode,
                            PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        init();
    }

    public BaseHeaderLayout(Context context , PullToRefreshBase.Orientation scrollDirection) {
        this(context , PullToRefreshBase.Mode.PULL_FROM_START , scrollDirection , null);
    }

    private void init() {
        removeAllViews();
    }

    @Override
    public int getContentSize() {
        return getHeight();
    }

    @Override
    protected final int getDefaultDrawableResId() {
        return 0;
    }

    @Override
    public final void onPull(float scaleOfLayout) {
        onPullImpl(scaleOfLayout);
    }

    @Override
    public final void refreshing() {
        refreshingImpl();
    }

    @Override
    public final void reset() {
        resetImpl();
    }

    public void setPullLabel(CharSequence pullLabel) {}

    public void setRefreshingLabel(CharSequence refreshingLabel) {}

    public void setReleaseLabel(CharSequence releaseLabel) {}
}
