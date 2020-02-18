package com.samuraitech.gladosapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.ui.main.MainActivity
import com.samuraitech.gladosapp.utils.GraphicsUtilities.makeTransparentActionBar

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        makeTransparentActionBar(window, supportActionBar)

        val buttonSignUp = findViewById<AppCompatButton>(R.id.btn_signup)

        buttonSignUp.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
