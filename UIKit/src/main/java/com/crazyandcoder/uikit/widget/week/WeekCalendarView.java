package com.crazyandcoder.uikit.widget.week;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.uikit.R;
import com.crazyandcoder.uikit.utils.CalendarUtils;
import com.crazyandcoder.uikit.utils.DpSpUtils;
import com.crazyandcoder.uikit.widget.calender_v2.CalendarData;

import java.util.ArrayList;

/**
 * desc:
 * author: created by liji
 * date:   6/10/21 16:47
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class WeekCalendarView extends FrameLayout {

    private RecyclerView weekRecyclerView;
    private WeekCalendarAdapter adapter;
    private LinearLayout monthCalendarL;
    private ImageView backTodayImg;
    private OnMonthCalendarShowListener listener;

    private CalendarData selectCalendarData;
    private CenterLayoutManager centerLayoutManager;
    private CalendarData todayCalendarData;


    public WeekCalendarView(@NonNull Context context) {
        this(context, null);
    }

    public WeekCalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_calendar_week, this);

        weekRecyclerView = findViewById(R.id.weekRecyclerView);
        monthCalendarL = findViewById(R.id.monthCalendarL);
        backTodayImg = findViewById(R.id.backTodayImg);


        adapter = new WeekCalendarAdapter(context, new ArrayList<CalendarData>(), DpSpUtils.dp2px(45));

        centerLayoutManager = new CenterLayoutManager(context);
        centerLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        weekRecyclerView.setLayoutManager(centerLayoutManager);

        weekRecyclerView.setAdapter(adapter);
        adapter.setOnRecyclerviewItemClick(new WeekCalendarAdapter.OnRecyclerviewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                if (listener == null || adapter == null) return;
                selectCalendarData = adapter.getItemData(position);
                updateSelectedDayStatue(adapter.getItemData(position), position);
            }
        });


        //跳转到月历
        monthCalendarL.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) return;
                listener.monthCalendar(selectCalendarData);
            }
        });

        //回到今天
        backTodayImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                backToday();
            }
        });
    }

    /**
     * 回到今天
     * author  liji
     * time    6/11/21 11:21 AM
     */
    private void backToday() {
        setDefaultSelectDay(CalendarUtils.getTodayDate());
    }


    /**
     * 初始化日历数据
     * author  liji
     * time    6/10/21 5:44 PM
     */
    public void initData(String startDate) {
        adapter.getData().clear();
        adapter.getData().addAll(CalendarUtils.getWeekCalendarData(startDate, 30, 7));
    }


    /**
     * 设置选中的状态
     * author  liji
     * time    6/10/21 1:51 PM
     */
    private void updateSelectedDayStatue(CalendarData itemData, int position) {
        if (adapter == null || adapter.getData() == null || adapter.getData().size() == 0) return;
        for (CalendarData data : adapter.getData()) {
            data.setSelected(itemData.getYearMonthDay().equals(data.getYearMonthDay()));
        }
        adapter.notifyDataSetChanged();
        checkBackTodayStatue(itemData);
        smoothScrollToCenter(itemData, position);
    }

    private void updateSelectedDayStatue(CalendarData itemData) {
        if (adapter == null || adapter.getData() == null || adapter.getData().size() == 0) return;
        int position = adapter.getData().indexOf(itemData);
        if (position != -1) {
            updateSelectedDayStatue(itemData, position);
        } else {
            adapter.getData().clear();
            adapter.getData().addAll(CalendarUtils.getWeekCalendarData(itemData.getYearMonthDay(), 30, 7));
            int newPosition = adapter.getData().indexOf(itemData);
            updateSelectedDayStatue(itemData, newPosition);
        }
    }

    /**
     * 将选中的日期滚动到屏幕中间
     * author  liji
     * time    6/11/21 11:01 AM
     */
    private void smoothScrollToCenter(CalendarData itemData, int position) {
        centerLayoutManager.smoothScrollToPosition(weekRecyclerView, new RecyclerView.State(), position);
    }

    /**
     * 检测是否显示回到今天
     * author  liji
     * time    6/11/21 10:42 AM
     */
    private void checkBackTodayStatue(CalendarData itemData) {
        if (itemData == null || itemData.getYearMonthDay().equals("")) return;
        String now = CalendarUtils.getTodayDate();
        if (now.equals("")) return;
        backTodayImg.setVisibility(now.equals(itemData.getYearMonthDay()) ? View.GONE : View.VISIBLE);
    }

    /**
     * 设置默认选中
     * author  liji
     * time    6/10/21 2:35 PM
     */
    public void setDefaultSelectDay(String date) {
        if (date == null || date.equals("")) return;
        CalendarData data = CalendarUtils.getMonthCalendarData(date);
        updateSelectedDayStatue(data);
    }


    public void setOnMonthCalendarShowListener(OnMonthCalendarShowListener listener) {
        this.listener = listener;
    }

    public interface OnMonthCalendarShowListener {
        void monthCalendar(CalendarData data);
    }
}
