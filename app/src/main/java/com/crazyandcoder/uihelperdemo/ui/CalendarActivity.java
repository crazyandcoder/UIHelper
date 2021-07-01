package com.crazyandcoder.uihelperdemo.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.crazyandcoder.uihelperdemo.R;
import com.crazyandcoder.uikit.utils.CalendarUtils;
import com.crazyandcoder.uikit.widget.calender_v1.bean.CalendarData;
import com.crazyandcoder.uikit.widget.calender_v1.callback.OnMonthCalendarListener;
import com.crazyandcoder.uikit.widget.calender_v1.callback.OnWeekCalendarListener;
import com.crazyandcoder.uikit.widget.calender_v1.dialog.SignCalendarDialog;
import com.crazyandcoder.uikit.widget.calender_v1.view.week.WeekCalendarView;

public class CalendarActivity extends AppCompatActivity {

    private WeekCalendarView weekCalendarView;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);
        result = findViewById(R.id.result);
        weekCalendarView = findViewById(R.id.weekCalendarView);
        result.setText("选择结果：" + CalendarUtils.getTodayDate());

        //周日历初始化数据
        weekCalendarView.initData(CalendarUtils.getTodayDate());
        weekCalendarView.setDefaultSelectDay(CalendarUtils.getTodayDate());
        weekCalendarView.setOnWeekCalendarListener(new OnWeekCalendarListener() {
            @Override
            public void onMonthCalendarShow(CalendarData data) {
                showSignCalendarDialog(data.getYearMonthDay());
            }

            @Override
            public void onWeekCalendarSelect(CalendarData data) {
                result.setText("选择结果：" + data.toString());
            }
        });
    }

    /**
     * 周日历选中日期后打卡月日历dialog
     * author  liji
     * time    6/24/21 10:43 AM
     */
    private void showSignCalendarDialog(String defaultDate) {
        SignCalendarDialog dialog = new SignCalendarDialog(this, "2021-01", "2021-12", defaultDate, new OnMonthCalendarListener() {
            @Override
            public void onSelected(CalendarData day) {
                weekCalendarView.setDefaultSelectDay(day.getYearMonthDay());
                result.setText("选择结果：" + day.toString());
            }
        });
        dialog.show();
    }
}