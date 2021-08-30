package com.crazyandcoder.uikit.widget.calender_v1.view.month;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.uikit.R;
import com.crazyandcoder.uikit.utils.CalendarUtils;
import com.crazyandcoder.uikit.widget.calender_v1.decoration.MonthCalendarDecoration;
import com.crazyandcoder.uikit.widget.calender_v1.decoration.MonthCenterGridLayoutManager;
import com.crazyandcoder.uikit.widget.calender_v1.adapter.MonthCalendarAdapter;
import com.crazyandcoder.uikit.widget.calender_v1.bean.CalendarData;

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
    private MonthCalendarAdapter adapter;
    private OnDaySelectedListener listener;
    private MonthCenterGridLayoutManager monthCenterGridLayoutManager;

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
        adapter = new MonthCalendarAdapter(context, new ArrayList<CalendarData>());
        monthCenterGridLayoutManager = new MonthCenterGridLayoutManager(context, 7);
        monthCenterGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                //spanCount	每行排列 item 个数，在GridLayoutManager对象创建时需要传入
                //spanSize	当前位置的 item 跨度大小，在 setSpanSizeLookup() 方法返回
                if (CalendarData.TYPE_MONTH == adapter.getItemData(i).getItemType()) {
                    return 7;
                } else {
                    return 1;
                }
            }
        });
        monthCalendarRecycler.setLayoutManager(monthCenterGridLayoutManager);
        monthCalendarRecycler.addItemDecoration(new MonthCalendarDecoration());
        monthCalendarRecycler.setAdapter(adapter);
        adapter.setOnRecyclerviewItemClick(new MonthCalendarAdapter.OnRecyclerviewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                if (listener == null || adapter == null) return;
                listener.onDay(adapter.getItemData(position));
                updateSelectedDayStatue(adapter.getItemData(position));
            }
        });
        monthCalendarRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    MonthCenterGridLayoutManager manager = (MonthCenterGridLayoutManager) recyclerView.getLayoutManager();
                    int start = manager.findFirstCompletelyVisibleItemPosition();
                    int end = manager.findLastCompletelyVisibleItemPosition();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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
        monthCenterGridLayoutManager.smoothScrollToPosition(monthCalendarRecycler, new RecyclerView.State(), position);
    }

    public void backToday() {
        String today = CalendarUtils.getTodayDate();
        if (today == null || today.equals("")) return;
        CalendarData data = CalendarUtils.getMonthCalendarData(today);
        int position = adapter.getData().indexOf(data);
        monthCenterGridLayoutManager.smoothScrollToPosition(monthCalendarRecycler, new RecyclerView.State(), position);
    }

    public void initData(String startDate, String endDate) {
        adapter.getData().clear();
        adapter.getData().addAll(CalendarUtils.getMonthCalendarData(startDate, endDate, true));
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
