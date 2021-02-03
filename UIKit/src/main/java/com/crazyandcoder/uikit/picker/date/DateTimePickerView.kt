package com.crazyandcoder.uikit.picker.date

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import com.crazyandcoder.uikit.R

/**
 * 日期选择控件
 */
class DateTimePickerView : BaseDateTimePickerView {

    var textSize = 16f
    var textColor = Color.parseColor("#666666")
    var showLabel = false


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        var typedArray: TypedArray = context?.obtainStyledAttributes(attrs, R.styleable.DateTimePickerView)
        this.textSize = typedArray?.getDimension(R.styleable.DateTimePickerView_textSize, 16f)
        this.textColor = typedArray?.getColor(R.styleable.DateTimePickerView_textColor, Color.BLACK)
        this.showLabel = typedArray?.getBoolean(R.styleable.DateTimePickerView_showLabel, false)


    }

    private fun initConfig() {
        yearWheelView?.setTextSize(textSize, true)
        monthWheelView?.setTextSize(textSize, true)
        dayWheelView?.setTextSize(textSize, true)
    }

    override fun getDatePickerViewLayoutId(): Int = R.layout.layout_date_picker_view

    override fun getYearWheelViewId(): Int = R.id.yearWheelView

    override fun getMonthWheelViewId(): Int = R.id.monthWheelView

    override fun getDayWheelViewId(): Int = R.id.dayWheelView

    override fun initView() {
        initConfig()

    }


}