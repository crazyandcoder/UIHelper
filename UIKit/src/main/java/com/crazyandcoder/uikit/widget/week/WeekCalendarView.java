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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.uikit.R;
import com.crazyandcoder.uikit.utils.CalendarUtils;
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

        adapter = new WeekCalendarAdapter(context, new ArrayList<CalendarData>());

        HorizontalLayoutManager linearLayoutManager = new HorizontalLayoutManager(1,5);
        weekRecyclerView.setLayoutManager(linearLayoutManager);

        weekRecyclerView.setAdapter(adapter);
        adapter.setOnRecyclerviewItemClick(new WeekCalendarAdapter.OnRecyclerviewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                if (listener == null || adapter == null) return;
                selectCalendarData = adapter.getItemData(position);
                updateSelectedDayStatue(adapter.getItemData(position));
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
                if (listener == null) return;
                listener.monthCalendar(selectCalendarData);
            }
        });
    }


    /**
     * 初始化日历数据
     * author  liji
     * time    6/10/21 5:44 PM
     */
    public void initData(String startDate) {
        adapter.getData().clear();
        adapter.getData().addAll(CalendarUtils.getWeekCalendarData(startDate, 30,7));
    }


    /**
     * 设置选中的状态
     * author  liji
     * time    6/10/21 1:51 PM
     */
    private void updateSelectedDayStatue(CalendarData itemData) {
        if (adapter == null || adapter.getData() == null || adapter.getData().size() == 0) return;
        for (CalendarData data : adapter.getData()) {
            data.setSelected(itemData.getYearMonthDay().equals(data.getYearMonthDay()));
        }
        adapter.notifyDataSetChanged();
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
        int position = adapter.getData().indexOf(data);
        weekRecyclerView.smoothScrollToPosition(position == -1 ? 0 : position);
    }


    public void setOnMonthCalendarShowListener(OnMonthCalendarShowListener listener) {
        this.listener = listener;
    }

    public interface OnMonthCalendarShowListener {
        void monthCalendar(CalendarData data);
    }
}
