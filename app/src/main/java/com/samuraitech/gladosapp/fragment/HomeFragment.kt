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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.collections.ArrayList

import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.adapter.RoomsAdapter
import com.samuraitech.gladosapp.api.RoomRestAPI
import com.samuraitech.gladosapp.model.Room
import com.samuraitech.gladosapp.ui.manager.ManagerActivity
import com.samuraitech.gladosapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewOfLayout = inflater.inflate(R.layout.fragment_home, container, false)

        val btnNotification: Button
        btnNotification = viewOfLayout.findViewById(R.id.btn_notification_test)

        btnNotification.setOnClickListener {
//            NotificationManager.displayNotification(viewOfLayout.context, "Test", "test")
            startActivity(Intent(context, ManagerActivity::class.java))
        }

        val listRooms: ArrayList<Room> = ArrayList()

        //Build Recyclerview
        val recyclerRooms: RecyclerView = viewOfLayout.findViewById(R.id.recycler_view_rooms)

        //Load data
        loadData(listRooms, recyclerRooms)

        //Refresh layout
        val swipeRefresh: SwipeRefreshLayout = viewOfLayout.findViewById(R.id.swipe_refresh_home)

        swipeRefresh.setOnRefreshListener {
            listRooms.clear()
            loadData(listRooms, recyclerRooms)
            swipeRefresh.isRefreshing = false
        }

        return viewOfLayout
    }

    private fun loadData(listRooms: ArrayList<Room>, recyclerRooms: RecyclerView) {
        RoomRestAPI()
            .getAllRooms()
            .enqueue(object : Callback<List<Room>> {
                override fun onFailure(call: Call<List<Room>>?, t: Throwable?) {
                    t!!.printStackTrace()
                }

                override fun onResponse(call: Call<List<Room>>?, response: Response<List<Room>>?) {
                    try {
                        if (response!!.body() == null) {
                            Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                        } else {
                            response.body().forEach {
                                //Add response item to listRooms
                                listRooms.add(it)
                            }

                            recyclerRooms.layoutManager = LinearLayoutManager(context!!)
                            recyclerRooms.adapter = RoomsAdapter(listRooms, context!!)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }
}
