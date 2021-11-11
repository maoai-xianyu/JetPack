package com.mao.jetpack.widget.shadow;

import android.graphics.Color;
import android.text.TextUtils;

import java.util.regex.Pattern;

public final class Util {


    private final static String COLOR_REGEX = "^#([0-9a-fA-F]{8}|[0-9a-fA-F]{6})$";
    private final static String COLOR_REGEX_8 = "^#([0-9a-fA-F]{8})$";

    public static int hashCode(int value) {
        return hashCode(value, 17);
    }

    public static int hashCode(int value, int accumulator) {
        return accumulator * 31 + value;
    }

    // 判断传入的颜色字符串是不是正确的字符串
    public static boolean checkColor(String color) {
        return !TextUtils.isEmpty(color) && Pattern.matches(COLOR_REGEX, color);
    }

    /**
     * 猫眼APP 购票，点映，预售..按钮的阴影
     * @param color
     * @return
     */
    public static int convertColor(String color) {
        return convertColor("2E",color);
    }

    /**
     * 颜色转换
     *
     * @param transparency 透明度，默认是82% 透敏度 对应的是 2E 且必须是两位
     * @param color 需要时8位(有透明度)或者6位(无透明度)
     */
    public static int convertColor(String transparency, String color) {
        if ((!TextUtils.isEmpty(transparency) && transparency.length() == 2) && checkColor(color)) {
            String convertColor;
            if (Pattern.matches(COLOR_REGEX_8, color)) {
                // #+8位数的颜色值
                convertColor = "#" + transparency + color.substring(3);
            } else {
                // #+6位数的颜色值
                convertColor = "#" + transparency + color.substring(1);
            }
            if (checkColor(convertColor)) {
                return Color.parseColor(convertColor);
            }
        }
        throw new IllegalArgumentException("Unknown color");
    }
}