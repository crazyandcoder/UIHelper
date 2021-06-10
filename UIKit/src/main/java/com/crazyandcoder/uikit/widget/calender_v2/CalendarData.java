package com.crazyandcoder.uikit.widget.calender_v2;

import java.util.Date;
import java.util.Objects;

/**
 * desc:
 * author: created by liji
 * date:   6/9/21 21:24
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class CalendarData {
    //item类型
    public static int TYPE_DAY = 1;//日期item
    public static int TYPE_MONTH = 2;//月份item
    int itemType = TYPE_DAY;//默认是日期item

    //item状态
    public static int ITEM_STATE_BEGIN_DATE = 1;//开始日期
    public static int ITEM_STATE_END_DATE = 2;//结束日期
    public static int ITEM_STATE_SELECTED = 3;//选中状态
    public static int ITEM_STATE_NORMAL = 4;//正常状态
    public int itemState = ITEM_STATE_NORMAL;

    //具体日期
    private Date date;
    //一个月的某天
    private String day;
    //月份
    private String month;

    //yyyy-MM-dd
    private String formatDate;

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getItemState() {
        return itemState;
    }

    public void setItemState(int itemState) {
        this.itemState = itemState;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getFormatDate() {
        return formatDate == null ? "" : formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarData data = (CalendarData) o;
        return formatDate.equals(data.formatDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formatDate);
    }
}
