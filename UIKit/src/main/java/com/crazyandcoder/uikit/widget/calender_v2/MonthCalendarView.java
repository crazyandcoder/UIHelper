package com.crazyandcoder.uikit.widget.calender_v2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.uikit.R;
import com.crazyandcoder.uikit.utils.CalendarUtils;

import java.util.ArrayList;

/**
 * desc:
 * author: created by liji
 * date:   6/9/21 21:07
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class MonthCalendarView extends FrameLayout {


    private RecyclerView monthCalendarRecycler;
    private CalendarAdapter adapter;
    private OnDaySelectedListener listener;

    public MonthCalendarView(@NonNull Context context) {
        this(context, null);
    }

    public MonthCalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MonthCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_calendar_month, this);
        monthCalendarRecycler = findViewById(R.id.monthRecyclerView);
        adapter = new CalendarAdapter(context, new ArrayList<CalendarData>());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 7);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if (CalendarData.TYPE_MONTH == adapter.getItemData(i).getItemType()) {
                    return 7;
                } else {
                    return 1;
                }
            }
        });
        monthCalendarRecycler.setLayoutManager(gridLayoutManager);
        monthCalendarRecycler.addItemDecoration(new CalendarMonthDecoration());
        monthCalendarRecycler.setAdapter(adapter);
        adapter.setOnRecyclerviewItemClick(new CalendarAdapter.OnRecyclerviewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                if (listener == null || adapter == null) return;
                listener.onDay(adapter.getItemData(position));
                updateSelectedDayStatue(adapter.getItemData(position));
            }
        });
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
        monthCalendarRecycler.smoothScrollToPosition(position == -1 ? 0 : position);
    }

    public void initData(String startDate, String endDate) {
        adapter.getData().clear();
        adapter.getData().addAll(CalendarUtils.getMonthCalendarData(startDate, endDate));
    }

    public void updateData() {

    }

    public void setOnDaySelectedListener(OnDaySelectedListener listener) {
        this.listener = listener;
    }


    public interface OnDaySelectedListener {
        void onDay(CalendarData day);
    }
}
