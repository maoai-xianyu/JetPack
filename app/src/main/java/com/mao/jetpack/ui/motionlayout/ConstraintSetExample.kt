package com.mao.jetpack.ui.motionlayout

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.mao.jetpack.R

class ConstraintSetExample : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_start_m)

        findViewById<ImageView>(R.id.image_film_cover).setOnClickListener(this)
        findViewById<RatingBar>(R.id.rating_film_rating).rating = 4.5f
        findViewById<TextView>(R.id.text_film_title).text = getString(R.string.film_title)
        findViewById<TextView>(R.id.text_film_description).text = getString(R.string.film_description)
    }

    private var toggle = true

    override fun onClick(v: View?) {
        val root = findViewById<ConstraintLayout>(R.id.root)
        TransitionManager.beginDelayedTransition(root)
        val constraintSet = ConstraintSet()
        if (toggle) {
            constraintSet.clone(this, R.layout.activity_constraint_end_m)
        } else {
            constraintSet.clone(this, R.layout.activity_constraint_start_m)
        }
        constraintSet.applyTo(root)
        toggle = !toggle
    }
}
