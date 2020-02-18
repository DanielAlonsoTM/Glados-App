package com.samuraitech.gladosapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.utils.DialogUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ConnectFragment : Fragment() {

    companion object {
        fun newInstance(): ConnectFragment = ConnectFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewOfLayout = inflater.inflate(R.layout.fragment_connect, container, false)

        val fab: FloatingActionButton
        fab = viewOfLayout.findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            // BluetoothUtilities.scanDevices(view)

            // WifiUtilities.netWifiService(applicationContext, view)
            DialogUtils.alertDialog(view, viewOfLayout.context, layoutInflater)
        }
        return viewOfLayout
    }
}
