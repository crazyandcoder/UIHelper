package com.crazyandcoder.uikit.widget.date.picker.date

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.crazyandcoder.uikit.R
import com.crazyandcoder.uikit.widget.date.wheel.WheelView
import java.util.*
import kotlin.collections.ArrayList

class YearWheelView : WheelView<Int> {


    private var startYear = 0
    private var endYear = 0
    private var selectedYear = 0
    private var maxYear = -1
    private var minYear = -1

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    ) {
        var typedArray: TypedArray? = context?.obtainStyledAttributes(attrs, R.styleable.YearWheelView)
        this.startYear = typedArray?.getInt(R.styleable.YearWheelView_startYear, 1900)!!
        this.endYear = typedArray?.getInt(R.styleable.YearWheelView_endYear, 2100)!!
        this.selectedYear = typedArray?.getInt(R.styleable.YearWheelView_selectedYear, Calendar.getInstance()[Calendar.YEAR])

        //设置年份初始化数据
        updateYearInitData()
        setSelectedYear(this.selectedYear);
    }

    /**
     * 设置选中的年份
     */
    private fun setSelectedYear(select: Int?) {
        setSelectedYear(select, false)
    }

    /**
     * 设置选中的年份
     */
    private fun setSelectedYear(select: Int?, isSmoothScroll: Boolean) {
        setSelectedYear(select, false, 0)
    }


    /**
     * 设置选中的年份
     */
    private fun setSelectedYear(select: Int?, isSmoothScroll: Boolean, smoothDuration: Int) {
        select?.let {
            if (it in startYear..endYear) {
                var result = select
                if (isBiggerMaxYear(select)) {
                    result = maxYear
                } else if (isLessMinYear(select)) {
                    result = minYear
                }
                updateSelectedYear(result, false, 0)
            }
        }
    }

    /**
     * 设置选中的年份
     */
    private fun updateSelectedYear(selectedYear: Int, smoothScroll: Boolean, smoothDuration: Int) {
        setSelectedItemPosition(selectedYear - startYear, smoothScroll, smoothDuration)
    }


    /**
     * 是否大于最大年份
     */
    private fun isBiggerMaxYear(year: Int): Boolean {
        return maxYear in 1..year

    }

    /**
     * 是否小于最小年份
     */
    private fun isLessMinYear(year: Int): Boolean {
        if (minYear > 0 && year < minYear) {
            return true
        }
        return false
    }


    /**
     * 设置年份初始化数据
     */
    private fun updateYearInitData() {
        var list: MutableList<Int> = ArrayList(1)
        for (i in this.startYear!!..this.endYear!!) {
            list.add(i)
        }
        super.setData(list)
    }

    /**
     *   设置最大年份
     */
    public fun setMaxYear(year: Int) {
        if (year > endYear) {
            maxYear = endYear
            return
        }
        maxYear = year
        checkCurrentSelectedYear(selectedItemData)
    }


    /**
     * 设置最小年份
     */
    public fun setMinYear(year: Int) {
        if (year < startYear) {
            minYear = startYear
            return
        }
        minYear = year
        checkCurrentSelectedYear(selectedItemData)
    }


    private fun checkCurrentSelectedYear(year: Int) {
        if (isBiggerMaxYear(year)) {
            setSelectedYear(maxYear)
        } else if (isLessMinYear(year)) {
            setSelectedYear(minYear)
        }
    }

    /**
     * 设置年份区间
     */
    private fun setYearRange(start: Int, end: Int) {
        this.startYear = start
        this.endYear = end
        updateYearInitData()
        updateMaxAndMinYear()
    }

    /**
     * 更新最大和最小年份
     */
    private fun updateMaxAndMinYear() {
        if (maxYear > endYear) {
            maxYear = endYear
        }
        if (minYear < startYear) {
            minYear = startYear
        }

        if (maxYear < minYear) {
            maxYear = minYear
        }
    }

    protected override fun onItemSelected(year: Int?, position: Int) {
        if (year != null) {
            checkCurrentSelectedYear(year)
        }
    }

    public fun getSelectedYear(): Int {
        return selectedItemData
    }


}