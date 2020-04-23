package com.samuraitech.gladosapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.adapter.RoomsAdapter
import com.samuraitech.gladosapp.model.Device
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
    private val listDevices: ArrayList<Device> = ArrayList()

    //Dump Function
    private fun addDumpsRooms() {
        addDumpsDevices()

        listRooms.add(Room(1, "Bathroom", "1 hour ago", 1, 1, 1, listDevices))
        listRooms.add(Room(2, "BedRoom", "1 hour ago", 1, 1, 1, listDevices))
        listRooms.add(Room(3, "Bathroom", "1 hour ago", 1, 1, 1, listDevices))
        listRooms.add(Room(4, "Bathroom", "1 hour ago", 1, 1, 1, listDevices))
        listRooms.add(Room(5, "Bathroom", "1 hour ago", 1, 1, 1, listDevices))
    }

    //Dump Function
    private fun addDumpsDevices() {
        listDevices.add(Device(1, "Samsung s30 plus 4k", "tv"))
        listDevices.add(Device(2, "Phillips n35xf", "speaker"))
        listDevices.add(Device(3, "Light bulb w30n-l", "light-bulb"))
        listDevices.add(Device(4, "Unknown", "speaker"))
    }
}
