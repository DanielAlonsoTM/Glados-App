package com.samuraitech.gladosapp.ui.splash

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.util.Pair
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 2000 // 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        val iconSplash = findViewById<ImageView>(R.id.splash_icon)
//        val titleSplash = findViewById<TextView>(R.id.splash_title)

//        val pairs = ArrayList<Pair<View, String>>()
//        pairs.add(Pair(iconSplash, iconSplash.transitionName))
//        pairs.add(Pair(titleSplash, titleSplash.transitionName))

//        val options = ActivityOptions.makeSceneTransitionAnimation(this, pairs[0], pairs[1])

        val intent = Intent(this, LoginActivity::class.java)

        Handler().postDelayed({
//            startActivity(intent, options.toBundle())
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, splashTimeOut)
    }
}
