package com.samuraitech.gladosapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.adapter.EventRegistersAdapter
import com.samuraitech.gladosapp.api.EventRestAPI
import com.samuraitech.gladosapp.model.EventRegister
import com.samuraitech.gladosapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsRegisterFragment : Fragment() {
    companion object {
        fun newInstance(): EventsRegisterFragment = EventsRegisterFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewOfLayout = inflater.inflate(R.layout.fragment_events, container, false)

        val listEventRegisters: ArrayList<EventRegister> = ArrayList()

        val recyclerViewEvent: RecyclerView
        recyclerViewEvent = viewOfLayout.findViewById(R.id.recycler_view_events)
        recyclerViewEvent.layoutManager = LinearLayoutManager(context!!)

        loadData(listEventRegisters, recyclerViewEvent)

        val swipeRefresh: SwipeRefreshLayout = viewOfLayout.findViewById(R.id.swipe_refresh_events)

        swipeRefresh.setOnRefreshListener {
            listEventRegisters.clear()
            loadData(listEventRegisters, recyclerViewEvent)
            swipeRefresh.isRefreshing = false
        }

        return viewOfLayout
    }

    private fun loadData(listEventRegisters: ArrayList<EventRegister>, recyclerNotifications: RecyclerView) {
        EventRestAPI().getAllEvents().enqueue(object : Callback<List<EventRegister>> {
            override fun onFailure(call: Call<List<EventRegister>>?, t: Throwable?) {
                t!!.printStackTrace()
                Toast.makeText(context, "It's not possible get events", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<EventRegister>>?, response: Response<List<EventRegister>>?) {
                if (response!!.body() == null) {
                    Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                } else {
                    response.body().forEach {
                        listEventRegisters.add(it)
                    }

                    listEventRegisters.sortByDescending { it.date }

                    recyclerNotifications.adapter = EventRegistersAdapter(listEventRegisters, context!!)
                }
            }
        })
    }
}
