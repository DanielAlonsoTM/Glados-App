package com.samuraitech.gladosapp.ui.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.fragment.RoutineFragment
import com.samuraitech.gladosapp.fragment.HomeFragment
import com.samuraitech.gladosapp.fragment.EventsRegisterFragment
import com.samuraitech.gladosapp.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val requestCode = 101

    private val homeFragment = HomeFragment.newInstance()
    private val connectFragment = RoutineFragment.newInstance()
    private val eventsRegisterFragment = EventsRegisterFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)

        appContext = applicationContext

        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bnv_connect -> {
                    openFragment(connectFragment)
                    true
                }
                R.id.bnv_home -> {
                    openFragment(homeFragment)
                    true
                }
                R.id.bnv_notifications -> {
                    openFragment(eventsRegisterFragment)
                    true
                }
                else -> false
            }
        }

        bottom_navigation_view.selectedItemId = R.id.bnv_home

        btn_settings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    companion object {
        lateinit var appContext: Context
        var channelId = "simplified_coding"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        setupPermissions()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("PERMISSION", "Permission to coarse denied")
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            requestCode
        )
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
