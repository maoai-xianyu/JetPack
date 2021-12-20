package com.mao.jetpack.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 *
 * @author zhangkun
 * @time 2021/4/27 8:51 AM
 * @Description
 */
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )