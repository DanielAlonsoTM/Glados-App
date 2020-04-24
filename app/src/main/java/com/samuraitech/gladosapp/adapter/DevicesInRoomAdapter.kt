package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.model.Device
import kotlinx.android.synthetic.main.item_device.view.*

class DevicesInRoomAdapter(private val devices: ArrayList<Device>, val context: Context) :
    RecyclerView.Adapter<ViewHolderDevice>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDevice {
        return ViewHolderDevice(LayoutInflater.from(context).inflate(R.layout.item_device, parent, false))
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    override fun onBindViewHolder(holder: ViewHolderDevice, position: Int) {
        val device = devices[position]

        val textViewName = holder.deviceName
        val imageViewDevice = holder.deviceImage
        val switchDevice = holder.deviceSwitch

        textViewName.text = device.name

        imageViewDevice.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_bulb))

        imageViewDevice.setColorFilter(
            ContextCompat.getColor(context, R.color.colorWhite),
            android.graphics.PorterDuff.Mode.SRC_IN
        )

        switchDevice.setOnClickListener {
            Log.d("SWITCH", "test")
        }
    }
}

class ViewHolderDevice(view: View) : RecyclerView.ViewHolder(view) {
    val deviceName: TextView = view.text_device_name
    val deviceImage: ImageView = view.image_device
    val deviceSwitch: SwitchMaterial = view.switch_device
}