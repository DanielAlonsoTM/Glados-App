package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.api.RoomRestAPI
import com.samuraitech.gladosapp.api.RoutineRestAPI
import com.samuraitech.gladosapp.model.Room
import com.samuraitech.gladosapp.model.Routine
import com.samuraitech.gladosapp.utils.Constants
import kotlinx.android.synthetic.main.item_routine.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoutinesAdapter(private val routines: ArrayList<Routine>, val context: Context) :
    RecyclerView.Adapter<ViewHolderRoutine>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRoutine {
        return ViewHolderRoutine(LayoutInflater.from(context).inflate(R.layout.item_routine, parent, false))
    }

    override fun getItemCount(): Int {
        return routines.size
    }

    override fun onBindViewHolder(holderRoutine: ViewHolderRoutine, position: Int) {
        val routine = routines[position]

        val textViewRoutineName = holderRoutine.routineName
        val textViewRoomName = holderRoutine.routineRoomName
        val textViewDeviceName = holderRoutine.routineDeviceName
        val textViewInitTime = holderRoutine.routineInitTime
        val buttonActiveRoutine = holderRoutine.switchActive

        textViewRoutineName.text = routine.name
        textViewInitTime.text = "Start time: ${routine.timeInit}"
        textViewDeviceName.text = routine.deviceId

        //Get RoomsData
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
                                if (it.idRoom == routine.roomId) {
                                    textViewRoomName.text = it.name
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })

        //Set checked switch buttons
        when (routine.active) {
            0 -> buttonActiveRoutine.isChecked = false
            1 -> buttonActiveRoutine.isChecked = true
        }

        //PUT routines state
        buttonActiveRoutine.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                routine.active = 1
            } else {
                routine.active = 0
            }

            RoutineRestAPI()
                .updateRoutine(routine)
                .enqueue(object : Callback<Routine> {
                    override fun onFailure(call: Call<Routine>?, t: Throwable?) {
                        t!!.printStackTrace()
                    }

                    override fun onResponse(call: Call<Routine>?, response: Response<Routine>?) {
                        if (response!!.body() == null) {
                            Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                        } else {
                            val message = if (routine.action == 0) {
                                "is disable"
                            } else {
                                "is enable"
                            }

                            Toast.makeText(context, "Routine $message", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
    }
}

class ViewHolderRoutine(view: View) : RecyclerView.ViewHolder(view) {
    val routineName: TextView = view.text_routine_name
    val routineRoomName: TextView = view.text_routine_room_name
    val routineInitTime: TextView = view.text_init_time
    val switchActive: Switch = view.switch_button_active
    val routineDeviceName: TextView = view.text_routine_device_name
}