package com.samuraitech.gladosapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.adapter.NotificationsAdapter
import com.samuraitech.gladosapp.model.Notification
import com.samuraitech.gladosapp.model.NotificationType

class NotificationsFragment : Fragment() {

    companion object {
        fun newInstance(): NotificationsFragment = NotificationsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewOfLayout = inflater.inflate(R.layout.fragment_notifications, container, false)

        addDumpNotifications()

        val recyclerNotifications: RecyclerView
        recyclerNotifications = viewOfLayout.findViewById(R.id.recycler_view_notifications)

        recyclerNotifications.layoutManager = LinearLayoutManager(context!!)
        recyclerNotifications.adapter = NotificationsAdapter(listNotifications, context!!)

        return viewOfLayout
    }

    private val listNotifications: ArrayList<Notification> = ArrayList()

    private fun addDumpNotifications() {

        val drawableSpeaker = ContextCompat.getDrawable(context!!, R.drawable.ic_action_speaker)
        val drawableSmartPhone = ContextCompat.getDrawable(context!!, R.drawable.ic_action_smartphone)
        val drawableTv = ContextCompat.getDrawable(context!!, R.drawable.ic_action_tv)

        listNotifications.add(
            Notification(
                "Smartphone linked successful",
                getString(R.string.dump_text),
                NotificationType.NORMAL,
                drawableSmartPhone!!
            )
        )
        listNotifications.add(
            Notification(
                "Bluetooth Speaker has low battery",
                getString(R.string.dump_text),
                NotificationType.WARNING,
                drawableSpeaker!!
            )
        )
        listNotifications.add(
            Notification(
                "Lost connection with SmartTV",
                getString(R.string.dump_text),
                NotificationType.ERROR,
                drawableTv!!
            )
        )
        listNotifications.add(
            Notification(
                "Bluetooth Speaker has low battery",
                getString(R.string.dump_text),
                NotificationType.WARNING,
                drawableSpeaker
            )
        )
        listNotifications.add(
            Notification(
                "SmartTv has been turned on",
                getString(R.string.dump_text),
                NotificationType.NORMAL,
                drawableTv
            )
        )
    }
}
