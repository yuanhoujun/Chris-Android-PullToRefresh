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
 * Default load indictor layout
 *
 * @author Scott Smith 2017-03-26 14:16
 */
public class DefaultLoadIndictorLayout extends LoadIndictorLayout {
    private ImageView mIndictorImage;
    private TextView mIndictorText;
    private LinearLayout mInnerLayout;
    private AnimationDrawable mDrawable;

    public DefaultLoadIndictorLayout(@NonNull Context context) {
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

        mIndictorImage.setBackgroundResource(R.drawable.default_loading);
        mDrawable = (AnimationDrawable) mIndictorImage.getBackground();

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
    public void onPullToLoadMore() {
        if(null != mDrawable) {
            mDrawable.start();
        }
        if(null != mIndictorText) {
            mIndictorText.setText("加载中...");
        }
    }

    @Override
    public void onReset() {
        resetUI();
    }
}
