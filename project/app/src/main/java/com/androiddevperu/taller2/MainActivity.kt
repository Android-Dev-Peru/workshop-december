package com.androiddevperu.taller2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val name: String = "Pepito"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        greetings(10)
    }

    fun greetings(numero: Int) {

        val numero2 = numero + 10

        Log.d("Main", "Hola mundo!, ${numero2}")

        // throw NullPointerException()

        Log.i("Main", "Info log")
        Log.w("Main", "Waning log")
        Log.e("Main", "ERRORRRRRRRR")
    }

}