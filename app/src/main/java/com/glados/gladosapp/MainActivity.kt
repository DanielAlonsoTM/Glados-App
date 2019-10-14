package com.glados.gladosapp

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.glados.gladosapp.utils.GraphicsUtilities.changeFullColorAppBar
import com.glados.gladosapp.utils.Utilities

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val requestCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        MainActivity.appContext = applicationContext

        changeFullColorAppBar(this, window, supportActionBar)

        fab.setOnClickListener { view ->
            // BluetoothUtilities.scanDevices(view)

            // WifiUtilities.netWifiService(applicationContext, view)
            // DialogUtils.basicAlert(view, applicationContext)
            basicAlert(view)
        }
    }

    companion object {
        lateinit var appContext: Context
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

    private fun basicAlert(view: View) {
        val builderDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater

        val dialogLayout = inflater.inflate(R.layout.popup_wifi, null)
        val editText = dialogLayout.findViewById<AppCompatEditText>(R.id.passwordWifi)
        val buttonAlert = dialogLayout.findViewById<Button>(R.id.buttonConfirmPassword)

        buttonAlert.setOnClickListener { Utilities.snackBarMessage(view, editText.text.toString()) }

        builderDialog.setView(dialogLayout)

        builderDialog.show()
    }
}
