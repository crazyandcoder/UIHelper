package com.crazyandcoder.uikit.picker.date

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import com.crazyandcoder.uikit.listener.OnDateSelectedListener
import com.crazyandcoder.uikit.wheel.OnItemSelectedListener
import com.crazyandcoder.uikit.wheel.OnWheelChangedListener
import com.crazyandcoder.uikit.wheel.WheelView
import java.text.SimpleDateFormat
import java.util.*

/**
 * 基础日期选择类
 */
abstract class BaseDateTimePickerView : LinearLayout, OnItemSelectedListener<Int?>, OnWheelChangedListener {


    protected lateinit var yearWheelView: YearWheelView
    protected lateinit var monthWheelView: MonthWheelView
    protected lateinit var dayWheelView: DayWheelView
    private lateinit var onDateSelectedListener: OnDateSelectedListener

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        if (getDatePickerViewLayoutId() != 0) {
            LayoutInflater.from(context).inflate(getDatePickerViewLayoutId(), this)
        }
    }

    @Override
    protected override fun onFinishInflate() {
        super.onFinishInflate()
        val yearId = getYearWheelViewId()
        if (hasViewId(yearId)) {
            yearWheelView = findViewById(yearId)
        }
        val monthId = getMonthWheelViewId()
        if (hasViewId(monthId)) {
            monthWheelView = findViewById(monthId)
        }
        val dayId = getDayWheelViewId()
        if (hasViewId(dayId)) {
            dayWheelView = findViewById(dayId)
        }
        if (yearWheelView != null) {
            yearWheelView.onItemSelectedListener = this
            yearWheelView.onWheelChangedListener = this
        }
        if (monthWheelView != null) {
            monthWheelView.onItemSelectedListener = this
            monthWheelView.onWheelChangedListener = this
            if (yearWheelView != null) {
                monthWheelView.setCurrentSelectedYear(yearWheelView.getSelectedYear())
            }
        }
        if (dayWheelView != null) {
            dayWheelView.onItemSelectedListener = this
            dayWheelView.onWheelChangedListener = this
            if (yearWheelView != null && monthWheelView != null) {
                dayWheelView.setYearAndMonth(yearWheelView.getSelectedYear(), monthWheelView.getSelectedMonth())
            }
        }

    }

    override fun onItemSelected(wheelView: WheelView<Int?>?, data: Int?, position: Int) {
        when (wheelView?.id) {
            getYearWheelViewId() -> {
                if (data != null) {
                    dayWheelView?.setYear(data)
                    monthWheelView?.setCurrentSelectedYear(data)
                }
            }

            getMonthWheelViewId() -> {
                if (data != null) {
                    dayWheelView?.setMonth(data)
                }
            }
        }

        this.onDateSelectedListener?.let {
            val year = if (yearWheelView == null) 1970 else yearWheelView.getSelectedYear()
            val month = if (monthWheelView == null) 1 else monthWheelView.getSelectedMonth()
            val day = if (dayWheelView == null) 1 else dayWheelView.getSelectedDay()
            val date = "$year-$month-$day"
            it.onDateSelected(this, year, month, day, SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date))
        }

    }

    /**
     * 获取 datePickerView 的布局资源id
     *
     * @return 布局资源id
     */
    @LayoutRes
    protected abstract fun getDatePickerViewLayoutId(): Int

    /**
     * 获取 YearWheelView 的 id
     *
     * @return YearWheelView id
     */
    @IdRes
    protected abstract fun getYearWheelViewId(): Int

    /**
     * 获取 MonthWheelView 的 id
     *
     * @return MonthWheelView id
     */
    @IdRes
    protected abstract fun getMonthWheelViewId(): Int

    /**
     * 获取 DayWheelView id
     *
     * @return DayWheelView id
     */
    @IdRes
    protected abstract fun getDayWheelViewId(): Int



    override fun onWheelScroll(scrollOffsetY: Int) {
    }

    override fun onWheelItemChanged(oldPosition: Int, newPosition: Int) {
    }

    override fun onWheelSelected(position: Int) {
    }

    override fun onWheelScrollStateChanged(state: Int) {
    }


    private fun hasViewId(@IdRes idRes: Int): Boolean {
        return idRes != 0 && idRes != NO_ID
    }

    /**
     * 获取年份 WheelView
     *
     * @return 年份 WheelView
     */
    fun getYearWv(): YearWheelView? {
        return yearWheelView
    }

    /**
     * 获取月份 WheelView
     *
     * @return 月份 WheelView
     */
    fun getMonthWv(): MonthWheelView? {
        return monthWheelView
    }

    /**
     * 获取日 WheelView
     *
     * @return 日 WheelView
     */
    fun getDayWv(): DayWheelView? {
        return dayWheelView
    }

    fun setMaxDate(date: Date) {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val maxYear = calendar[Calendar.YEAR]
        val maxMonth = calendar[Calendar.MONTH] + 1
        val maxDay = calendar[Calendar.DAY_OF_MONTH]
        if (yearWheelView != null) {
            yearWheelView.setMaxYear(maxYear)
        }
        if (monthWheelView != null) {
            monthWheelView.setMaxYearAndMonth(maxYear, maxMonth)
        }
        if (dayWheelView != null) {
            dayWheelView.setMaxYearMonthAndDay(maxYear, maxMonth, maxDay)
        }
    }

    /**
     * 设置最小选择的日期 日期小于此日期则会选中到此日期
     *
     * @param date 日期
     */
    open fun setMinDate(@NonNull date: Date?) {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val minYear = calendar[Calendar.YEAR]
        val minMonth = calendar[Calendar.MONTH] + 1
        val minDay = calendar[Calendar.DAY_OF_MONTH]
        if (yearWheelView != null) {
            yearWheelView.setMinYear(minYear)
        }
        if (monthWheelView != null) {
            monthWheelView.setMinYearAndMonth(minYear, minMonth)
        }
        if (dayWheelView != null) {
            dayWheelView.setMinYearMonthAndDay(minYear, minMonth, minDay)
        }
    }

    /**
     * 设置日期回调监听器
     *
     * @param onDateSelectedListener 日期回调监听器
     */
    fun setOnDateSelectedListener(onDateSelectedListener: OnDateSelectedListener) {
        this.onDateSelectedListener = onDateSelectedListener
    }

}