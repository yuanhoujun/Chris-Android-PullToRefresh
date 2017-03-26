package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.internal.Utils;
import com.handmark.pulltorefresh.library.util.Logger;

/**
 * 请描述使用该类使用方法！！！
 *
 * @author Scott Smith 2017-03-24 15:15
 */
public class DefaultHeaderLayout extends BaseHeaderLayout {
    private ImageView mIndictorImage;
    private TextView mIndictorText;
    private LinearLayout mInnerLayout;
    private AnimationDrawable mDrawable;

    public DefaultHeaderLayout(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        mInnerLayout = new LinearLayout(context);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT ,
                (int) Utils.dp2px(getContext() , 60));
        lp.gravity = Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM;
        mInnerLayout.setLayoutParams(lp);
        mInnerLayout.setGravity(Gravity.CENTER_VERTICAL);
        mInnerLayout.setOrientation(LinearLayout.HORIZONTAL);

        mIndictorImage = new ImageView(context);
        mIndictorImage.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        mInnerLayout.addView(mIndictorImage);

        mIndictorText = new TextView(context);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.leftMargin = (int) Utils.dp2px(getContext() , 10f);
        mIndictorText.setLayoutParams(lp1);
        mIndictorText.setTextSize(TypedValue.COMPLEX_UNIT_DIP , 14f);
        mIndictorText.setTextColor(Color.WHITE);
        mInnerLayout.addView(mIndictorText);

        addView(mInnerLayout);

        resetUI();
    }

    private void resetUI() {
        if(null != mIndictorText) {
            mIndictorText.setText(R.string.pull_down_to_refresh);
        }
        if(null != mIndictorImage) {
            mIndictorImage.setBackgroundResource(R.drawable.tableview_pull_refresh_arrow_down);
        }
        if(null != mDrawable) {
            mDrawable.stop();
        }
    }

    @Override
    public int getContentSize() {
        return mInnerLayout.getHeight();
    }

    @Override
    public void setLastUpdatedLabel(CharSequence label) {
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
        Logger.e("scaleOfLayout = " + scaleOfLayout);
    }

    @Override
    protected void pullToRefreshImpl() {
        mIndictorText.setText(R.string.pull_down_to_refresh);
    }

    @Override
    protected void refreshingImpl() {
        mIndictorImage.setBackgroundResource(R.drawable.default_loading);

        if(null == mDrawable) {
            mDrawable = (AnimationDrawable) mIndictorImage.getBackground();
        }
        if(!mDrawable.isRunning()) {
            mDrawable.start();
        }
        mIndictorText.setText(R.string.loading);
    }

    @Override
    protected void releaseToRefreshImpl() {
        mIndictorText.setText(R.string.release_to_refresh);
        mIndictorImage.setBackgroundResource(R.drawable.tableview_pull_refresh_arrow_up);
    }

    @Override
    protected void resetImpl() {
        resetUI();
    }
}
