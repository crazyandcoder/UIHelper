package com.crazyandcoder.uikit.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.crazyandcoder.uikit.R;
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

    public SignCalendarDialog(@NonNull Context context, String defaultDate) {
        super(context);
        this.context = context;
        this.defaultDate = defaultDate;
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
        monthCalendarView.initData("2021-01-01", "2021-12-31");


        monthCalendarView.setDefaultSelectDay(defaultDate);

        monthCalendarView.setOnDaySelectedListener(new MonthCalendarView.OnDaySelectedListener() {
            @Override
            public void onDay(CalendarData day) {
                Toast.makeText(context, day.getFormatDate(), Toast.LENGTH_LONG).show();
            }
        });


        backTodayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthCalendarView.setDefaultSelectDay("2021-06-10");
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
}
