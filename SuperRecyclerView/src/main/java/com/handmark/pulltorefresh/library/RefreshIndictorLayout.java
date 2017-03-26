package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 刷新指示器布局基类，如果要实现自定义的刷新指示器，请继承该类实现相应方法。
 *
 * @author Scott Smith 2017-03-26 13:05
 */
public abstract class RefreshIndictorLayout extends FrameLayout {

    public RefreshIndictorLayout(@NonNull Context context) {
        super(context);
    }

    public final void setHeight(int height) {
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.height = height;
        requestLayout();
    }

    /**
     * 重写该方法返回指示器有效内容大小，{@link PullToRefreshBase}会根据这个尺寸决定释放刷新距离
     *
     * @return 有效内容尺寸
     */
    public abstract int getContentSize();

    /**
     * 在下拉刷新整个过程中，都会调用该方法
     *
     * @param scaleOfLayout 滑动距离占ContentSize的比例
     */
    public abstract void onPull(float scaleOfLayout);

    /**
     * 当滑动距离未达到刷新距离的时候，该方法会一直被调用
     */
    public abstract void onPullToRefresh();

    /**
     * 当滑动距离达到刷新距离的时候，该方法就会被调用
     */
    public abstract void onReleaseToRefresh();

    /**
     * 当滑动距离达到刷新距离，松开手指后，UI会一直处于刷新状态，该方法就会被调用
     */
    public abstract void onRefreshing();

    /**
     * 刷新操作完成后，会调用该方法进行UI重置
     */
    public abstract void onReset();
}
