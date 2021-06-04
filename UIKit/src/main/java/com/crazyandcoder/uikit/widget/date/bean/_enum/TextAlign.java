package com.crazyandcoder.uikit.widget.date.bean._enum;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义文字对齐方式注解
 * <p>
 * {@link #mTextAlign}
 * {@link #setTextAlign(int)}
 */
@IntDef({ITextAlign.TEXT_ALIGN_LEFT, ITextAlign.TEXT_ALIGN_CENTER, ITextAlign.TEXT_ALIGN_RIGHT})
@Retention(value = RetentionPolicy.SOURCE)
public @interface TextAlign {
}
