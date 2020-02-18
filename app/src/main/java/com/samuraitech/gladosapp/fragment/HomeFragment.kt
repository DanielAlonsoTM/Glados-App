package com.samuraitech.gladosapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.notification.NotificationManager
import com.samuraitech.gladosapp.utils.DialogUtils
import com.samuraitech.gladosapp.utils.Utilities

class HomeFragment : Fragment() {

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewOfLayout = inflater.inflate(R.layout.fragment_home, container, false)

        val btnNotification: Button
        btnNotification = viewOfLayout.findViewById(R.id.btn_notification_test)

        btnNotification.setOnClickListener {
            NotificationManager.displayNotification(viewOfLayout.context, "Test", "test")
        }

        return viewOfLayout
    }
}
