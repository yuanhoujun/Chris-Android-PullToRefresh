package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.internal.Utils;

/**
 * 请描述使用该类使用方法！！！
 *
 * @author Scott Smith 2017-03-24 15:39
 */
public class DefaultFooterLayout extends BaseFooterLayout {
    private ImageView mIndictorImage;
    private TextView mIndictorText;
    private LinearLayout mInnerLayout;
    private AnimationDrawable mDrawable;

    public DefaultFooterLayout(Context context, PullToRefreshBase.Orientation scrollDirection) {
        super(context, scrollDirection);
        initView(context);
    }

    private void initView(Context context) {
        mInnerLayout = new LinearLayout(context);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT ,
                (int) Utils.dp2px(getContext() , 60));
        lp.gravity = Gravity.CENTER_HORIZONTAL|Gravity.TOP;
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

        mIndictorImage.setBackgroundResource(R.drawable.default_loading);
        mDrawable = (AnimationDrawable) mIndictorImage.getBackground();
    }

    @Override
    public int getContentSize() {
        return mInnerLayout.getHeight();
    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
        if(null != mDrawable) {
            mDrawable.start();
        }
        if(null != mIndictorText) {
            mIndictorText.setText("加载中...");
        }
    }

    @Override
    protected void pullToRefreshImpl() {

    }

    @Override
    protected void refreshingImpl() {
    }

    @Override
    protected void releaseToRefreshImpl() {
    }

    @Override
    protected void resetImpl() {
        if(null != mDrawable) {
            mDrawable.stop();
        }
    }
}