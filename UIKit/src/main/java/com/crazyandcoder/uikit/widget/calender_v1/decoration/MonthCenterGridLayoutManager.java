package com.crazyandcoder.uikit.widget.calender_v1.decoration;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/**
 * desc:
 * author: created by liji
 * date:   6/11/21 14:06
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class MonthCenterGridLayoutManager extends GridLayoutManager {
    public MonthCenterGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MonthCenterGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MonthCenterGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }


    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        RecyclerView.SmoothScroller smoothScroller = new MonthCenterGridLayoutManager.CenterSmoothScroller(recyclerView.getContext());
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

    private static class CenterSmoothScroller extends LinearSmoothScroller {

        public CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
        }

        @Override
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 30f / displayMetrics.densityDpi;
        }
    }

}
