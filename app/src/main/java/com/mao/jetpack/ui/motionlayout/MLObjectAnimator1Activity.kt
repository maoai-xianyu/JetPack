package com.mao.jetpack.ui.motionlayout

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.TransitionManager
import com.mao.jetpack.databinding.ActivityMObjectAn1Binding

/**
 *
 * @author zhangkun
 * @time 2021/4/27 8:28 AM
 * @Description
 */
class MLObjectAnimator1Activity : AppCompatActivity() {


    lateinit var binding: ActivityMObjectAn1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMObjectAn1Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    // 过渡动画
    fun onClick(view: View){
        // 方式一：
        // 计算要移动的距离，动画按钮最左边移动到最右边
        /*val distance = binding.root.width - view.width
        Logger.debug(" distance $distance")
        view.animate()
            //.translationX(200f.dp)
            .translationX(distance.toFloat())
            .start()*/


        // 方式二
        // 按钮最左边移动到最右边  TransitionManager 执行动画，需要拿到父布局,需要添加到修改属性之前
        //TransitionManager.beginDelayedTransition(view.parent as FrameLayout)
        /*TransitionManager.beginDelayedTransition(binding.flRoot)
        val layoutParams = view.layoutParams as FrameLayout.LayoutParams
        layoutParams.gravity = Gravity.END
        //view.layoutParams = layoutParams
        view.requestLayout()*/


        // 精简方式
        TransitionManager.beginDelayedTransition(binding.flRoot)
        with(view.layoutParams as FrameLayout.LayoutParams){
            gravity = Gravity.END
            height *=2
            width *=2
            view.layoutParams = this
        }

        // 属性动画是不改变布局参数的，布局的宽高是不发生变化的

    }
}