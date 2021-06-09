package com.crazyandcoder.uikit.widget.calender.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.uikit.R;
import com.crazyandcoder.uikit.widget.calender.adapter.MonthCalendarAdapter;
import com.crazyandcoder.uikit.widget.calender.callback.MonthDecorationViewCallBack;
import com.crazyandcoder.uikit.widget.calender.decoration.MonthDecoration;
import com.crazyandcoder.uikit.widget.calender.model.CalendarDay;
import com.crazyandcoder.uikit.widget.calender.model.MonthCalendarViewWrapper;

/**
 * desc:
 * author: created by liji
 * date:   6/9/21 15:30
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class MonthCalendarView extends RecyclerView {


    private MonthCalendarAdapter calendarAdapter;
    private MonthDecoration monthTitleDecoration;



    public MonthCalendarView(@NonNull Context context) {
        this(context,null);
    }

    public MonthCalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MonthCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MonthCalendarView);
        calendarAdapter = new MonthCalendarAdapter(getContext(), typedArray);
        typedArray.recycle();
    }

    /**
     * 释放 MonthTitleDecoration中资源
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (monthTitleDecoration != null) {
            monthTitleDecoration.destroy();
        }
    }


    /**
     * 外部调用刷新
     */
    public void refresh() {
        calendarAdapter.refresh();
    }

    public int covertToPosition(@NonNull CalendarDay calendarDay) {
        return calendarAdapter.covertToPosition(calendarDay);
    }

    public void display(MonthCalendarViewWrapper.CalendarBuilder calendarBuilder) {
        MonthDecorationViewCallBack monthDecorationViewCallBack = calendarBuilder.monthDecorationViewCallBack;
        boolean isStick = calendarBuilder.isStick;
        boolean isShowMonthTitleView = calendarBuilder.isShowMonthDecorationView;
        if (isShowMonthTitleView && monthDecorationViewCallBack != null) {
            monthTitleDecoration = new MonthDecoration();
            monthTitleDecoration.setStick(isStick);
            monthTitleDecoration.setMonthDecorationViewCallBack(monthDecorationViewCallBack);
            addItemDecoration(monthTitleDecoration);
        }
        calendarAdapter.init(calendarBuilder);
        setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(calendarAdapter);
    }
}
