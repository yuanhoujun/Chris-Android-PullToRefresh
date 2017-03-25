package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.internal.LoadingLayout;

/**
 * 请描述使用该类使用方法！！！
 *
 * @author Scott Smith 2017-03-24 14:30
 */
public abstract class PullToRefreshBaseV1<T extends View> extends PullToRefreshBase<T> {
    public PullToRefreshBaseV1(Context context) {
        super(context);
    }

    public PullToRefreshBaseV1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshBaseV1(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshBaseV1(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    protected LoadingLayout createLoadingLayout(Context context, Mode mode, TypedArray attrs) {
        if(Mode.PULL_FROM_START == mode) {
            return createHeaderLayout(context);
        } else {
            return createFooterLayout(context);
        }
    }

    public abstract BaseHeaderLayout createHeaderLayout(Context context);

    public abstract BaseFooterLayout createFooterLayout(Context context);
}
