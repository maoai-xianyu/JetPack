package com.mao.jetpack.widget.shadow;

public final class Util {

    public static int hashCode(int value) {
        return hashCode(value, 17);
    }

    public static int hashCode(int value, int accumulator) {
        return accumulator * 31 + value;
    }

}