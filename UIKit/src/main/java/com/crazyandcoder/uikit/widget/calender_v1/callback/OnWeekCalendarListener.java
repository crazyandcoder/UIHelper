package com.crazyandcoder.uikit.widget.calender_v1.callback;

import com.crazyandcoder.uikit.widget.calender_v1.bean.CalendarData;

/**
 * desc: 周日历选择回调
 * author: created by liji
 * date:   6/24/21 10:37
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public interface OnWeekCalendarListener {

    /**
     * 周日历选择回调
     * author  liji
     * time    6/24/21 10:38 AM
     */
    void onMonthCalendarShow(CalendarData data);


    /**
     * 周日历选择
     * author  liji
     * time    7/1/21 4:28 PM
     */
    void onWeekCalendarSelect(CalendarData data);
}
