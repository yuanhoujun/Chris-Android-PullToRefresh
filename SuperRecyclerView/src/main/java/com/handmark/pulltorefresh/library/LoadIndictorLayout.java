package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

/**
 * 加载更多指示器布局基类，如果要实现自定义的加载更多布局指示器，请继承该类型，实现相应的抽象方法。
 *
 * @author Scott Smith 2017-03-26 13:05
 */
public abstract class LoadIndictorLayout extends FrameLayout {
    public LoadIndictorLayout(@NonNull Context context) {
        super(context);
    }

    /**
     * 当控件滑动到最底部的时候，会触发加载更多操作，并调用该方法
     */
    public abstract void onPullToLoadMore();

    /**
     * 当加载更多操作完成后，会调用该方法完成UI重置
     */
    public abstract void onReset();

    /**
     * 重写该方法返回指示器有效内容大小，{@link PullToRefreshBase}会根据这个尺寸决定释放刷新距离
     *
     * @return 有效内容尺寸
     */
    public abstract int getContentSize();
}
