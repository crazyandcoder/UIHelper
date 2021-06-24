package com.crazyandcoder.uikit;

import org.junit.Test;

import static com.crazyandcoder.uikit.utils.CalendarUtils.getMonthCalendarData;
import static com.crazyandcoder.uikit.utils.CalendarUtils.getMonthDate;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String date = getMonthDate(-4);
        System.out.println(date);
    }
}