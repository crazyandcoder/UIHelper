package com.crazyandcoder.uihelperdemo.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.crazyandcoder.uihelperdemo.R;
import com.crazyandcoder.uikit.widget.calender_v2.CalendarData;
import com.crazyandcoder.uikit.widget.calender_v2.MonthCalendarView;
import com.crazyandcoder.uikit.widget.dialog.SignCalendarDialog;
import com.crazyandcoder.uikit.widget.week.WeekCalendarView;

public class CalendarActivity extends AppCompatActivity {

    private WeekCalendarView weekCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);
        weekCalendarView = findViewById(R.id.weekCalendarView);
        weekCalendarView.initData("2021-06-10");
        weekCalendarView.setOnMonthCalendarShowListener(new WeekCalendarView.OnMonthCalendarShowListener() {
            @Override
            public void monthCalendar(CalendarData data) {
                showSignCalendarDialog(data.getYearMonthDay());
            }
        });
    }

    private void showSignCalendarDialog(String defaultDate) {
        SignCalendarDialog dialog = new SignCalendarDialog(this, defaultDate);
        dialog.show();
    }
}