package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.internal.Utils;

/**
 * Default refresh indictor layout
 *
 * @author Scott Smith 2017-03-26 13:45
 */
public class DefaultRefreshIndictorLayout extends RefreshIndictorLayout {
    private ImageView mIndictorImage;
    private TextView mIndictorText;
    private LinearLayout mInnerLayout;
    private AnimationDrawable mDrawable;

    public DefaultRefreshIndictorLayout(@NonNull Context context) {
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
    public void onPull(float scaleOfLayout) {

    }

    @Override
    public void onPullToRefresh() {
        mIndictorText.setText(R.string.pull_down_to_refresh);
    }

    @Override
    public void onReleaseToRefresh() {
        mIndictorText.setText(R.string.release_to_refresh);
        mIndictorImage.setBackgroundResource(R.drawable.tableview_pull_refresh_arrow_up);
    }

    @Override
    public void onRefreshing() {
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
    public void onReset() {
        resetUI();
    }
}
