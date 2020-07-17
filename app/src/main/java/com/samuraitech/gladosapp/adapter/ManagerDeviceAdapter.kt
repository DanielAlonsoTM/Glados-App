package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.api.DeviceRestAPI
import com.samuraitech.gladosapp.api.RoomRestAPI
import com.samuraitech.gladosapp.model.Device
import com.samuraitech.gladosapp.model.Room
import com.samuraitech.gladosapp.utils.Constants
import com.samuraitech.gladosapp.utils.Utilities
import com.samuraitech.gladosapp.utils.Utilities.snackBarMessage
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
        val editTextName = holder.deviceName
        val saveButton = holder.buttonSave
        val deleteButton = holder.buttonDelete
        val spinnerType = holder.spinnerType
        val spinnerRoom = holder.spinnerRoom

        //Set spinnerType Adapter
        val categories: MutableList<String> = ArrayList()
        categories.add("Curtains")
        categories.add("Bluetooth Speaker")
        categories.add("Smart Tv")
        categories.add("Bulb")
        categories.add("Microwave")

        //Build adapter spinnerType
        val dataAdapterType: ArrayAdapter<String> = ArrayAdapter(
            context,
            R.layout.item_spinner_custom,
            categories
        )
        dataAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.adapter = dataAdapterType

        //Set position spinnerType
        val spinnerTypePosition = dataAdapterType.getPosition(device.type)
        spinnerType.setSelection(spinnerTypePosition)

        //Set spinnerRoom Adapter
        loadDataRoomInSpinner(spinnerRoom, device)

        nameLayout.hint = device.name

        saveButton.setOnClickListener { it ->
            if (editTextName.text.isNotEmpty()) {
                device.name = editTextName.text.toString()
            }

            device.roomId = spinnerRoom.selectedItem.let { it as Room }.idRoom
            device.type = spinnerType.selectedItem.toString()

            //Update device
            updateDevice(device, nameLayout, it)
        }

        deleteButton.setOnClickListener {
            deleteDevice(device, position, it)
        }
    }

    private fun deleteDevice(device: Device, position: Int, view: View) {
        DeviceRestAPI()
            .deleteDevice(device)
            .enqueue(object : Callback<Device> {
                override fun onFailure(call: Call<Device>?, t: Throwable?) {
                    t!!.printStackTrace()
                    Toast.makeText(context, "It's not possible delete this device", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Device>?, response: Response<Device>?) {
                    if (response!!.body() == null) {
                        Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                        Toast.makeText(context, "It's not possible delete this device", Toast.LENGTH_SHORT)
                    } else {
                        devices.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, itemCount)

                        snackBarMessage(view, "Device deleted")
                    }
                }
            })
    }

    private fun updateDevice(device: Device, nameLayout: TextInputLayout, view: View) {
        DeviceRestAPI()
            .updateDevice(device)
            .enqueue(object : Callback<Device> {
                override fun onFailure(call: Call<Device>?, t: Throwable?) {
                    t!!.printStackTrace()
                    Toast.makeText(context, "Is not possible update device", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Device>?, response: Response<Device>?) {
                    if (response!!.body() == null) {
                        Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                    } else {
                        snackBarMessage(view, "Device updated!")

                        nameLayout.hint = device.name
                    }
                }
            })
    }

    private fun loadDataRoomInSpinner(spinnerRoom: Spinner, device: Device) {
        RoomRestAPI()
            .getAllRooms()
            .enqueue(object : Callback<List<Room>> {
                override fun onFailure(call: Call<List<Room>>?, t: Throwable?) {
                    t!!.printStackTrace()
                    Toast.makeText(context, "Is not possible load rooms", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<Room>>?, response: Response<List<Room>>?) {
                    if (response!!.body() == null) {
                        Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                        Toast.makeText(context, "Is not possible load rooms", Toast.LENGTH_SHORT).show()
                    } else {
                        val listRooms: ArrayList<Room> = ArrayList()

                        response.body().forEach {
                            listRooms.add(it)
                        }

                        //Build adapter spinnerRoom
                        val dataAdapterRoom: ArrayAdapter<Room> = ArrayAdapter(
                            context, R.layout.item_spinner_custom,
                            listRooms
                        )
                        dataAdapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerRoom.adapter = dataAdapterRoom

                        //Set position spinnerRoom
                        var spinnerRoomPosition = 0

                        response.body().forEach {
                            if (it.idRoom == device.roomId) {
                                spinnerRoomPosition = dataAdapterRoom.getPosition(it)
                            }
                        }

                        spinnerRoom.setSelection(spinnerRoomPosition)
                    }
                }
            })
    }
}

class ViewHolderManagerDevices(view: View) : RecyclerView.ViewHolder(view) {
    val deviceNameLayout: TextInputLayout = view.edit_device_name

    val deviceName: EditText = view.input_device_name

    val spinnerType: Spinner = view.spinner_type
    val spinnerRoom: Spinner = view.spinner_room

    val buttonSave: Button = view.button_device_save
    val buttonDelete: Button = view.button_device_delete
}
