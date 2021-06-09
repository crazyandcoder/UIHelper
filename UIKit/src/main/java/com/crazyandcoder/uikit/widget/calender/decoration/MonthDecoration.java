package com.crazyandcoder.uikit.widget.calender.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.uikit.widget.calender.callback.MonthDecorationViewCallBack;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 * author: created by liji
 * date:   6/9/21 15:19
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class MonthDecoration extends RecyclerView.ItemDecoration {

    private boolean isInitHeight;
    private boolean isStick;
    private int monthTitleHeight;
    private Map<Integer, View> monthTitleViewMap = new HashMap<>();
    private MonthDecorationViewCallBack monthDecorationViewCallBack;

    public void setMonthDecorationViewCallBack(MonthDecorationViewCallBack monthDecorationViewCallBack) {
        this.monthDecorationViewCallBack = monthDecorationViewCallBack;
    }


    public void setStick(boolean isStick) {
        this.isStick = isStick;
    }

    public interface MonthDateCallback {
        Date getMonthDate(int position);
    }

    public MonthDecoration() {

    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter instanceof MonthDateCallback) {
            MonthDateCallback monthDateCallback = (MonthDateCallback) adapter;
            if (!isInitHeight) {
                Date monthDate = monthDateCallback.getMonthDate(0);
                View monthTitleView = monthDecorationViewCallBack.getMonthDecorationView(0, monthDate);
                monthTitleView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                monthTitleHeight = monthTitleView.getMeasuredHeight();
                isInitHeight = true;
            }
        }
        //预留MonthTitleView的高度
        outRect.top = monthTitleHeight;
    }


    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter instanceof MonthDateCallback) {
            int childCount = parent.getChildCount();
            MonthDateCallback monthDateCallback = (MonthDateCallback) parent.getAdapter();
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int top = parent.getPaddingTop();
            /**
             * 粘性思路
             *
             * 1.当最后一个itemView bottom与monthTitle bottom重合时候。第二个monthTitle就到了第一个monthTitle底部
             * 2.当重合后再往上滑动时，只需要不断改变悬停monthTitle top值(itemView.bottom-monthTitleHeight)重新绘制
             * 从而视觉上感觉 第二个monthTitle往上推第一个monthTitle。
             */
            for (int i = 0; i < childCount; i++) {
                View view = parent.getChildAt(i);
                int index = parent.getChildAdapterPosition(view);
                View monthTitleView;
                if (monthTitleViewMap.get(index) == null) {
                    Date monthDate = monthDateCallback.getMonthDate(index);
                    monthTitleView = monthDecorationViewCallBack.getMonthDecorationView(index, monthDate);
                    ensureViewLayout(monthTitleView, parent);
                    monthTitleViewMap.put(index, monthTitleView);
                } else {
                    monthTitleView = monthTitleViewMap.get(index);
                }
                if (isStick) {
                    if (i == 0) {
                        int tempTop = view.getBottom() - monthTitleHeight;
                        if (tempTop < top) {
                            top = tempTop;
                        }
                    } else {
                        top = view.getTop() - monthTitleHeight;
                    }
                } else {
                    top = view.getTop() - monthTitleHeight;
                }
                c.save();
                c.translate(0, top);
                c.clipRect(left, 0, right, monthTitleHeight);
                monthTitleView.draw(c);
                c.restore();
            }
        }
    }

    private void ensureViewLayout(View monthTitleView, RecyclerView recyclerView) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) monthTitleView.getLayoutParams();
        int widthSpec;
        int heightSpec;
        if (layoutParams != null) {
            widthSpec = View.MeasureSpec.makeMeasureSpec(
                    recyclerView.getMeasuredWidth() - layoutParams.leftMargin - layoutParams.rightMargin, View.MeasureSpec.EXACTLY);
            if (layoutParams.height > 0) {
                heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
            } else {
                heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            }
        } else {
            widthSpec = View.MeasureSpec.makeMeasureSpec(recyclerView.getMeasuredWidth(), View.MeasureSpec.EXACTLY);
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        monthTitleView.measure(widthSpec, heightSpec);
        monthTitleView.layout(0, 0, monthTitleView.getMeasuredWidth(), monthTitleView.getMeasuredHeight());
    }

    /**
     * 释放资源
     */
    public void destroy() {
        monthTitleViewMap.clear();
        monthDecorationViewCallBack = null;
    }


}
