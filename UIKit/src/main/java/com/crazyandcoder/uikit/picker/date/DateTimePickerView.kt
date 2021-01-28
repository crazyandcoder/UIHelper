package com.crazyandcoder.uikit.picker.date

import android.content.Context
import android.util.AttributeSet
import com.crazyandcoder.uikit.R

/**
 * 日期选择控件
 */
class DateTimePickerView : BaseDateTimePickerView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    override fun getDatePickerViewLayoutId(): Int = R.layout.layout_date_picker_view

    override fun getYearWheelViewId(): Int = R.id.yearWheelView

    override fun getMonthWheelViewId(): Int = R.id.monthWheelView

    override fun getDayWheelViewId(): Int = R.id.dayWheelView


}