package com.crazyandcoder.uikit.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.crazyandcoder.uikit.R;
import com.crazyandcoder.uikit.widget.calender.utils.Utils;
import com.crazyandcoder.uikit.widget.calender_v2.CalendarData;
import com.crazyandcoder.uikit.widget.calender_v2.MonthCalendarView;

/**
 * desc:
 * author: created by liji
 * date:   6/10/21 15:38
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class SignCalendarDialog extends AbsCrazyBaseDialog {

    private MonthCalendarView monthCalendarView;
    private TextView backTodayTv;
    private Context context;
    private ImageView closeSignCalendarImg;
    private String defaultDate;
    private OnMonthDaySelectListener listener;
    private String startDate;
    private String endDate;

    public SignCalendarDialog(@NonNull Context context, String startDate, String endDate, String defaultDate, OnMonthDaySelectListener listener) {
        super(context);
        this.context = context;
        this.defaultDate = defaultDate;
        this.listener = listener;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_sign_calendar;
    }

    @Override
    protected void initView() {

        setBottomStyle();

        closeSignCalendarImg = findViewById(R.id.closeSignCalendarImg);
        backTodayTv = findViewById(R.id.backTodayTv);
        monthCalendarView = findViewById(R.id.monthCalendarView);

        monthCalendarView.initData(startDate, endDate);
        monthCalendarView.setDefaultSelectDay(defaultDate);


        monthCalendarView.setOnDaySelectedListener(new MonthCalendarView.OnDaySelectedListener() {
            @Override
            public void onDay(CalendarData day) {
                if (listener == null) return;
                listener.onDaySelect(day);
                dismiss();
            }
        });


        backTodayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthCalendarView.backToday();
            }
        });

        closeSignCalendarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected void initData() {

    }

    public interface OnMonthDaySelectListener {
        void onDaySelect(CalendarData day);
    }

    /**
     * 设置日历范围
     * author  liji
     * time    6/16/21 3:58 PM
     */
    public void setInitData(String sDate, String eDate) {
        if (monthCalendarView == null) return;
    }
}
