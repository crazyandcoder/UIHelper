package com.crazyandcoder.uikit.widget.calender.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.crazyandcoder.uikit.R;
import com.crazyandcoder.uikit.utils.DpSpUtils;

/**
 * desc: 周标题标签view
 * 星期天-星期六
 * author: created by liji
 * date:   6/9/21 15:04
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public class WeekLabelTitleView extends View {

    private int defaultLabelWeekendTextColor = Color.parseColor("#FF6E00");
    private int defaultLabelTextColor = Color.parseColor("#000000");
    private int defaultLabelTextSize = DpSpUtils.sp2px(13);

    private int weekendTextColor;
    private int textColor;
    private float textSize;

    private Paint.FontMetrics fontMetrics;
    private Paint labelPaint;
    private int columnNum = 7;
    private float labelWidth;
    private static final String[] LABEL_ARR = new String[]{
            "周日", "周一", "周二", "周三", "周四", "周五", "周六"
    };
    private RectF labelRectF;
    private CharSequence[] labelArr;


    public WeekLabelTitleView(Context context) {
        this(context, null);
    }

    public WeekLabelTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekLabelTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeekLabelView);
        weekendTextColor = typedArray.getColor(R.styleable.WeekLabelView_cl_labelWeekendTextColor, defaultLabelWeekendTextColor);
        textColor = typedArray.getColor(R.styleable.WeekLabelView_cl_labelTextColor, defaultLabelTextColor);
        textSize = typedArray.getDimension(R.styleable.WeekLabelView_cl_labelTextSize, defaultLabelTextSize);
        labelArr = typedArray.getTextArray(R.styleable.WeekLabelView_cl_labelArr);
        if (labelArr == null) labelArr = LABEL_ARR;
        typedArray.recycle();

        labelRectF = new RectF();
        labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelPaint.setTextAlign(Paint.Align.CENTER);
        labelPaint.setTextSize(textSize);
        fontMetrics = new Paint.FontMetrics();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        labelPaint.getFontMetrics(fontMetrics);
        setMeasuredDimension(widthMeasureSpec,
                (int) (fontMetrics.descent - fontMetrics.ascent) + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        labelWidth = (w - getPaddingLeft() - getPaddingRight()) / (columnNum * 1.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        labelPaint.getFontMetrics(fontMetrics);
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int labelHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        for (int i = 0; i < columnNum; i++) {
            labelRectF.set(left, top, left + labelWidth, top + labelHeight);
            if (i == 0 || i == columnNum - 1) {
                labelPaint.setColor(weekendTextColor);
            } else {
                labelPaint.setColor(textColor);
            }
            float distance = (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent;
            float baseline = labelRectF.centerY() + distance;
            canvas.drawText(labelArr[i].toString(), labelRectF.centerX(), baseline, labelPaint);
            left += labelWidth;
        }
    }
}
