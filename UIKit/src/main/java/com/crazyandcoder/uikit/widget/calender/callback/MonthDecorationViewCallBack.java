package com.crazyandcoder.uikit.widget.calender.callback;

import android.view.View;

import java.util.Date;

/**
 * desc:
 * author: created by liji
 * date:   6/9/21 15:28
 * email:  crazyandcoder@gmail.com
 * github: https://github.com/crazyandcoder
 */
public interface MonthDecorationViewCallBack {

    /**
     * 提供外部设置MonthTitleView
     *
     * @param position adapter的position位置
     * @param date     月份日期
     * @return
     */
    View getMonthDecorationView(int position, Date date);


}
