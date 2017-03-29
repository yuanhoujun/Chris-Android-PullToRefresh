package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.util.Logger;

/**
 * 支持下拉刷新，上拉加载更多的RecyclerView
 *
 * @author Scott Smith 2017-03-24 14:40
 */
public class SuperRecyclerView extends PullToRefreshBase<EnhencedRecyclerView> {

    public SuperRecyclerView(Context context) {
        this(context , null);
    }

    public SuperRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        initListener();
    }

    private void initListener() {
        mRefreshableView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Logger.e(">>>>>>> dy = " + dy);
                if(isReadyForPullEnd() && dy > 0) {
                    loadEvent();
                    Logger.e("已经滑动到最底部 dy = " + dy);
                }
            }
        });
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
    protected EnhencedRecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        return new EnhencedRecyclerView(context, attrs);
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
