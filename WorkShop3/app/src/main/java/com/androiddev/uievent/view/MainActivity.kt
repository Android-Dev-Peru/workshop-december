package com.androiddev.uievent.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.androiddev.uievent.R
import com.androiddev.uievent.databinding.ActivityMainBinding
import com.androiddev.uievent.extensions.isEmail
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.buttonLogIn.setOnClickListener {
            //showSnackbar(it, "Click demo")
            //showToast("Hola comunidad")
            validateData()
        }

        binding.radioButtonGender.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButtonMan -> showToast("Click en hombre")
                R.id.radioButtonWoman -> showToast("click en mujer")
            }
        }


        binding.editTextPassword.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateData()
                showToast("action Clicked")
                true
            } else {
                false
            }
        }
    }

    private fun validateData() {
        validateEmail(binding.editTextEmail.text.toString())
        validatePassword()
    }

    private fun validatePassword() {

    }

    private fun validateEmail(email: String) {
        if (email.isEmpty()) {
            showToast("Campo correo esta vacio")
        }
        if (!email.isEmail()) {
            showToast("Campo no acorde al formato requerido")
        }
    }

    private fun showSnackbar(view: View, text: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(view, text, duration).show()
    }

    private fun showToast(text: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, text, duration).show()
    }

}