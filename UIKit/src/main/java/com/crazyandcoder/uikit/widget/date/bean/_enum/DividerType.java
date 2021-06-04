package com.crazyandcoder.uikit.widget.date.bean._enum;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义分割线类型注解
 * <p>
 * {@link #mDividerType}
 * {@link #setDividerType(int)}
 */
@IntDef({IDividerType.DIVIDER_TYPE_FILL, IDividerType.DIVIDER_TYPE_WRAP})
@Retention(value = RetentionPolicy.SOURCE)
public @interface DividerType {
}