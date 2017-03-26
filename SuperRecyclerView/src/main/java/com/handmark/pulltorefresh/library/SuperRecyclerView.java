package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 支持下拉刷新，上拉加载更多的RecyclerView
 *
 * @author Scott Smith 2017-03-24 14:40
 */
public class SuperRecyclerView extends PullToRefreshBase<RecyclerView> {

    public SuperRecyclerView(Context context) {
        super(context);
    }

    public SuperRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public RefreshIndictorLayout createRefreshIndictorLayout(Context context) {
        return new DefaultRefreshIndictorLayout(context);
    }

    @Override
    public LoadIndictorLayout createLoadIndictorLayout(Context context) {
        return new DefaultLoadIndictorLayout(context);
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        return new RecyclerView(context, attrs);
    }

    @Override
    protected boolean isReadyForPullStart() {
        if (mRefreshableView.getChildCount() <= 0) {
            return true;
        }
        int firstVisiblePosition = mRefreshableView.getChildLayoutPosition(mRefreshableView.getChildAt(0));
        if (firstVisiblePosition == 0) {
            return mRefreshableView.getChildAt(0).getTop() == mRefreshableView.getPaddingTop();
        }
        return false;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        int lastItemPosition = mRefreshableView.getChildCount() - 1;
        View lastItemView = mRefreshableView.getChildAt(lastItemPosition);
        int lastVisiblePosition = mRefreshableView.getChildLayoutPosition(lastItemView);
        // 是否是最后一个Item View
        if (lastVisiblePosition >= 0 && lastVisiblePosition >= mRefreshableView.getAdapter().getItemCount() - 1) {
            return mRefreshableView.getChildAt(lastItemPosition).getBottom() <= mRefreshableView.getBottom();
        }
        return false;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mRefreshableView.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mRefreshableView.setAdapter(adapter);
    }
}
