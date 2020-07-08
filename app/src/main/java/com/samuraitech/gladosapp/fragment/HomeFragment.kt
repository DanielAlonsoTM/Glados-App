package com.samuraitech.gladosapp.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.samuraitech.gladosapp.api.RoomRestAPI
import com.samuraitech.gladosapp.model.Room
import com.samuraitech.gladosapp.ui.manager.ManagerActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
//            NotificationManager.displayNotification(viewOfLayout.context, "Test", "test")
            startActivity(Intent(context, ManagerActivity::class.java))
        }

        val listRooms: ArrayList<Room> = ArrayList()

        //Load data
        RoomRestAPI()
            .getAllRooms()
            .enqueue(object : Callback<List<Room>> {
                override fun onFailure(call: Call<List<Room>>?, t: Throwable?) {
                    t!!.printStackTrace()
                }

                override fun onResponse(call: Call<List<Room>>?, response: Response<List<Room>>?) {
                    try {
                        if (response!!.body() == null) {
                            Log.e("NULL_RESPONSE", "Response is null: $response")
                        } else {
                            response.body().forEach {
                                //Add response item to listRooms
                                listRooms.add(it)
                            }

                            //Build Recyclerview
                            val recyclerRooms: RecyclerView = viewOfLayout.findViewById(R.id.recycler_view_rooms)
                            recyclerRooms.layoutManager = LinearLayoutManager(context!!)
                            recyclerRooms.adapter = RoomsAdapter(listRooms, context!!)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })

        return viewOfLayout
    }
}
