package com.crazyandcoder.uikit.widget.calender_v1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.uikit.R;
import com.crazyandcoder.uikit.widget.calender_v1.bean.CalendarData;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 * author: created by liji
 * date:   6/9/21 21:27
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class MonthCalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CalendarData> list;

    public MonthCalendarAdapter(Context context, List<CalendarData> list) {
        this.context = context;
        this.list = list;
    }

    private MonthCalendarAdapter.OnRecyclerviewItemClick onRecyclerviewItemClick;

    public MonthCalendarAdapter.OnRecyclerviewItemClick getOnRecyclerviewItemClick() {
        return onRecyclerviewItemClick;
    }

    public void setOnRecyclerviewItemClick(MonthCalendarAdapter.OnRecyclerviewItemClick onRecyclerviewItemClick) {
        this.onRecyclerviewItemClick = onRecyclerviewItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == CalendarData.TYPE_DAY) {
            View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_day, parent, false);
            final MonthCalendarAdapter.DayViewHolder dayViewHolder = new MonthCalendarAdapter.DayViewHolder(rootView);
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
        } else if (viewType == CalendarData.TYPE_MONTH) {
            View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_month, parent, false);
            return new MonthCalendarAdapter.MonthViewHolder(rootView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MonthCalendarAdapter.DayViewHolder) {
            MonthCalendarAdapter.DayViewHolder vh = ((MonthCalendarAdapter.DayViewHolder) holder);
            vh.dayTv.setText(getItemData(position).getDay());
            vh.dayDescTv.setText("");
            vh.dayBgL.setSelected(getItemData(position).isSelected());
            vh.dayTv.setTextColor(getItemData(position).isSelected() ? Color.parseColor("#ffffff") : Color.parseColor("#333333"));
        } else if (holder instanceof MonthCalendarAdapter.MonthViewHolder) {
            String month = getItemData(position).getMonth();
            String[] split = month.split("-");
            if (split == null || split.length != 2) return;
            String text = split[0] + "年" + split[1] + "月";
            ((MonthViewHolder) holder).montTv.setText(text);
        }
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
        public TextView dayTv;
        public TextView dayDescTv;
        public RelativeLayout dayBgL;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayBgL = itemView.findViewById(R.id.dayBgL);
            dayTv = itemView.findViewById(R.id.dayTv);
            dayDescTv = itemView.findViewById(R.id.dayDescTv);
        }
    }

    public class MonthViewHolder extends RecyclerView.ViewHolder {
        public TextView montTv;

        public MonthViewHolder(@NonNull View itemView) {
            super(itemView);
            montTv = itemView.findViewById(R.id.montTv);
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
