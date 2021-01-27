package com.crazyandcoder.uihelperdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.crazyandcoder.uihelperdemo.R;

public class PickerViewActivity extends AppCompatActivity {


    private TextView btn1;
    private TextView btn2;
    private TextView btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_view);
        btn1 = findViewById(R.id.datePicker1Tv);
        btn2 = findViewById(R.id.datePicker2Tv);
        btn3 = findViewById(R.id.datePicker3Tv);
    }
}