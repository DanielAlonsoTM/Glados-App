package com.samuraitech.gladosapp.ui.main

import android.Manifest
import android.content.Context
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
import com.samuraitech.gladosapp.fragment.ConnectFragment
import com.samuraitech.gladosapp.fragment.HomeFragment
import com.samuraitech.gladosapp.utils.GraphicsUtilities.changeFullColorAppBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val requestCode = 101

    private val homeFragment = HomeFragment.newInstance()
    private val connectFragment = ConnectFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        appContext = applicationContext

        changeFullColorAppBar(this, window, supportActionBar)

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
                R.id.bnv_settings -> {
                    openFragment(connectFragment)
                    true
                }
                else -> false
            }
        }

        bottom_navigation_view.selectedItemId = R.id.bnv_home
    }

    companion object {
        lateinit var appContext: Context
        var channelId = "simplified_coding"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
