package com.adevperu.template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {

    private val container by lazy {
        findViewById<FrameLayout>(R.id.flayContainer)
    }

    private fun clearViews() {
        container.removeAllViews()
    }

    private fun addCardView(view: View) {
        container.addView(view)
    }

    private fun removeCardView(view: View) {
        container.removeView(view)
    }

    private fun addCardView() {
        val view = CardView(this)
        addCardView(view.also { itCardView ->
            itCardView.addListener {
                removeCardView(itCardView)
            }
        })
    }

    private fun populateCards() {
        for (i in 0..4) {
            addCardView()
        }
    }

    private fun ui() {
        findViewById<Button>(R.id.btnReset).setOnClickListener {
            clearViews()
            populateCards()
        }
        populateCards()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ui()
    }
}