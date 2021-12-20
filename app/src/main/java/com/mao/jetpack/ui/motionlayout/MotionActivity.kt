package com.mao.jetpack.ui.motionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import com.mao.jetpack.R

class MotionActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_motion_layout_m)

    findViewById<RatingBar>(R.id.rating_film_rating).rating = 4.5f
    findViewById<TextView>(R.id.text_film_title).text = getString(R.string.film_title)
    findViewById<TextView>(R.id.text_film_description).text = getString(R.string.film_description)
  }
}
