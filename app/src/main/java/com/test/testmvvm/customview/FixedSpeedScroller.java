package com.test.testmvvm.customview;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class FixedSpeedScroller extends Scroller {
    private int mDuration = 1500;

    public FixedSpeedScroller(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
        // TODO Auto-generated constructor stub
    }

    // public FixedSpeedScroller(Context context, Interpolator interpolator,
    // boolean flywheel) {
    // super(context, interpolator, flywheel);
    // // TODO Auto-generated constructor stub
    // }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    /**
     * @param duration
     * @Description 设置滑动间隔
     * @author Created by qinxianyuzou on 2014-10-29.
     */

    public void setDuration(int duration) {
        mDuration = duration;
    }
}
