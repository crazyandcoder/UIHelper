package com.crazyandcoder.uikit.widget.calender.callback;

import com.crazyandcoder.uikit.widget.calender.model.CalendarSelectDay;

public interface OnCalendarSelectDayListener<K> {

    /**
     * 当自定义属性中isSingleSelect为true时；
     * {@link CalendarSelectDay#getFirstSelectDay()} 为单选后的值，
     * {@link CalendarSelectDay#getLastSelectDay()}为null。
     * 当自定义属性中iisSingleSelect为false时；
     * {@link CalendarSelectDay#getFirstSelectDay()}为第一次选择的日期，
     * {@link CalendarSelectDay#getLastSelectDay()}为最后一次选择的日期。
     *
     * @param calendarSelectDay 选中的日期
     */
    void onCalendarSelectDay(CalendarSelectDay<K> calendarSelectDay);

}
