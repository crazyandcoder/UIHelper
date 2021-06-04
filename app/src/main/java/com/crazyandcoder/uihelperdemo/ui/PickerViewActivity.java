package com.crazyandcoder.uihelperdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.crazyandcoder.uihelperdemo.R;
import com.crazyandcoder.uikit.widget.date.listener.OnDateSelectedListener;
import com.crazyandcoder.uikit.widget.date.picker.date.BaseDateTimePickerView;
import com.crazyandcoder.uikit.widget.date.picker.date.DateTimePickerView;

import java.util.Date;

public class PickerViewActivity extends AppCompatActivity {


    private TextView btn1;
    private TextView btn2;
    private TextView btn3;
    private DateTimePickerView dateTimePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_view);
        btn1 = findViewById(R.id.datePicker1Tv);
        btn2 = findViewById(R.id.datePicker2Tv);
        btn3 = findViewById(R.id.datePicker3Tv);


        dateTimePickerView = findViewById(R.id.datePickerView);
        dateTimePickerView.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(BaseDateTimePickerView datePickerView, int year, int month, int day, Date date) {
                Toast.makeText(PickerViewActivity.this, "" + date.toString(), Toast.LENGTH_LONG).show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWheelViewData();
            }
        });
    }

    private void setWheelViewData() {




    }
}