package com.crazyandcoder.uihelperdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.uihelperdemo.R;
import com.crazyandcoder.uihelperdemo.bean.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> list;
    private Context context;
    private OnItemClickListener listener;

    public ItemAdapter(Context context, List<Item> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        final Item item = list.get(position);
        holder.name.setText("" + item.getName());
        holder.itemRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private RelativeLayout itemRl;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            itemRl = itemView.findViewById(R.id.itemRl);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position, Item item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
