package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.model.Routine
import kotlinx.android.synthetic.main.item_routine.view.*

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
        textViewRoomName.text = routine.roomId.toString()
        textViewInitTime.text = "Init time: ${routine.timeInit}"
        textViewDeviceName.text = routine.deviceId

    }
}

class ViewHolderRoutine(view: View) : RecyclerView.ViewHolder(view) {
    val routineName: TextView = view.text_routine_name
    val routineRoomName: TextView = view.text_routine_room_name
    val routineInitTime: TextView = view.text_init_time
    val switchActive: Switch = view.switch_button_active
    val routineDeviceName: TextView = view.text_routine_device_name
}