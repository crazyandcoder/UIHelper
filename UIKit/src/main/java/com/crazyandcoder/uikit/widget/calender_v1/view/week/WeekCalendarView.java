package com.crazyandcoder.uikit.widget.calender_v1.view.week;

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
import com.crazyandcoder.uikit.widget.calender_v1.adapter.WeekCalendarAdapter;
import com.crazyandcoder.uikit.widget.calender_v1.bean.CalendarData;
import com.crazyandcoder.uikit.widget.calender_v1.callback.OnWeekCalendarListener;
import com.crazyandcoder.uikit.widget.calender_v1.decoration.WeekCenterLayoutManager;

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
    private OnWeekCalendarListener listener;

    private CalendarData selectCalendarData;
    private WeekCenterLayoutManager weekCenterLayoutManager;
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

        weekCenterLayoutManager = new WeekCenterLayoutManager(context);
        weekCenterLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        weekRecyclerView.setLayoutManager(weekCenterLayoutManager);

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
                listener.onSelected(selectCalendarData);
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
        int index = -1;
        for (int i = 0; i < adapter.getData().size(); i++) {
            adapter.getData().get(i).setHide(false);
            if (itemData.getYearMonthDay().equals(adapter.getData().get(i).getYearMonthDay())) {
                adapter.getData().get(i).setSelected(true);
                index = i;
            } else {
                adapter.getData().get(i).setSelected(false);
            }
        }
        if (index != -1) {
            adapter.getData().get(index).setHide(true);
            //第二个和倒数第二个之间的范围，选中的话隐藏分割线
            if (index - 1 >= 0)
                adapter.getData().get(index - 1).setHide(true);
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
        weekCenterLayoutManager.smoothScrollToPosition(weekRecyclerView, new RecyclerView.State(), position);
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
        selectCalendarData = data;
        updateSelectedDayStatue(data);
    }


    public void setOnWeekCalendarListener(OnWeekCalendarListener listener) {
        this.listener = listener;
    }

}
