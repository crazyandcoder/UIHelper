package com.crazyandcoder.uikit.widget.date.bean._enum;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 时间选择限制
 * <p>
 */
@IntDef({ITimePickerLimit.ALL,
        ITimePickerLimit.TODAY_BEFORE,
        ITimePickerLimit.TODAY_AFTER})
@Retention(value = RetentionPolicy.SOURCE)
public @interface TimePickerLimit {
}
