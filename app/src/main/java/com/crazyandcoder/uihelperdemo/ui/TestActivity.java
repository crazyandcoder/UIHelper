package com.crazyandcoder.uihelperdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.crazyandcoder.uihelperdemo.R;

public class TestActivity extends AppCompatActivity {
    private TextView btn1;
    private TextView btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btn1 = findViewById(R.id.test);
        btn2 = findViewById(R.id.result);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTime();
            }
        });


    }

    private void countDownTime() {


    }

}