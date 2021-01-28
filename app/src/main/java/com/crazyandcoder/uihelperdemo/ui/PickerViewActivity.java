package com.crazyandcoder.uihelperdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.crazyandcoder.uihelperdemo.R;
import com.crazyandcoder.uikit.listener.OnDateSelectedListener;
import com.crazyandcoder.uikit.picker.date.BaseDateTimePickerView;
import com.crazyandcoder.uikit.picker.date.DateTimePickerView;
import com.crazyandcoder.uikit.picker.date.DayWheelView;
import com.crazyandcoder.uikit.picker.date.MonthWheelView;
import com.crazyandcoder.uikit.picker.date.YearWheelView;

import java.util.Date;

public class PickerViewActivity extends AppCompatActivity {


    private TextView btn1;
    private TextView btn2;
    private TextView btn3;
    private DateTimePickerView dateTimePickerView;
    private YearWheelView year;
    private MonthWheelView month;
    private DayWheelView day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_view);
        btn1 = findViewById(R.id.datePicker1Tv);
        btn2 = findViewById(R.id.datePicker2Tv);
        btn3 = findViewById(R.id.datePicker3Tv);
        year = findViewById(R.id.year);
        month = findViewById(R.id.month);
        day = findViewById(R.id.day);


        dateTimePickerView = findViewById(R.id.datePickerView);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWheelViewData();
            }
        });
    }

    private void setWheelViewData() {
//        dateTimePickerView.setOnDateSelectedListener(new OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(BaseDateTimePickerView datePickerView, int year, int month, int day, Date date) {
//                Toast.makeText(PickerViewActivity.this, "" + date.toString(), Toast.LENGTH_LONG).show();
//            }
//        });


        year.setMinYear(2000);
        year.setMaxYear(2050);

        month.setCurrentSelectedYear(2021);
        month.setMaxYearAndMonth(2030, 8);
        month.setMinYearAndMonth(2010, 3);
        month.setMaxMonth(11);
        month.setMinMonth(2);

        day.setMaxYearMonthAndDay(2030, 2, 11);
        day.setMinYearMonthAndDay(2010, 11, 21);

    }
}