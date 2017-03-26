package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import com.handmark.pulltorefresh.library.internal.LoadingLayout;

/**
 * 请描述使用该类使用方法！！！
 *
 * @author Scott Smith 2017-03-24 15:25
 */
public class BaseFooterLayout extends LoadingLayout {
    public BaseFooterLayout(Context context, PullToRefreshBase.Mode mode , TypedArray attrs) {
        super(context , mode , attrs);
        init();
    }

    public BaseFooterLayout(Context context) {
        this(context , PullToRefreshBase.Mode.PULL_FROM_START , null);
    }

    private void init() {
        removeAllViews();
    }

    @Override
    protected int getDefaultDrawableResId() {
        return 0;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

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
    protected void onPullImpl(float scaleOfLayout) {

    }

    @Override
    protected void pullToRefreshImpl() {

    }

    @Override
    protected void pullToLoadMoreImpl() {

    }

    @Override
    protected void refreshingImpl() {

    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    @Override
    protected void resetImpl() {

    }
}
