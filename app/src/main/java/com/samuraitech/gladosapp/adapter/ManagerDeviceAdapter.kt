package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.model.Device
import kotlinx.android.synthetic.main.item_editable_device.view.*

class ManagerDeviceAdapter(private val devices: ArrayList<Device>, val context: Context) :
    RecyclerView.Adapter<ViewHolderManagerDevices>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderManagerDevices {
        return ViewHolderManagerDevices(
            LayoutInflater.from(context).inflate(R.layout.item_editable_device, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    override fun onBindViewHolder(holder: ViewHolderManagerDevices, position: Int) {
        val device = devices[position]

        val nameLayout = holder.deviceNameLayout
        val typeLayout = holder.deviceTypeLayout

        val editTextName = holder.deviceName
        val editTextType = holder.deviceType

        nameLayout.hint = device.name
        typeLayout.hint = device.type
    }
}

class ViewHolderManagerDevices(view: View) : RecyclerView.ViewHolder(view) {
    val deviceNameLayout :TextInputLayout = view.edit_device_name
    val deviceTypeLayout :TextInputLayout = view.edit_device_type

    val deviceName: EditText = view.input_device_name
    val deviceType: EditText = view.input_device_type
}
