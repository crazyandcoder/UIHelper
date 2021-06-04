package com.crazyandcoder.uikit.widget.date.picker.date

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.crazyandcoder.uikit.R
import com.crazyandcoder.uikit.widget.date.wheel.WheelView
import java.util.*
import kotlin.collections.ArrayList

class MonthWheelView : WheelView<Int> {


    private var selectedMonth = 0
    private var currentSelectedYear = 0
    private var maxYear = -1
    private var maxMonth = -1
    private var minMonth = -1
    private var minYear = -1

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    ) {
        var typedArray: TypedArray? = context?.obtainStyledAttributes(attrs, R.styleable.MonthWheelView)
        this.selectedMonth = typedArray?.getInt(R.styleable.MonthWheelView_selectedMonth, Calendar.getInstance()[Calendar.MONTH])!!

        //设置月份初始化数据
        updateMonthInitData()
        setSelectedMonth(this.selectedMonth);
    }


    /**
     * 设置月份初始化数据
     */
    private fun updateMonthInitData() {
        var list: MutableList<Int> = ArrayList(1)
        for (i in 1..12) {
            list.add(i)
        }
        super.setData(list)
    }

    /**
     * 设置选中的月份
     */
    private fun setSelectedMonth(select: Int?) {
        setSelectedMonth(select, false)
    }

    /**
     * 设置选中的月份
     */
    private fun setSelectedMonth(select: Int?, isSmoothScroll: Boolean) {
        setSelectedMonth(select, false, 0)
    }


    /**
     * 设置选中的月份
     */
    private fun setSelectedMonth(select: Int?, isSmoothScroll: Boolean, smoothDuration: Int) {
        select?.let {
            if (it in 1..12) {
                var result = select
                if (isBiggerMaxMonth(select)) {
                    result = maxMonth
                } else if (isLessMinMonth(select)) {
                    result = minMonth
                }
                updateSelectedMonth(result, false, 0)
            }
        }
    }

    /**
     * 设置选中的月份
     */
    private fun updateSelectedMonth(selectedMonth: Int, smoothScroll: Boolean, smoothDuration: Int) {
        setSelectedItemPosition(selectedMonth - 1, smoothScroll, smoothDuration)
    }


    /**
     * 是否大于最大月份
     */
    private fun isBiggerMaxMonth(month: Int): Boolean {
        return maxMonth in 1..month

    }

    /**
     * 是否小于最小月份
     */
    private fun isLessMinMonth(month: Int): Boolean {
        if (minMonth > 0 && month < minMonth) {
            return true
        }
        return false
    }


    /**
     *   设置最大月份
     */
    public fun setMaxMonth(month: Int) {
        maxMonth = month
        checkCurrentSelectedMonth(selectedItemData)
    }

    /**
     * 设置最小月份
     */
    public fun setMinMonth(month: Int) {
        minMonth = month
        checkCurrentSelectedMonth(selectedItemData)
    }


    private fun checkCurrentSelectedMonth(month: Int) {
        if (isBiggerMaxMonth(month)) {
            setSelectedMonth(maxMonth)
        } else if (isLessMinMonth(month)) {
            setSelectedMonth(minMonth)
        }
    }


    protected override fun onItemSelected(month: Int?, position: Int) {
        if (month != null) {
            checkCurrentSelectedMonth(month)
        }
    }

    public fun getSelectedMonth(): Int {
        return selectedItemData
    }

    fun setCurrentSelectedYear(selectedYear: Int) {
        this.currentSelectedYear = selectedYear
        checkCurrentSelectedMonth(selectedItemData)
    }

    fun setMaxYearAndMonth(maxYear: Int, maxMonth: Int) {
        this.maxYear = maxYear
        this.maxMonth = maxMonth
        checkCurrentSelectedMonth(selectedItemData)

    }

    fun setMinYearAndMonth(minYear: Int, minMonth: Int) {
        this.minYear = minYear
        this.minMonth = minMonth
        checkCurrentSelectedMonth(selectedItemData)

    }


}