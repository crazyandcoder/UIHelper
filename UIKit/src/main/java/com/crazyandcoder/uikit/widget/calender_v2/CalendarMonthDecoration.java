package com.crazyandcoder.uikit.widget.calender_v2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.uikit.widget.calender.utils.Utils;

public class CalendarMonthDecoration extends RecyclerView.ItemDecoration {
    Paint paint = new Paint();
    Paint colorPaint = new Paint();
    Paint linePaint = new Paint();

    public CalendarMonthDecoration() {
        paint.setColor(Color.parseColor("#F4F7FB"));
        paint.setStyle(Paint.Style.FILL);
        colorPaint.setColor(Color.parseColor("#333333"));
        colorPaint.setAntiAlias(true);
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.parseColor("#dddddd"));
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getChildCount() <= 0) {
            return;
        }
        //头部的高度
        int height = Utils.dip2px(parent.getContext(), 50f);
        //获取第一个可见的view，通过此view获取其对应的月份
        CalendarAdapter calendarAdapter = (CalendarAdapter) parent.getAdapter();
        View fistView = parent.getChildAt(0);
        String text = calendarAdapter.getData().get(parent.getChildAdapterPosition(fistView)).getMonth();
        String fistMonthStr = "";
        int fistViewTop = 0;
        //查找当前可见的itemView中第一个月份类型的item
        for (int i = 0; i < parent.getChildCount(); i++) {
            View v = parent.getChildAt(i);
            if (2 == parent.getChildViewHolder(v).getItemViewType()) {
                fistMonthStr = calendarAdapter.getData().get(parent.getChildAdapterPosition(v)).getMonth();
                fistViewTop = v.getTop();
                break;
            }
        }

        //计算偏移量
        int topOffset = 0;
        if (!fistMonthStr.equals(text) && fistViewTop < height) {
            //前提是第一个可见的月份item不是当前显示的月份和距离的顶部的距离小于头部的高度
            topOffset = height - fistViewTop;
        }
        int t = 0 - topOffset;

        //绘制头部区域
        RectF rectF = new RectF(parent.getLeft(), t, parent.getRight(), t + height);
        c.drawRect(rectF, paint);
        colorPaint.setTextAlign(Paint.Align.LEFT);
        colorPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        colorPaint.setTextSize(Utils.sp2px(parent.getContext(), 16f));
        //绘制头部月份文字
        //计算baseline
        Paint.FontMetrics fontMetrics = colorPaint.getFontMetrics();
        float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseline = rectF.centerY() + distance;
        c.drawText(text, Utils.dip2px(parent.getContext(), 24), baseline, colorPaint);
    }


}
