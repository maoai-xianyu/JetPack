package com.mao.jetpack.ui.motionlayout

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.TransitionManager
import com.mao.jetpack.databinding.ActivityMObjectAn2Binding
import com.mao.jetpack.databinding.ActivityMotionlayoutBinding
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2021/4/27 8:28 AM
 * @Description
 */
class MLObjectAnimator2Activity : AppCompatActivity() {


    // 不能在布局中为其它布局定义root，最顶层可以
    lateinit var binding: ActivityMObjectAn2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMObjectAn2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun onClick(v: View) {
        // 属性动画发生位置的移动，但是不会作用与其他布局，可以自定义属性动画达到过度动画的效果
        /*v.animate()
            .scaleX(2f)
            .scaleY(2f)
            .start()*/


        // 过渡动画，同时会作用与其他控件，改变了布局的参数
        TransitionManager.beginDelayedTransition(binding.root)

        with(v.layoutParams as LinearLayout.LayoutParams) {
            height *= 2
            width *= 2
            //v.layoutParams = this
        }

        v.requestLayout()


    }

    // 过度动画
    /**
     * 1. 有两个场景   开始场景   ----->    结束场景
     *
     */
}