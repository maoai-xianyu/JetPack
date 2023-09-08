package com.mao.jetpack.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DimenUtils {


    private static float applyDimension(int unit, float value, DisplayMetrics metrics) {
        switch (unit) {
            case 1:
            case 2:
                return TypedValue.applyDimension(unit, value, metrics);
            case 3:
            case 4:
            case 5:
            default:
                return 0.0F;
            case 6:
                return value / metrics.density;
            case 7:
                return value / metrics.scaledDensity;
        }
    }

    public static int dp2px(float value) {
        return (int) applyDimension(1, value, Resources.getSystem().getDisplayMetrics());
    }

    public static int sp2px(float value) {
        return (int) applyDimension(2, value, Resources.getSystem().getDisplayMetrics());
    }

    public static int px2dp(float value) {
        return (int) applyDimension(6, value, Resources.getSystem().getDisplayMetrics());
    }

    public static int px2sp(float value) {
        return (int) applyDimension(7, value, Resources.getSystem().getDisplayMetrics());
    }

    public static int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public static int getScreenWidth() {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static float getScaleDensity() {
        return Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = Resources.getSystem().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    public static float getCenter(float parent, float child) {
        return (parent - child) / 2.0F;
    }
}