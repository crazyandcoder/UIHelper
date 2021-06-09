package com.crazyandcoder.uikit.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class DpSpUtils {
    /**
     * dp转换px
     *
     * @param dp dp值
     * @return 转换后的px值
     */
    public static int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * sp转换px
     *
     * @param sp sp值
     * @return 转换后的px值
     */
    public static int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }
}
