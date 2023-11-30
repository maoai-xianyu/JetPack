package com.mao.jetpack.ui.recyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mao.jetpack.R
import com.mao.jetpack.databinding.ActivityRecyclerBinding
import com.mao.jetpack.utils.Logger

/**
 * @author zhangkun
 * @time 2022/10/24 16:56
 * @Description  多海报正确显示
 */
class ActivityRecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val list = mutableListOf<Int>()
        list.add(R.drawable.a_image_1)
        list.add(R.drawable.a_image_2)
        list.add(R.drawable.a_image_3)
        list.add(R.drawable.a_image_4)
        list.add(R.drawable.a_image_1)
        list.add(R.drawable.a_image_2)
        list.add(R.drawable.a_image_3)
        list.add(R.drawable.a_image_4)
        list.add(R.drawable.a_image_1)
        list.add(R.drawable.a_image_2)
        list.add(R.drawable.a_image_3)
        list.add(R.drawable.a_image_4)
        val adapter = RecyclerAdapter(list)
        binding.rv.adapter = adapter

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // Update the background ImageView's scroll position based on RecyclerView's scroll
                val scrollX = binding.rv.computeHorizontalScrollOffset()
                Logger.debug("scrollX $scrollX")
                /*binding.flBg.scrollX = scrollX
                binding.ivLogo.scrollTo()*/
                //binding.flBg.x = scrollX.toFloat()
                adjustViewsPosition(dx)
            }
        })
        binding.hsBg.setOnTouchListener { _, _ -> true }


        //------

        val adapter2 = RecyclerAdapter(list)
        binding.rv2.adapter = adapter2


        binding.rv2.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // Update the background ImageView's scroll position based on RecyclerView's scroll
                val scrollX = binding.rv2.computeHorizontalScrollOffset()
                Logger.debug("scrollX $scrollX")
                /*binding.flBg.scrollX = scrollX
                binding.ivLogo.scrollTo()*/
                //binding.flBg.x = scrollX.toFloat()
                adjustViewsPosition2(dx)
            }
        })
        binding.hsBg2.setOnTouchListener { _, _ -> true }

    }

    private fun adjustViewsPosition(dx: Int) {
        // Calculate the new X position based on the scroll distance
        val newFlBgX = binding.hsBg.x - dx
        val newIvLogoX = binding.hsBg.x - dx

        // Set the new X positions for View and ImageView
        // binding.flBg.x = newFlBgX;
        binding.hsBg.x = newFlBgX
        binding.ivLogo.x = newIvLogoX;
    }

    private fun adjustViewsPosition2(dx: Int) {
        // Calculate the new X position based on the scroll distance
        val newFlBgX = binding.hsBg2.x - dx
        val newIvLogoX = binding.hsBg2.x - dx

        // Set the new X positions for View and ImageView
        // binding.flBg.x = newFlBgX;
        binding.hsBg2.x = newFlBgX
        binding.ivLogo2.x = newIvLogoX;
    }
}