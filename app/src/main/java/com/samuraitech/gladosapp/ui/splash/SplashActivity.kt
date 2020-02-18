package com.samuraitech.gladosapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.ui.login.LoginActivity
import com.samuraitech.gladosapp.utils.GraphicsUtilities.makeTransparentActionBar

class SplashActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 2000 // 2 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        makeTransparentActionBar(window, supportActionBar)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, splashTimeOut)
    }
}
