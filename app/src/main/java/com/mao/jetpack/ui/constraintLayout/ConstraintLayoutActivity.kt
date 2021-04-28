package com.mao.jetpack.ui.constraintLayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.mao.jetpack.R
import com.mao.jetpack.databinding.ActivityConstraintlayoutBinding
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2021/4/14 7:55 AM
 * @Description
 */
class ConstraintLayoutActivity : AppCompatActivity() {


    private lateinit var rootBinding: ActivityConstraintlayoutBinding

    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootBinding = ActivityConstraintlayoutBinding.inflate(layoutInflater)
        setContentView(rootBinding.root)

        rootBinding.button.setOnClickListener {
            rootBinding.group.visibility = View.GONE

        }

        rootBinding.btnLayer.setOnClickListener {
            //rootBinding.layer.visibility = View.GONE
            rootBinding.layer.rotation = 45f
            rootBinding.layer.translationY = 100f
            rootBinding.layer.translationX = 100f

        }

    }

    // 代码设置约束
    fun onClickConstraintSet(view: View) {

        // ConstraintSet 设置约束
        val constraintLayout = view as ConstraintLayout
        val constraintSet = ConstraintSet().apply {
            clone(constraintLayout)
            connect(
                R.id.twitter,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM
            )
        }
        constraintSet.applyTo(constraintLayout)
    }

    fun onClick(view: View) {
        rootBinding.placeholder.setContentId(view.id)
    }


    // 替换布局
    fun onClickConstraintSetLayout(view: View) {
        Logger.debug("-----跟换布局 ")
        if (!flag) {
            val constraintLayout = view as ConstraintLayout

            val constraintSet = ConstraintSet().apply {
                isForceId = false
                 clone(this@ConstraintLayoutActivity, R.layout.activity_constraint_end)
            }
            TransitionManager.beginDelayedTransition(constraintLayout)
            constraintSet.applyTo(constraintLayout)
            flag = true
        }
    }


    fun itemClick(view: View) {
        Logger.debug("-----触发点击事件 ")

        if (flag) {
            val constraintLayout = view.parent as ConstraintLayout

            val constraintSet = ConstraintSet().apply {
                // 没有id的方式
                isForceId = false
                clone(this@ConstraintLayoutActivity, R.layout.activity_constraint_start)
            }
            TransitionManager.beginDelayedTransition(constraintLayout)
            // 修改布局之前
            constraintSet.applyTo(constraintLayout)
            flag = false
        }
    }
}