package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.model.Room
import kotlinx.android.synthetic.main.item_room.view.*

class RoomsAdapter(private val items: ArrayList<Room>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_room, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = items[position]

        val textViewName = holder.roomName
        val textViewLastActivity = holder.roomLastActivity
        val textViewTemperature = holder.roomTemperature
        val textViewKilowattsConsumed = holder.roomKilowatts
        val textViewHumidity = holder.roomHumidity

        val roomTemperature = "${room.temperature} °C"
        val roomKilowatts = "${room.kilowattsConsumed} kWh"
        val roomHumidity = "${room.humidity} %"

        textViewName.text = room.name
        textViewLastActivity.text = room.lastActivity
        textViewTemperature.text = roomTemperature
        textViewKilowattsConsumed.text = roomKilowatts
        textViewHumidity.text = roomHumidity
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val roomName: TextView = view.text_room_name
    val roomLastActivity: TextView = view.text_last_activity
    val roomTemperature: TextView = view.text_temperature
    val roomKilowatts: TextView = view.text_kilowatts
    val roomHumidity: TextView = view.text_humidity
}