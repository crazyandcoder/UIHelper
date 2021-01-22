package com.crazyandcoder.uikit.wheel;

/**
 * 条目选中监听器
 *
 * @param <T>
 */
public interface OnItemSelectedListener<T> {

    /**
     * 条目选中回调
     *
     * @param wheelView wheelView
     * @param data      选中的数据
     * @param position  选中的下标
     */
    void onItemSelected(WheelView<T> wheelView, T data, int position);
}
