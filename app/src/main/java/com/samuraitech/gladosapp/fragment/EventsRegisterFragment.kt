package com.samuraitech.gladosapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.adapter.EventRegistersAdapter
import com.samuraitech.gladosapp.api.EventRestAPI
import com.samuraitech.gladosapp.model.EventRegister
import com.samuraitech.gladosapp.model.EnumType.EventType
import com.samuraitech.gladosapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsRegisterFragment : Fragment() {

    companion object {
        fun newInstance(): EventsRegisterFragment = EventsRegisterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewOfLayout = inflater.inflate(R.layout.fragment_events, container, false)

        val listEventRegisters: ArrayList<EventRegister> = ArrayList()

        val recyclerNotifications: RecyclerView
        recyclerNotifications = viewOfLayout.findViewById(R.id.recycler_view_notifications)
        recyclerNotifications.layoutManager = LinearLayoutManager(context!!)

        EventRestAPI().getAllEvents().enqueue(object : Callback<List<EventRegister>> {
            override fun onFailure(call: Call<List<EventRegister>>?, t: Throwable?) {
                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<List<EventRegister>>?, response: Response<List<EventRegister>>?) {
                if (response!!.body() == null) {
                    Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                } else {
                    response.body().forEach {
                        listEventRegisters.add(it)
                    }

                    recyclerNotifications.adapter = EventRegistersAdapter(listEventRegisters, context!!)
                }
            }
        })

        return viewOfLayout
    }

    //Dump Function
//    private fun addDumpNotifications() {
//
//        val drawableSpeaker = ContextCompat.getDrawable(context!!, R.drawable.ic_action_speaker)
//        val drawableSmartPhone = ContextCompat.getDrawable(context!!, R.drawable.ic_action_smartphone)
//        val drawableTv = ContextCompat.getDrawable(context!!, R.drawable.ic_action_tv)
//
//        listEventRegisters.add(
//            EventRegister(
//                "Smartphone linked successful",
//                getString(R.string.dump_text),
//                EventType.NORMAL,
//                drawableSmartPhone!!
//            )
//        )
//        listEventRegisters.add(
//            EventRegister(
//                "Bluetooth Speaker has low battery",
//                getString(R.string.dump_text),
//                EventType.WARNING,
//                drawableSpeaker!!
//            )
//        )
//        listEventRegisters.add(
//            EventRegister(
//                "Lost connection with SmartTV",
//                getString(R.string.dump_text),
//                EventType.ERROR,
//                drawableTv!!
//            )
//        )
//        listEventRegisters.add(
//            EventRegister(
//                "Bluetooth Speaker has low battery",
//                getString(R.string.dump_text),
//                EventType.WARNING,
//                drawableSpeaker
//            )
//        )
//        listEventRegisters.add(
//            EventRegister(
//                "SmartTv has been turned on",
//                getString(R.string.dump_text),
//                EventType.NORMAL,
//                drawableTv
//            )
//        )
//    }
}
