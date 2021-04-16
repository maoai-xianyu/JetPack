package com.mao.jetpack.utils

import android.content.Context
import android.util.AttributeSet
import android.view.ViewAnimationUtils
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.jar.Attributes
import kotlin.math.hypot

/**
 *
 * @author zhangkun
 * @time 2021/4/16 8:26 AM
 * @Description 自定义 helper
 */
class CircularRevealHelper(context: Context, attrs: AttributeSet) :
    ConstraintHelper(context, attrs) {


    // 布局加载完之后，对指定为view进行动画的加载
    override fun updatePostLayout(container: ConstraintLayout) {
        super.updatePostLayout(container)


        referencedIds.forEach {

            val view = container.getViewById(it)
            val radius = hypot(view.width.toDouble(), view.height.toDouble())


            ViewAnimationUtils.createCircularReveal(
                view, 0, 0, 0f, radius.toFloat()
            )
                .setDuration(2000L)
                .start()

        }
    }

}