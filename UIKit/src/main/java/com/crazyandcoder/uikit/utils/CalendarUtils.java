package com.crazyandcoder.uikit.utils;

import android.annotation.SuppressLint;

import com.crazyandcoder.uikit.widget.calender_v2.CalendarData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * desc:
 * author: created by liji
 * date:   6/10/21 10:21
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class CalendarUtils {

    public static HashMap<String, CalendarData> cacheCalendarMap = new HashMap<>();

    @SuppressLint("SimpleDateFormat")
    public static CalendarData getMonthCalendarData(String defaultDate) {
        CalendarData dateBean = new CalendarData();
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(defaultDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            dateBean.setDate(date);
            dateBean.setDay(calendar.get(Calendar.DAY_OF_MONTH) + "");
            dateBean.setMonth(new SimpleDateFormat("yyyy-MM").format(date));
            dateBean.setYearMonthDay(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateBean;
    }

    /**
     * 获取指定日期的前beforeDay的日期到之后的afterDay的日期
     * author  liji
     * time    6/10/21 6:57 PM
     */
    public static List<CalendarData> getWeekCalendarData(String indexDateStr, int beforeDay, int afterDay) {
        List<CalendarData> dateBeans = new ArrayList<>();
        try {
            //日期格式化
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatYYYYMM = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat formatMMDD = new SimpleDateFormat("MM-dd");

            Calendar calendar = Calendar.getInstance();

            //指定日期
            Date indexDate = indexDateStr == null || indexDateStr.equals("") ? new Date() : new SimpleDateFormat("yyyy-MM-dd").parse(indexDateStr);
            calendar.setTime(indexDate);

            //指定日期前三十天日期
            calendar.add(Calendar.DATE, -beforeDay);
            Date startDate = new Date(calendar.getTimeInMillis());

            //指定日期后七天的日期
            calendar.setTime(indexDate);
            calendar.add(Calendar.DATE, afterDay);
            Date endDate = new Date(calendar.getTimeInMillis());

            Calendar monthCalendar = Calendar.getInstance();
            monthCalendar.setTime(startDate);

            //按月生成日历
            for (; monthCalendar.getTimeInMillis() <= endDate.getTime(); ) {

                //生成某一天日期实体 日item
                CalendarData dateBean = new CalendarData();
                dateBean.setDate(monthCalendar.getTime());
                dateBean.setDay(format.format(monthCalendar.getTime()).equals(getTodayDate()) ? "今天" : monthCalendar.get(Calendar.DAY_OF_MONTH) + "");
                dateBean.setMonth(formatYYYYMM.format(monthCalendar.getTime()));
                dateBean.setYearMonthDay(format.format(monthCalendar.getTime()));
                dateBean.setMonthDay(formatMMDD.format(monthCalendar.getTime()));
                dateBean.setWeek(format.format(monthCalendar.getTime()).equals(getTodayDate()) ? "今天" : getWeekDesc(monthCalendar.get(Calendar.DAY_OF_WEEK)));
                dateBeans.add(dateBean);

                //天数加1
                monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            //月份加1
            calendar.add(Calendar.MONTH, 1);

        } catch (Exception ex) {
        }
        return dateBeans;
    }


    /**
     * 根据指定的日期，获取前 beforeMonth 个月和 afterMonth 个月的日期数据
     * author  liji
     * time    6/10/21 8:49 PM
     */
    public static List<CalendarData> getMonthCalendarData(String indexDateStr, int beforeMonth, int afterMonth) {
        List<CalendarData> dateBeans = new ArrayList<>();
        //日期格式化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatYYYYMM = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat formatMMDD = new SimpleDateFormat("MM-dd");

        try {
            Calendar calendar = Calendar.getInstance();
            //指定日期
            Date indexDate = indexDateStr == null || indexDateStr.equals("") ? new Date() : new SimpleDateFormat("yyyy-MM-dd").parse(indexDateStr);
            calendar.setTime(indexDate);

            //当前日期前 beforeMonth 个月
            calendar.add(Calendar.MONTH, -beforeMonth);
            Date startDate = new Date(calendar.getTimeInMillis());


            //当前日期后 afterMonth 个月
            calendar.setTime(indexDate);
            calendar.add(Calendar.MONTH, afterMonth);
            Date endDate = new Date(calendar.getTimeInMillis());


            //开始月份的1号开始
            calendar.setTime(startDate);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Calendar monthCalendar = Calendar.getInstance();

            //按月生成日历 每行7个 最多6行 42个
            for (; calendar.getTimeInMillis() <= endDate.getTime(); ) {
                //月份item
                CalendarData monthCalendarData = new CalendarData();
                monthCalendarData.setDate(calendar.getTime());
                monthCalendarData.setMonth(formatYYYYMM.format(monthCalendarData.getDate()));
                monthCalendarData.setItemType(CalendarData.TYPE_MONTH);
                dateBeans.add(monthCalendarData);

                //开始月份
                monthCalendar.setTime(calendar.getTime());
                //开始月份的1号
                monthCalendar.set(Calendar.DAY_OF_MONTH, 1);

                //加一个月
                monthCalendar.add(Calendar.MONTH, 1);
                //开始月份的最后一天
                monthCalendar.add(Calendar.DAY_OF_MONTH, -1);
                Date endMonthDay = monthCalendar.getTime();

                //生成单个月的日历，重置每个月的1号日期
                monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
                for (; monthCalendar.getTimeInMillis() <= endMonthDay.getTime(); ) {
                    //处理一个月开始的第一天
                    if (monthCalendar.get(Calendar.DAY_OF_MONTH) == 1) {
                        //看某个月第一天是周几，之前的日期全部填充空白
                        int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                        firstDayPlaceholder(weekDay, dateBeans, monthCalendarData);
                    }

                    //生成某一天日期实体 日item
                    CalendarData dateBean = new CalendarData();
                    dateBean.setDate(monthCalendar.getTime());
                    dateBean.setDay(format.format(monthCalendar.getTime()).equals(getTodayDate()) ? "今" : monthCalendar.get(Calendar.DAY_OF_MONTH) + "");
                    dateBean.setMonth(monthCalendarData.getMonth());
                    dateBean.setYearMonthDay(format.format(monthCalendar.getTime()));
                    dateBean.setMonthDay(formatMMDD.format(monthCalendar.getTime()));
                    dateBean.setWeek(getWeekDesc(monthCalendar.get(Calendar.DAY_OF_WEEK)));
                    dateBeans.add(dateBean);

                    //处理一个月的最后一天
                    if (monthCalendar.getTimeInMillis() == endMonthDay.getTime()) {
                        //看某个月第一天是周几
                        int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                        lastDayPlaceholder(weekDay, dateBeans, monthCalendarData);
                    }

                    //天数加1
                    monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                //月份加1
                calendar.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateBeans;
    }


    /**
     * 根据指定的日期，获取前 beforeMonth 个月和 afterMonth 个月的日期数据
     * author  liji
     * time    6/10/21 8:49 PM
     * asc: 月份是否从小到大显示
     */
    public static List<CalendarData> getMonthCalendarData(String indexDateStr, int beforeMonth, int afterMonth, boolean asc) {
        List<CalendarData> dateBeans = new ArrayList<>();
        //日期格式化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatYYYYMM = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat formatMMDD = new SimpleDateFormat("MM-dd");

        try {
            Calendar calendar = Calendar.getInstance();
            //指定日期
            Date indexDate = indexDateStr == null || indexDateStr.equals("") ? new Date() : new SimpleDateFormat("yyyy-MM-dd").parse(indexDateStr);
            calendar.setTime(indexDate);

            //当前日期前 beforeMonth 个月
            calendar.add(Calendar.MONTH, -beforeMonth);
            Date startDate = new Date(calendar.getTimeInMillis());


            //当前日期后 afterMonth 个月
            calendar.setTime(indexDate);
            calendar.add(Calendar.MONTH, afterMonth);
            Date endDate = new Date(calendar.getTimeInMillis());


            //开始月份的1号开始
            calendar.setTime(asc ? startDate : endDate);

            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Calendar monthCalendar = Calendar.getInstance();

            if (asc) {
                //按月生成日历 每行7个 最多6行 42个
                for (; calendar.getTimeInMillis() <= endDate.getTime(); ) {
                    //月份item
                    CalendarData monthCalendarData = new CalendarData();
                    monthCalendarData.setDate(calendar.getTime());
                    monthCalendarData.setMonth(formatYYYYMM.format(monthCalendarData.getDate()));
                    monthCalendarData.setItemType(CalendarData.TYPE_MONTH);
                    dateBeans.add(monthCalendarData);

                    //开始月份
                    monthCalendar.setTime(calendar.getTime());
                    //开始月份的1号
                    monthCalendar.set(Calendar.DAY_OF_MONTH, 1);

                    //加一个月
                    monthCalendar.add(Calendar.MONTH, 1);
                    //开始月份的最后一天
                    monthCalendar.add(Calendar.DAY_OF_MONTH, -1);
                    Date endMonthDay = monthCalendar.getTime();

                    //生成单个月的日历，重置每个月的1号日期
                    monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
                    for (; monthCalendar.getTimeInMillis() <= endMonthDay.getTime(); ) {
                        //处理一个月开始的第一天
                        if (monthCalendar.get(Calendar.DAY_OF_MONTH) == 1) {
                            //看某个月第一天是周几，之前的日期全部填充空白
                            int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                            firstDayPlaceholder(weekDay, dateBeans, monthCalendarData);
                        }

                        //生成某一天日期实体 日item
                        CalendarData dateBean = new CalendarData();
                        dateBean.setDate(monthCalendar.getTime());
                        dateBean.setDay(format.format(monthCalendar.getTime()).equals(getTodayDate()) ? "今" : monthCalendar.get(Calendar.DAY_OF_MONTH) + "");
                        dateBean.setMonth(monthCalendarData.getMonth());
                        dateBean.setYearMonthDay(format.format(monthCalendar.getTime()));
                        dateBean.setMonthDay(formatMMDD.format(monthCalendar.getTime()));
                        dateBean.setWeek(getWeekDesc(monthCalendar.get(Calendar.DAY_OF_WEEK)));
                        dateBeans.add(dateBean);

                        //处理一个月的最后一天
                        if (monthCalendar.getTimeInMillis() == endMonthDay.getTime()) {
                            //看某个月第一天是周几
                            int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                            lastDayPlaceholder(weekDay, dateBeans, monthCalendarData);
                        }

                        //天数加1
                        monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    //月份加1
                    calendar.add(Calendar.MONTH, 1);
                }
            } else {
                //按月生成日历 每行7个 最多6行 42个
                for (; calendar.getTimeInMillis() >= startDate.getTime(); ) {
                    //月份item
                    CalendarData monthCalendarData = new CalendarData();
                    monthCalendarData.setDate(calendar.getTime());
                    monthCalendarData.setMonth(formatYYYYMM.format(monthCalendarData.getDate()));
                    monthCalendarData.setItemType(CalendarData.TYPE_MONTH);
                    dateBeans.add(monthCalendarData);

                    //开始月份
                    monthCalendar.setTime(calendar.getTime());
                    //开始月份的1号
                    monthCalendar.set(Calendar.DAY_OF_MONTH, 1);

                    //加一个月
                    monthCalendar.add(Calendar.MONTH, 1);
                    //开始月份的最后一天
                    monthCalendar.add(Calendar.DAY_OF_MONTH, -1);
                    Date endMonthDay = monthCalendar.getTime();

                    //生成单个月的日历，重置每个月的1号日期
                    monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
                    for (; monthCalendar.getTimeInMillis() <= endMonthDay.getTime(); ) {
                        //处理一个月开始的第一天
                        if (monthCalendar.get(Calendar.DAY_OF_MONTH) == 1) {
                            //看某个月第一天是周几，之前的日期全部填充空白
                            int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                            firstDayPlaceholder(weekDay, dateBeans, monthCalendarData);
                        }

                        //生成某一天日期实体 日item
                        CalendarData dateBean = new CalendarData();
                        dateBean.setDate(monthCalendar.getTime());
                        dateBean.setDay(format.format(monthCalendar.getTime()).equals(getTodayDate()) ? "今" : monthCalendar.get(Calendar.DAY_OF_MONTH) + "");
                        dateBean.setMonth(monthCalendarData.getMonth());
                        dateBean.setYearMonthDay(format.format(monthCalendar.getTime()));
                        dateBean.setMonthDay(formatMMDD.format(monthCalendar.getTime()));
                        dateBean.setWeek(getWeekDesc(monthCalendar.get(Calendar.DAY_OF_WEEK)));
                        dateBeans.add(dateBean);

                        //处理一个月的最后一天
                        if (monthCalendar.getTimeInMillis() == endMonthDay.getTime()) {
                            //看某个月第一天是周几
                            int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                            lastDayPlaceholder(weekDay, dateBeans, monthCalendarData);
                        }

                        //天数加1
                        monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    //月份加1
                    calendar.add(Calendar.MONTH, -1);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateBeans;
    }


    /**
     * 生成指定日期的月日历数据
     * author  liji
     * time    6/10/21 2:42 PM
     */
    public static List<CalendarData> getMonthCalendarData(String sDate, String eDate) {
        return getMonthCalendarData(sDate, eDate, true);
    }


    /**
     * 生成指定日期的月日历数据
     * author  liji
     * time    6/10/21 2:42 PM
     * asc:是否月份从小到大，正序排序，1月～12月
     */
    public static List<CalendarData> getMonthCalendarData(String sDate, String eDate, boolean asc) {
        List<CalendarData> dateBeans = new ArrayList<>();
        try {
            Calendar calendar = Calendar.getInstance();
            //日期格式化
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatYYYYMM = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat formatMMDD = new SimpleDateFormat("MM-dd");

            //起始日期
            Date startDate = sDate == null || sDate.equals("") ? new Date() : new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
            calendar.setTime(startDate);

            //结束日期，在当前月份基础上往后加5个月
            Date endDate = null;
            if (eDate == null || eDate.equals("")) {
                //结束日期
                calendar.add(Calendar.MONTH, 5);
                endDate = new Date(calendar.getTimeInMillis());
            } else {
                endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);
            }


            //格式化开始日期和结束日期为 yyyy-mm-dd格式
            String endDateStr = format.format(endDate);
            endDate = format.parse(endDateStr);

            String startDateStr = format.format(startDate);
            startDate = format.parse(startDateStr);


            calendar.setTime(asc ? startDate : endDate);


            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Calendar monthCalendar = Calendar.getInstance();


            //正序排序，月份从小到大
            if (asc) {
                //按月生成日历 每行7个 最多6行 42个
                //每一行有七个日期  日 一 二 三 四 五 六 的顺序
                for (; calendar.getTimeInMillis() <= endDate.getTime(); ) {

                    //月份item
                    CalendarData monthCalendarData = new CalendarData();
                    monthCalendarData.setDate(calendar.getTime());
                    monthCalendarData.setMonth(formatYYYYMM.format(monthCalendarData.getDate()));
                    monthCalendarData.setItemType(CalendarData.TYPE_MONTH);
                    dateBeans.add(monthCalendarData);

                    //获取一个月结束的日期和开始日期
                    monthCalendar.setTime(calendar.getTime());
                    monthCalendar.set(Calendar.DAY_OF_MONTH, 1);

                    monthCalendar.add(Calendar.MONTH, 1);
                    monthCalendar.add(Calendar.DAY_OF_MONTH, -1);
                    Date endMonthDay = monthCalendar.getTime();

                    //重置为本月开始
                    monthCalendar.set(Calendar.DAY_OF_MONTH, 1);

                    for (; monthCalendar.getTimeInMillis() <= endMonthDay.getTime(); ) {
                        //生成单个月的日历
                        //处理一个月开始的第一天
                        if (monthCalendar.get(Calendar.DAY_OF_MONTH) == 1) {
                            //看某个月第一天是周几，之前的日期全部填充空白
                            int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                            firstDayPlaceholder(weekDay, dateBeans, monthCalendarData);
                        }

                        //生成某一天日期实体 日item
                        CalendarData dateBean = new CalendarData();
                        dateBean.setDate(monthCalendar.getTime());
                        dateBean.setDay(format.format(monthCalendar.getTime()).equals(getTodayDate()) ? "今" : monthCalendar.get(Calendar.DAY_OF_MONTH) + "");
                        dateBean.setMonth(monthCalendarData.getMonth());
                        dateBean.setYearMonthDay(format.format(monthCalendar.getTime()));
                        dateBean.setMonthDay(formatMMDD.format(monthCalendar.getTime()));
                        dateBean.setWeek(getWeekDesc(monthCalendar.get(Calendar.DAY_OF_WEEK)));
                        dateBeans.add(dateBean);

                        //处理一个月的最后一天
                        if (monthCalendar.getTimeInMillis() == endMonthDay.getTime()) {
                            //看某个月第一天是周几
                            int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                            lastDayPlaceholder(weekDay, dateBeans, monthCalendarData);
                        }

                        //天数加1
                        monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    //月份加1
                    calendar.add(Calendar.MONTH, 1);
                }
            } else {
                //按月生成日历 每行7个 最多6行 42个
                //每一行有七个日期  日 一 二 三 四 五 六 的顺序
                for (; calendar.getTimeInMillis() >= startDate.getTime(); ) {

                    //月份item
                    CalendarData monthCalendarData = new CalendarData();
                    monthCalendarData.setDate(calendar.getTime());
                    monthCalendarData.setMonth(formatYYYYMM.format(monthCalendarData.getDate()));
                    monthCalendarData.setItemType(CalendarData.TYPE_MONTH);
                    dateBeans.add(monthCalendarData);

                    //获取一个月结束的日期和开始日期
                    monthCalendar.setTime(calendar.getTime());
                    monthCalendar.set(Calendar.DAY_OF_MONTH, 1);

                    monthCalendar.add(Calendar.MONTH, 1);
                    monthCalendar.add(Calendar.DAY_OF_MONTH, -1);
                    Date endMonthDay = monthCalendar.getTime();

                    //重置为本月开始
                    monthCalendar.set(Calendar.DAY_OF_MONTH, 1);

                    for (; monthCalendar.getTimeInMillis() <= endMonthDay.getTime(); ) {
                        //生成单个月的日历
                        //处理一个月开始的第一天
                        if (monthCalendar.get(Calendar.DAY_OF_MONTH) == 1) {
                            //看某个月第一天是周几，之前的日期全部填充空白
                            int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                            firstDayPlaceholder(weekDay, dateBeans, monthCalendarData);
                        }

                        //生成某一天日期实体 日item
                        CalendarData dateBean = new CalendarData();
                        dateBean.setDate(monthCalendar.getTime());
                        dateBean.setDay(format.format(monthCalendar.getTime()).equals(getTodayDate()) ? "今" : monthCalendar.get(Calendar.DAY_OF_MONTH) + "");
                        dateBean.setMonth(monthCalendarData.getMonth());
                        dateBean.setYearMonthDay(format.format(monthCalendar.getTime()));
                        dateBean.setMonthDay(formatMMDD.format(monthCalendar.getTime()));
                        dateBean.setWeek(getWeekDesc(monthCalendar.get(Calendar.DAY_OF_WEEK)));
                        dateBeans.add(dateBean);

                        //处理一个月的最后一天
                        if (monthCalendar.getTimeInMillis() == endMonthDay.getTime()) {
                            //看某个月第一天是周几
                            int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                            lastDayPlaceholder(weekDay, dateBeans, monthCalendarData);
                        }

                        //天数加1
                        monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    //月份加1
                    calendar.add(Calendar.MONTH, -1);
                }
            }

        } catch (Exception ex) {

        }
        return dateBeans;
    }


    /**
     * 处理每个月最后一天之后的空白段
     * author  liji
     * time    6/10/21 9:06 PM
     */
    private static void lastDayPlaceholder(int weekDay, List<CalendarData> dateBeans, CalendarData monthCalendarData) {
        switch (weekDay) {
            case 1:
                //周日
                addDatePlaceholder(dateBeans, 6, monthCalendarData.getMonth());
                break;
            case 2:
                //周一
                addDatePlaceholder(dateBeans, 5, monthCalendarData.getMonth());
                break;
            case 3:
                //周二
                addDatePlaceholder(dateBeans, 4, monthCalendarData.getMonth());
                break;
            case 4:
                //周三
                addDatePlaceholder(dateBeans, 3, monthCalendarData.getMonth());
                break;
            case 5:
                //周四
                addDatePlaceholder(dateBeans, 2, monthCalendarData.getMonth());
                break;
            case 6:
                addDatePlaceholder(dateBeans, 1, monthCalendarData.getMonth());
                //周5
                break;
        }

    }

    /**
     * 处理每个月1号之前的空白段
     * author  liji
     * time    6/10/21 9:06 PM
     */
    private static void firstDayPlaceholder(int weekDay, List<CalendarData> dateBeans, CalendarData monthCalendarData) {
        switch (weekDay) {
            case 1:
                //周日
                break;
            case 2:
                //周一
                addDatePlaceholder(dateBeans, 1, monthCalendarData.getMonth());
                break;
            case 3:
                //周二
                addDatePlaceholder(dateBeans, 2, monthCalendarData.getMonth());
                break;
            case 4:
                //周三
                addDatePlaceholder(dateBeans, 3, monthCalendarData.getMonth());
                break;
            case 5:
                //周四
                addDatePlaceholder(dateBeans, 4, monthCalendarData.getMonth());
                break;
            case 6:
                addDatePlaceholder(dateBeans, 5, monthCalendarData.getMonth());
                //周五
                break;
            case 7:
                addDatePlaceholder(dateBeans, 6, monthCalendarData.getMonth());
                //周六
                break;
        }

    }

    private static String getWeekDesc(int weekDay) {
        switch (weekDay) {
            case 1:
                //周日
                return "周日";
            case 2:
                //周一
                return "周一";
            case 3:
                //周二
                return "周二";
            case 4:
                //周三
                return "周三";
            case 5:
                //周四
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
        }
        return "";
    }


    //添加空的日期占位
    private static void addDatePlaceholder(List<CalendarData> dateBeans, int count, String monthStr) {
        for (int i = 0; i < count; i++) {
            CalendarData dateBean = new CalendarData();
            dateBean.setMonth(monthStr);
            dateBeans.add(dateBean);
        }
    }

    private static String getWeekStr(String mWay) {
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return mWay;
    }

    /**
     * 返回现在的本地的日期
     * author  liji
     * time    6/11/21 10:45 AM
     */
    public static String getTodayDate() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(dt);
        return today == null ? "" : today;

    }
}
