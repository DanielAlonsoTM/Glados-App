package com.samuraitech.gladosapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.adapter.RoomsAdapter
import com.samuraitech.gladosapp.model.Room
import com.samuraitech.gladosapp.notification.NotificationManager

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

        addDumpsRooms()

        val recyclerRooms: RecyclerView
        recyclerRooms = viewOfLayout.findViewById(R.id.recycler_view_rooms)

        recyclerRooms.layoutManager = LinearLayoutManager(context!!)
        recyclerRooms.adapter = RoomsAdapter(listRooms, context!!)

        return viewOfLayout
    }

    private val listRooms: ArrayList<Room> = ArrayList()

    private fun addDumpsRooms() {
        listRooms.add(Room("Bathroom", "1 hour ago", 1, 1, 1))
        listRooms.add(Room("Bathroom", "1 hour ago", 1, 1, 1))
        listRooms.add(Room("Bathroom", "1 hour ago", 1, 1, 1))
        listRooms.add(Room("Bathroom", "1 hour ago", 1, 1, 1))
        listRooms.add(Room("Bathroom", "1 hour ago", 1, 1, 1))
    }
}
