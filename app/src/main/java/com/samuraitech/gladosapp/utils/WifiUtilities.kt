package com.samuraitech.gladosapp.utils

import android.content.Context
import android.net.wifi.SupplicantState
import android.net.wifi.WifiManager
import android.view.View

import com.samuraitech.gladosapp.utils.Utilities.snackBarMessage

object WifiUtilities {
    fun netWifiService(context: Context, view: View) {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo

        if (wifiInfo.supplicantState == SupplicantState.COMPLETED) {
            val ssid = wifiInfo.ssid
            print("BSSID: $ssid")
            snackBarMessage(view, ssid)
        } else {
            print("No hay fiutos")
            snackBarMessage(view, "Cueck")
        }
    }
}
