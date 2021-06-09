package com.crazyandcoder.uikit.widget.calender.model;


import com.crazyandcoder.uikit.widget.calender.callback.MonthDecorationViewCallBack;
import com.crazyandcoder.uikit.widget.calender.callback.OnCalendarSelectDayListener;
import com.crazyandcoder.uikit.widget.calender.view.MonthCalendarView;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MonthCalendarViewWrapper {
    public static CalendarBuilder wrap(MonthCalendarView calendarView) {
        return new CalendarBuilder(calendarView);
    }

    public static class CalendarBuilder {
        public java.util.Date minDate, maxDate;
        public SelectionMode selectionMode;
        public MonthDecorationViewCallBack monthDecorationViewCallBack;
        public boolean isStick = true;
        public boolean isShowMonthDecorationView;
        public OnCalendarSelectDayListener<CalendarDay> onCalendarSelectDayListener;
        public CalendarSelectDay<CalendarDay> calendarSelectDay;
        public WeakReference<MonthCalendarView> weakReference;


        public CalendarBuilder(MonthCalendarView calendarView) {
            this.weakReference = new WeakReference<>(calendarView);
        }

        public CalendarBuilder setDateRange(String startDate, String endDate) {
            try {
                this.minDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                this.maxDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return this;
        }

        public CalendarBuilder setDateRange(java.util.Date minDate, java.util.Date maxDate) {
            this.minDate = minDate;
            this.maxDate = maxDate;
            return this;
        }

        public CalendarBuilder setSelectionMode(SelectionMode selectionMode) {
            this.selectionMode = selectionMode;
            return this;
        }

        public CalendarBuilder setMonthDecorationViewCallBack(MonthDecorationViewCallBack monthDecorationViewCallBack) {
            this.monthDecorationViewCallBack = monthDecorationViewCallBack;
            return this;
        }

        public CalendarBuilder setShowMonthDecorationView(boolean isShowMonthTitleView) {
            this.isShowMonthDecorationView = isShowMonthTitleView;
            return this;
        }

        public CalendarBuilder setStick(boolean isStick) {
            this.isStick = isStick;
            return this;
        }


        public CalendarBuilder setOnCalendarSelectDayListener(OnCalendarSelectDayListener<CalendarDay> onCalendarSelectDayListener) {
            this.onCalendarSelectDayListener = onCalendarSelectDayListener;
            return this;

        }

        public CalendarBuilder setCalendarSelectDay(CalendarSelectDay<CalendarDay> calendarSelectDay) {
            this.calendarSelectDay = calendarSelectDay;
            return this;
        }

        public void display() {
            weakReference.get().display(this);
        }
    }
}
