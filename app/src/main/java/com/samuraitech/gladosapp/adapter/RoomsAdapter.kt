package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.api.DeviceRestAPI
import com.samuraitech.gladosapp.model.Device
import com.samuraitech.gladosapp.model.Room
import com.samuraitech.gladosapp.utils.Constants
import kotlinx.android.synthetic.main.item_room.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomsAdapter(private val rooms: ArrayList<Room>, val context: Context) : RecyclerView.Adapter<ViewHolderRoom>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRoom {
        return ViewHolderRoom(LayoutInflater.from(context).inflate(R.layout.item_room, parent, false))
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    override fun onBindViewHolder(holderRoom: ViewHolderRoom, position: Int) {
        val room = rooms[position]

        val textViewName = holderRoom.roomName
        val textViewLastActivity = holderRoom.roomLastActivity
        val buttonShowDevices = holderRoom.roomShowDevices

        val roomLastActivity = "Last activity: ${room.lastActivity.substring(0, 10)}"

        textViewName.text = room.name
        textViewLastActivity.text = roomLastActivity

        //Create recyclerViewDevices for every Room
        val recyclerDevices: RecyclerView = holderRoom.recyclerViewDevices

        recyclerDevices.layoutManager = GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false)

        //Load devices in list
        DeviceRestAPI()
            .getByRoomId(room.idRoom)
            .enqueue(object : Callback<List<Device>> {
                override fun onFailure(call: Call<List<Device>>?, t: Throwable?) {
                    t!!.printStackTrace()
                }

                override fun onResponse(call: Call<List<Device>>?, response: Response<List<Device>>?) {
                    try {
                        if (response!!.body() == null) {
                            Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                        } else {
                            val listDevicesInRoom: ArrayList<Device> = ArrayList()

                            response.body().forEach {
                                //Load al coincidences on the list
                                listDevicesInRoom.add(it)
                            }

                            //Build devices items
                            recyclerDevices.adapter = DevicesInRoomAdapter(listDevicesInRoom, context)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })

        buttonShowDevices.setOnClickListener {
            when (recyclerDevices.visibility) {
                View.GONE -> {
                    recyclerDevices.visibility = View.VISIBLE
                    buttonShowDevices.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_action_keyboard_arrow_up
                        )
                    )
                }
                View.VISIBLE -> {
                    recyclerDevices.visibility = View.GONE
                    buttonShowDevices.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_action_keyboard_arrow_down
                        )
                    )
                }
            }
        }
    }
}

class ViewHolderRoom(view: View) : RecyclerView.ViewHolder(view) {
    val roomName: TextView = view.text_routine_name
    val roomLastActivity: TextView = view.text_routine_room_name
    val roomShowDevices: ImageButton = view.btn_show_devices
    val recyclerViewDevices: RecyclerView = view.recycler_view_devices_in_room
}
