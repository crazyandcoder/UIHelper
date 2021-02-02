package com.crazyandcoder.uikit.picker.date

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.SparseArray
import com.crazyandcoder.uikit.R
import com.crazyandcoder.uikit.wheel.WheelView
import java.util.*

class DayWheelView : WheelView<Int> {


    private var selectedDay = 0
    private var maxDay = -1
    private var minDay = -1
    private var maxYear = -1
    private var minYear = -1
    private var maxMonth = -1
    private var minMonth = -1
    private var currentYear = 0;
    private var currentMonth = 0;
    private val mCalendar: Calendar = Calendar.getInstance()


    companion object {
        var cacheDays = SparseArray<MutableList<Int>>(1)

    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    ) {
        var typedArray: TypedArray? = context?.obtainStyledAttributes(attrs, R.styleable.DayWheelView)
        this.selectedDay = typedArray?.getInt(R.styleable.DayWheelView_selectedDay, Calendar.getInstance()[Calendar.DATE])!!
        this.currentMonth = typedArray?.getInt(R.styleable.DayWheelView_month, mCalendar[Calendar.MONTH] + 1)!!
        this.currentYear = typedArray?.getInt(R.styleable.DayWheelView_year, mCalendar[Calendar.YEAR])!!

        //设置月份初始化数据
        updateDayInitData()
        setSelectedDay(this.selectedDay);
    }


    /**
     * 设置月份初始化数据
     */
    private fun updateDayInitData() {
        mCalendar[Calendar.YEAR] = currentYear
        mCalendar[Calendar.MONTH] = currentMonth - 1
        mCalendar[Calendar.DATE] = 1
        mCalendar.roll(Calendar.DATE, -1)
        val days = mCalendar[Calendar.DATE]
        var list: MutableList<Int>? = cacheDays?.get(days)

        if (list == null || list.size == 0) {
            list = ArrayList(1)
            for (i in 1..days) {
                list.add(i)
            }
            cacheDays.put(days, data)
        }
        super.setData(list)
        checkCurrentSelectedDay(selectedItemData)
    }

    /**
     * 设置选中的月份
     */
    private fun setSelectedDay(select: Int?) {
        setSelectedDay(select, false)
    }

    /**
     * 设置选中的月份
     */
    private fun setSelectedDay(select: Int?, isSmoothScroll: Boolean) {
        setSelectedDay(select, false, 0)
    }


    /**
     * 设置选中的月份
     */
    private fun setSelectedDay(select: Int?, isSmoothScroll: Boolean, smoothDuration: Int) {
        select?.let {
            val days = mCalendar[Calendar.DATE]
            if (it in 1..days) {
                var result = select
                if (isBiggerMaxDay(select)) {
                    result = maxDay
                } else if (isLessMinDay(select)) {
                    result = minDay
                }
                updateSelectedDay(result, false, 0)
            }
        }
    }

    /**
     * 设置选中的月份
     */
    private fun updateSelectedDay(selectedDay: Int, smoothScroll: Boolean, smoothDuration: Int) {
        setSelectedItemPosition(selectedDay - 1, smoothScroll, smoothDuration)
    }


    /**
     * 是否大于最大月份
     */
    private fun isBiggerMaxDay(Day: Int): Boolean {
        return maxDay in 1..Day

    }

    /**
     * 是否小于最小月份
     */
    private fun isLessMinDay(Day: Int): Boolean {
        if (minDay > 0 && Day < minDay) {
            return true
        }
        return false
    }

    /**
     * 设置最大年月日
     */
    public fun setMaxYearMonthDay(maxYear: Int, maxMonth: Int, maxDay: Int) {
        this.maxYear = maxYear
        this.maxMonth = maxMonth
        this.maxDay = maxDay
        checkCurrentSelectedDay(selectedItemData)
    }

    /**
     * 设置最小年月日
     */
    public fun setMinYearMonthDay(minYear: Int, minMonth: Int, minDay: Int) {
        this.minYear = minYear
        this.minMonth = minMonth
        this.minDay = minDay
        checkCurrentSelectedDay(selectedItemData)
    }

    /**
     *   设置最大月份
     */
    public fun setMaxDay(Day: Int) {
        maxDay = Day
        checkCurrentSelectedDay(selectedItemData)
    }

    /**
     * 设置最小月份
     */
    public fun setMinDay(Day: Int) {
        minDay = Day
        checkCurrentSelectedDay(selectedItemData)
    }


    private fun checkCurrentSelectedDay(day: Int?) {
        day?.let {
            if (isBiggerMaxDay(it)) {
                setSelectedDay(maxDay)
            } else if (isLessMinDay(it)) {
                setSelectedDay(minDay)
            }
        }

    }


    protected override fun onItemSelected(Day: Int?, position: Int) {
        if (Day != null) {
            checkCurrentSelectedDay(Day)
        }
    }

    public fun getSelectedDay(): Int {
        return selectedItemData
    }

    fun setYearAndMonth(selectedYear: Int, selectedMonth: Int) {
        this.currentYear = selectedYear
        this.currentMonth = selectedMonth
        updateDayInitData()
    }

    fun setYear(year: Int) {
        this.currentYear = year
        updateDayInitData()
    }

    fun setMonth(month: Int) {
        this.currentMonth = month
        updateDayInitData()
    }

    fun setMaxYearMonthAndDay(maxYear: Int, maxMonth: Int, maxDay: Int) {
        this.maxYear = maxYear
        this.maxMonth = maxMonth
        this.maxDay = maxDay
        checkCurrentSelectedDay(selectedItemData)
    }

    fun setMinYearMonthAndDay(minYear: Int, minMonth: Int, minDay: Int) {

        this.minYear = minYear
        this.minMonth = minMonth
        this.minDay = minDay
        checkCurrentSelectedDay(selectedItemData)
    }


}