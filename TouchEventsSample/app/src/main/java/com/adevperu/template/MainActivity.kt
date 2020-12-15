package com.adevperu.template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children

class MainActivity : AppCompatActivity() {

    private fun clearViews() {
        findViewById<FrameLayout>(R.id.flayContainer).removeAllViews()
    }

    private fun addCardView() {
        val view = CardView(this)
        findViewById<FrameLayout>(R.id.flayContainer).addView(view)
        view.addListener {
            findViewById<FrameLayout>(R.id.flayContainer).removeView(it)
        }
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