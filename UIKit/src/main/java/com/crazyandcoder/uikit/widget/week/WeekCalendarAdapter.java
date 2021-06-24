package com.crazyandcoder.uikit.widget.week;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.uikit.R;
import com.crazyandcoder.uikit.utils.ScreenUtil;
import com.crazyandcoder.uikit.widget.calender_v2.CalendarData;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 * author: created by liji
 * date:   6/9/21 21:27
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class WeekCalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CalendarData> list;
    private int monthCalendarWidth;

    public WeekCalendarAdapter(Context context, List<CalendarData> list, int monthCalendarWidth) {
        this.context = context;
        this.list = list;
        this.monthCalendarWidth = monthCalendarWidth;
    }

    private WeekCalendarAdapter.OnRecyclerviewItemClick onRecyclerviewItemClick;

    public WeekCalendarAdapter.OnRecyclerviewItemClick getOnRecyclerviewItemClick() {
        return onRecyclerviewItemClick;
    }

    public void setOnRecyclerviewItemClick(WeekCalendarAdapter.OnRecyclerviewItemClick onRecyclerviewItemClick) {
        this.onRecyclerviewItemClick = onRecyclerviewItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_week_day, parent, false);
        final WeekCalendarAdapter.DayViewHolder dayViewHolder = new WeekCalendarAdapter.DayViewHolder(rootView);
        dayViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarData data = getItemData(dayViewHolder.getLayoutPosition());
                if (onRecyclerviewItemClick != null && data != null && data.getDay() != null && !data.getDay().equals("")) {
                    onRecyclerviewItemClick.onItemClick(v, dayViewHolder.getLayoutPosition());
                }
            }
        });
        return dayViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WeekCalendarAdapter.DayViewHolder vh = ((WeekCalendarAdapter.DayViewHolder) holder);
        vh.dateTv.setText(getItemData(position).getMonthDay());
        vh.weekTv.setText(getItemData(position).getWeek());
        vh.dayDescTv.setText("" + getItemData(position).getDesc());
        vh.dayBgL.setSelected(getItemData(position).isSelected());
        vh.dayLineView.setVisibility(getItemData(position).isHide() ? View.GONE : View.VISIBLE);

        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) vh.dayBgL.getLayoutParams();
        layoutParams.width = (ScreenUtil.getInstance(vh.dayBgL.getContext()).getScreenWidth() - monthCalendarWidth) / 5;
        vh.dayBgL.setLayoutParams(layoutParams);

    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        public TextView weekTv;
        public TextView dateTv;
        public TextView dayDescTv;
        public View dayLineView;
        public RelativeLayout dayBgL;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayLineView = itemView.findViewById(R.id.dayLineView);
            dayBgL = itemView.findViewById(R.id.dayBgL);
            dateTv = itemView.findViewById(R.id.dateTv);
            weekTv = itemView.findViewById(R.id.weekTv);
            dayDescTv = itemView.findViewById(R.id.dayDescTv);
        }
    }

    public interface OnRecyclerviewItemClick {
        void onItemClick(View v, int position);
    }

    public List<CalendarData> getData() {
        return list == null ? new ArrayList<CalendarData>() : list;
    }

    public CalendarData getItemData(int position) {
        if (list == null || list.size() == 0) return new CalendarData();
        if (position < list.size()) return list.get(position);
        return new CalendarData();
    }
}
