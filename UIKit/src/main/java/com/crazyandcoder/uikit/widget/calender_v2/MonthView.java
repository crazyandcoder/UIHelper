package com.crazyandcoder.uikit.widget.calender_v2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.uikit.R;

/**
 * desc:
 * author: created by liji
 * date:   6/9/21 21:07
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class MonthView extends FrameLayout {


    public MonthView(@NonNull Context context) {
        this(context, null);
    }

    public MonthView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MonthView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_calendar_month, this);

    }

}

