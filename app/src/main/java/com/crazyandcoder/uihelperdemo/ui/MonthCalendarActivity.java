package com.crazyandcoder.uihelperdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.crazyandcoder.uihelperdemo.R;
import com.crazyandcoder.uikit.widget.calender.callback.MonthDecorationViewCallBack;
import com.crazyandcoder.uikit.widget.calender.callback.OnCalendarSelectDayListener;
import com.crazyandcoder.uikit.widget.calender.model.CalendarDay;
import com.crazyandcoder.uikit.widget.calender.model.CalendarSelectDay;
import com.crazyandcoder.uikit.widget.calender.model.MonthCalendarViewWrapper;
import com.crazyandcoder.uikit.widget.calender.model.SelectionMode;
import com.crazyandcoder.uikit.widget.calender.view.MonthCalendarView;
import com.crazyandcoder.uikit.widget.calender.view.WeekLabelTitleView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthCalendarActivity extends AppCompatActivity {

    private MonthCalendarView monthCalendarView;
    private WeekLabelTitleView weekLabelTitleView;
    private TextView resultDateTv;

    private CalendarSelectDay<CalendarDay> calendarSelectDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        monthCalendarView = findViewById(R.id.monthCalendarView);
        weekLabelTitleView = findViewById(R.id.weekLabelView);
        resultDateTv = findViewById(R.id.resultDateTv);

        initSelectDay();


        String currentSelectDayStr = formatDate("yyyy-MM-dd", calendarSelectDay.getFirstSelectDay().toDate());
        resultDateTv.setText(currentSelectDayStr);



        MonthCalendarViewWrapper.wrap(monthCalendarView)
                //设置展示的日期范围
                .setDateRange("2021-05-01", "2022-12-31")
                //设置默认选中的日期
                .setCalendarSelectDay(calendarSelectDay)
                //选中模式-单选
                .setSelectionMode(SelectionMode.SINGLE)
                //设置日历选中事件
                .setOnCalendarSelectDayListener(new OnCalendarSelectDayListener<CalendarDay>() {
                    @Override
                    public void onCalendarSelectDay(CalendarSelectDay<CalendarDay> calendarSelectDay) {
                        CalendarDay firstSelectDay = calendarSelectDay.getFirstSelectDay();
                        if (firstSelectDay != null) {
                            String firstSelectDateStr = formatDate("yyyy-MM-dd", firstSelectDay.toDate());
                            resultDateTv.setText(firstSelectDateStr);
                        }
                    }
                })
                //月份头是否悬停
                .setStick(true)
                //是否展示月份布局
                .setShowMonthDecorationView(true)
                //设置月份布局回调
                .setMonthDecorationViewCallBack(new MonthDecorationViewCallBack() {
                    @Override
                    public View getMonthDecorationView(int position, Date date) {
                        View view = View.inflate(MonthCalendarActivity.this, R.layout.layout_calendar_month_title, null);
                        TextView tvMonthTitle = view.findViewById(R.id.monthDecorationTv);
                        tvMonthTitle.setText(formatDate("yyyy年MM月", date));
                        return view;
                    }
                })
                .display();

        //根据指定日期得到position位置
        int position = monthCalendarView.covertToPosition(calendarSelectDay.getFirstSelectDay());
        if (position != -1) {
            monthCalendarView.smoothScrollToPosition(position);
        }
    }

    private void initSelectDay() {
        calendarSelectDay = new CalendarSelectDay<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 2);
        CalendarDay firstSelectDay = new CalendarDay(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        calendarSelectDay.setFirstSelectDay(firstSelectDay);

    }

    public String formatDate(String format, Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }
}