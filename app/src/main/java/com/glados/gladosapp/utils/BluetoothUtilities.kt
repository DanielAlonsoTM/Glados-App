package com.glados.gladosapp.utils

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothHeadset
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.glados.gladosapp.MainActivity
import com.glados.gladosapp.utils.snackBarMessage

object BluetoothUtilities {

    //Get the default adapter
    private val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    fun starBluetooth(view: View) {
        if (bluetoothAdapter.isEnabled) {
            bluetoothAdapter.disable()
            snackBarMessage(view, "Disable Bluetooth")

        } else {
            bluetoothAdapter.enable()
            snackBarMessage(view, "Enable Bluetooth")
        }
    }

    fun scanDevices() {
        bluetoothLeScanner.startScan(bleScanner)
    }


    private val bleScanner = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            Log.d("DeviceListActivity", "onScanResult: ${result?.device?.address} - ${result?.device?.name}")
        }

        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
            super.onBatchScanResults(results)
            Log.d("DeviceListActivity", "onBatchScanResults:${results.toString()}")
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            Log.d("DeviceListActivity", "onScanFailed: $errorCode")
        }

    }



    private val bluetoothLeScanner: BluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner
//        get() {
//            val contextApp = MainActivity.appContext
//            val bluetoothManager = contextApp.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
//            val bluetoothAdapter = bluetoothManager.adapter
//            return bluetoothAdapter.bluetoothLeScanner
//        }

//    class ListDevicesAdapter(context: Context?, resource: Int) : ArrayAdapter<String>(context!!, resource)
}