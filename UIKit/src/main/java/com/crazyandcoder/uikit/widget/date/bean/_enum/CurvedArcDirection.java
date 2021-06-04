package com.crazyandcoder.uikit.widget.date.bean._enum;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义左右圆弧效果方向注解
 * <p>
 * {@link #mCurvedArcDirection}
 * {@link #setCurvedArcDirection(int)}
 */
@IntDef({ICurvedArcDirection.CURVED_ARC_DIRECTION_LEFT,
        ICurvedArcDirection.CURVED_ARC_DIRECTION_CENTER,
        ICurvedArcDirection.CURVED_ARC_DIRECTION_RIGHT})
@Retention(value = RetentionPolicy.SOURCE)
public @interface CurvedArcDirection {
}
