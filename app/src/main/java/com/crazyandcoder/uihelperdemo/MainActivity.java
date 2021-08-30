package com.crazyandcoder.uihelperdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.crazyandcoder.uihelperdemo.adapter.ItemAdapter;
import com.crazyandcoder.uihelperdemo.bean.Item;
import com.crazyandcoder.uihelperdemo.ui.CalendarActivity;
import com.lljjcoder.style.citypickerview.CityPickerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        updateItemList();

        if (itemAdapter != null) {
            itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position, Item item) {
                    parseItemData(item, position);
                }
            });
        }
    }

    private void parseItemData(Item item, int position) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(this, CalendarActivity.class);
                break;

        }
        startActivity(intent);
    }

    public void updateItemList() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("日历选择器", "月日历组件"));

        itemAdapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
    }
}