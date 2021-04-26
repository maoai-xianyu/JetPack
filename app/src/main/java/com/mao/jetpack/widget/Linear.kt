package com.mao.jetpack.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.VirtualLayout

/**
 *
 * @author zhangkun
 * @time 2021/4/18 10:59 AM
 * @Description  线性排列的效果  VirtualLayout 虚拟布局控件
 */
class Linear(context: Context, attrs: AttributeSet) : VirtualLayout(context, attrs) {


    private val constraintSet: ConstraintSet by lazy {

        ConstraintSet().apply {
            isForceId = false
        }

    }


    //布局之前，一组嵌套，实现位置设置
    override fun updatePreLayout(container: ConstraintLayout) {
        super.updatePreLayout(container)

        constraintSet.clone(container)

        val viewIds = referencedIds

        for (i in 1 until mCount) {

            val current = viewIds[i]
            val before = viewIds[i - 1]

            // 引用的方式，加上一组约束条件
            constraintSet.connect(
                current, ConstraintSet.START, before, ConstraintSet.END
            )

            constraintSet.connect(
                current, ConstraintSet.TOP, before, ConstraintSet.BOTTOM
            )
            constraintSet.applyTo(container)
        }
    }


}