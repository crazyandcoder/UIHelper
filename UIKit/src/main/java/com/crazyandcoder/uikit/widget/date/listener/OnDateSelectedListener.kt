package com.crazyandcoder.uikit.widget.date.listener

import com.crazyandcoder.uikit.widget.date.picker.date.BaseDateTimePickerView
import java.util.*

interface OnDateSelectedListener {
    /**
     * @param datePickerView BaseDatePickerView
     * @param year           选中的年份
     * @param month          选中的月份
     * @param day            选中的天
     * @param date           选中的日期
     */
    fun onDateSelected(datePickerView: BaseDateTimePickerView?, year: Int, month: Int,
                       day: Int, date: Date?)

}