package com.mao.jetpack.ui.coroutine

import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mao.jetpack.R
import com.mao.jetpack.global.AppUtilsKt
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2022/3/25 15:52
 * @Description
 */

@BindingAdapter("url")
fun loadImage(view: ImageView, url: String?) {
    Logger.debug("url $url")
    if (url.isNullOrEmpty()) {
        Glide.with(AppUtilsKt.sApplication).load(R.drawable.avatar)
            .override(60, 60)
            .into(view)
    } else {
        Glide.with(AppUtilsKt.sApplication).load(url)
            .override(60, 60)
            .into(view)
    }
}

@BindingAdapter("url")
fun loadImage(view: ImageView, @IdRes url: Int) {
    Glide.with(AppUtilsKt.sApplication).load(url)
        .override(60, 60)
        .into(view)
}

//@BindingAdapter(value = ["url", "defaultImageRes"], requireAll = false)
@BindingAdapter("url", "defaultImageRes", requireAll = false)
fun loadImage(view: ImageView, url: String?, @IdRes res: Int) {
    if (url.isNullOrEmpty()) {
        Glide.with(AppUtilsKt.sApplication).load(res)
            .override(60, 60)
            .into(view)
    } else {
        Glide.with(AppUtilsKt.sApplication).load(url)
            .override(60, 60)
            .into(view)
    }
}

