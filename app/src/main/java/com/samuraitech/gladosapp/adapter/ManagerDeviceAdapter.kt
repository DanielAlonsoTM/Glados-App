package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.api.DeviceRestAPI
import com.samuraitech.gladosapp.model.Device
import com.samuraitech.gladosapp.utils.Constants
import kotlinx.android.synthetic.main.item_editable_device.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val saveButton = holder.buttonSave

        nameLayout.hint = device.name
        typeLayout.hint = device.type

        saveButton.setOnClickListener {
            if (editTextName.text.isNotEmpty()) {
                device.name = editTextName.text.toString()
            }
            if (editTextType.text.isNotEmpty()) {
                device.type = editTextType.text.toString()
            }

            DeviceRestAPI()
                .updateDevice(device)
                .enqueue(object : Callback<Device> {
                    override fun onFailure(call: Call<Device>?, t: Throwable?) {
                        t!!.printStackTrace()
                        Toast.makeText(context, "Is not possible update device", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<Device>?, response: Response<Device>?) {
                        if (response!!.body() == null) {
                            Log.e(Constants.TAG_NULL_RESPONSE,"$response")
                        } else {
                            Toast.makeText(context, "Device updated!", Toast.LENGTH_SHORT).show()

                            nameLayout.hint = device.name
                            typeLayout.hint = device.type
                        }
                    }
                })
        }
    }
}

class ViewHolderManagerDevices(view: View) : RecyclerView.ViewHolder(view) {
    val deviceNameLayout :TextInputLayout = view.edit_device_name
    val deviceTypeLayout :TextInputLayout = view.edit_device_type

    val deviceName: EditText = view.input_device_name
    val deviceType: EditText = view.input_device_type

    val buttonSave: Button = view.button_device_save
}
