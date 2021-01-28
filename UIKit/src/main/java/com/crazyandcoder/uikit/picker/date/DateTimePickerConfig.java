package com.crazyandcoder.uikit.picker.date;

import com.crazyandcoder.uikit.bean._enum.TimePickerLimit;

/**
 * 日期选择器配置项
 */
public class DateTimePickerConfig {


    /**
     * 时间选择的限制
     * {1。没有限制
     * 2。今天之前
     * 3。今天之后
     * }
     */
    @TimePickerLimit
    private int limit;

    /**
     * 取消按钮文案
     */
    private String cancelBtnDesc;

    /**
     * 确认按钮文案
     */
    private String confirmBtnDesc;

    /**
     * 标题文案
     */
    private String title;

    /**
     * 日期选择器弹出来之后 是否显示半透明背景
     */
    private boolean showShadow = true;

    /**
     * 日期选择器弹出来之后 点击外部区域是否消失
     */
    private boolean canCancelOutside = false;


    public DateTimePickerConfig(Builder builder) {
        this.limit = builder.limit;
        this.cancelBtnDesc = builder.cancelBtnDesc;
        this.confirmBtnDesc = builder.confirmBtnDesc;
        this.title = builder.title;
        this.showShadow = builder.showShadow;
        this.canCancelOutside = builder.canCancelOutside;
    }

    public int getLimit() {
        return limit;
    }

    public String getCancelBtnDesc() {
        return cancelBtnDesc;
    }

    public String getConfirmBtnDesc() {
        return confirmBtnDesc;
    }

    public String getTitle() {
        return title;
    }

    public boolean isShowShadow() {
        return showShadow;
    }

    public boolean isCanCancelOutside() {
        return canCancelOutside;
    }

    public static class Builder {
        /**
         * 时间选择的限制
         * {1。没有限制
         * 2。今天之前
         * 3。今天之后
         * }
         */
        @TimePickerLimit
        private int limit;

        /**
         * 取消按钮文案
         */
        private String cancelBtnDesc;

        /**
         * 确认按钮文案
         */
        private String confirmBtnDesc;

        /**
         * 标题文案
         */
        private String title;

        /**
         * 日期选择器弹出来之后 是否显示半透明背景
         */
        private boolean showShadow = true;

        /**
         * 日期选择器弹出来之后 点击外部区域是否消失
         */
        private boolean canCancelOutside = false;

        public Builder setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder setCancelBtnDesc(String cancelBtnDesc) {
            this.cancelBtnDesc = cancelBtnDesc;
            return this;
        }

        public Builder setConfirmBtnDesc(String confirmBtnDesc) {
            this.confirmBtnDesc = confirmBtnDesc;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setShowShadow(boolean showShadow) {
            this.showShadow = showShadow;
            return this;
        }

        public Builder setCanCancelOutside(boolean canCancelOutside) {
            this.canCancelOutside = canCancelOutside;
            return this;
        }

        public DateTimePickerConfig build() {
            return new DateTimePickerConfig(this);
        }

    }
}

