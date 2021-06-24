package com.crazyandcoder.uihelperdemo.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.crazyandcoder.uihelperdemo.R;
import com.crazyandcoder.uikit.utils.CalendarUtils;
import com.crazyandcoder.uikit.widget.calender_v2.CalendarData;
import com.crazyandcoder.uikit.widget.dialog.SignCalendarDialog;
import com.crazyandcoder.uikit.widget.week.WeekCalendarView;

public class CalendarActivity extends AppCompatActivity {

    private WeekCalendarView weekCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);
        weekCalendarView = findViewById(R.id.weekCalendarView);
        weekCalendarView.initData(CalendarUtils.getTodayDate());
        weekCalendarView.setDefaultSelectDay(CalendarUtils.getTodayDate());
        weekCalendarView.setOnMonthCalendarShowListener(new WeekCalendarView.OnMonthCalendarShowListener() {
            @Override
            public void monthCalendar(CalendarData data) {
                showSignCalendarDialog(data.getYearMonthDay());
            }
        });
    }

    private void showSignCalendarDialog(String defaultDate) {
        SignCalendarDialog dialog = new SignCalendarDialog(this,"2021-01", "2021-12",defaultDate, new SignCalendarDialog.OnMonthDaySelectListener() {
            @Override
            public void onDaySelect(CalendarData day) {
                weekCalendarView.setDefaultSelectDay(day.getYearMonthDay());
            }
        });
        dialog.show();
    }
}