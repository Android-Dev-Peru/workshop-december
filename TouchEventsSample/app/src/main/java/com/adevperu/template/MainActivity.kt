package com.adevperu.template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children

class MainActivity : AppCompatActivity() {

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun clearViews() {
        findViewById<FrameLayout>(R.id.flayContainer).removeAllViews()
    }

    private fun addCardView() {
        val view = CardView(this)
        findViewById<FrameLayout>(R.id.flayContainer).addView(view)
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ui()
    }

    companion object {
        const val MESSAGE = "Hello Kotlin by Android Dev Perú !"
    }
}