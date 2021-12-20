package com.mao.jetpack.ui.motionlayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.Scene
import androidx.transition.TransitionManager
import com.mao.jetpack.R
import com.mao.jetpack.databinding.ActivityGoBinding
import kotlinx.android.synthetic.main.go_start.view.*

/**
 *
 * @author zhangkun
 * @time 2021/6/1 9:13 AM
 * @Description
 */
class GoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGoBinding

    private var toggle = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindData()
    }

    private fun bindData() {
        binding.root.image_film_cover.setOnClickListener(this)
        binding.root.rating_film_rating.rating = 4.5f
        binding.root.text_film_title.text = getString(R.string.film_title)
        binding.root.text_film_description.text = getString(R.string.film_description)
    }


    // 过度动画  两种场景进行切换  go_start 和 go_end  控件的id 需要一一对应哦
    override fun onClick(v: View?) {

        val startScene = Scene.getSceneForLayout(binding.rootView, R.layout.go_start, this)
        val endScene = Scene.getSceneForLayout(binding.rootView, R.layout.go_end, this)

        if (toggle) {
            TransitionManager.go(endScene)
        } else {
            TransitionManager.go(startScene)
        }

        bindData()

        toggle = !toggle
    }
}