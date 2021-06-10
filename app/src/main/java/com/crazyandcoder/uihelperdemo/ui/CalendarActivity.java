package com.crazyandcoder.uihelperdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.crazyandcoder.uihelperdemo.R;
import com.crazyandcoder.uikit.widget.calender_v2.MonthCalendarView;
import com.crazyandcoder.uikit.widget.dialog.SignCalendarDialog;

public class CalendarActivity extends AppCompatActivity {

    private MonthCalendarView monthCalendarView;
    private TextView chooseTv;
    private TextView defaultDateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);
        defaultDateTv = findViewById(R.id.defaultDateTv);
        chooseTv = findViewById(R.id.chooseTv);
        chooseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignCalendarDialog("");
            }
        });
        defaultDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignCalendarDialog("2021-06-10");
            }
        });

    }

    private void showSignCalendarDialog(String defaultDate) {
        SignCalendarDialog dialog = new SignCalendarDialog(this, defaultDate);
        dialog.show();

    }
}