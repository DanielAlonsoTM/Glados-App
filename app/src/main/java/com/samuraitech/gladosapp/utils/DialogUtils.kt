package com.samuraitech.gladosapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.gms.auth.api.signin.GoogleSignIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.api.DeviceRestAPI
import com.samuraitech.gladosapp.api.RoomRestAPI
import com.samuraitech.gladosapp.model.Device
import com.samuraitech.gladosapp.model.Room
import com.samuraitech.gladosapp.utils.Utilities.snackBarMessage
import java.util.*


class DialogUtils {
    @SuppressLint("InflateParams")
    fun alertDialogRoom(view: View, inflater: LayoutInflater) {
        val dialogLayout = inflater.inflate(R.layout.dialog_add_room, null)

        val builderDialog = AlertDialog.Builder(ContextThemeWrapper(view.context, R.style.AlertDialogCustom))
        builderDialog.setTitle("Add Room")

        val roomName = dialogLayout.findViewById<AppCompatEditText>(R.id.room_name)

        builderDialog.setView(dialogLayout)
        builderDialog.setPositiveButton("Ok") { _: DialogInterface?, _: Int ->
            if (roomName.text!!.isNotEmpty() && roomName.text != null) {
                val room = Room(
                    "",
                    (0..500).random(),
                    roomName.text.toString(),
                    LocalDateTime.now().toString(),
                    "0",
                    "0",
                    "0"
                )

                insertRoom(room, view)
            } else {
                snackBarMessage(view, "Please, type room name")
            }
        }

        builderDialog.show()
    }

    @SuppressLint("InflateParams")
    fun alertDialogDevice(view: View, inflater: LayoutInflater, context: Context) {
        val dialogLayout = inflater.inflate(R.layout.dialog_add_device, null)

        val builderDialog = AlertDialog.Builder(ContextThemeWrapper(view.context, R.style.AlertDialogCustom))
        builderDialog.setTitle("Add Device")

        val deviceName: AppCompatEditText = dialogLayout.findViewById(R.id.device_name)
        val spinnerDeviceType: Spinner = dialogLayout.findViewById(R.id.spinner_type_dialog)
        val spinnerRoom: Spinner = dialogLayout.findViewById(R.id.spinner_room_dialog)

        val account = GoogleSignIn.getLastSignedInAccount(context)

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
        spinnerDeviceType.adapter = dataAdapterType

        //Build adapter spinnerRoom
        loadDataRoomInSpinner(spinnerRoom, view, context)

        builderDialog.setView(dialogLayout)
        builderDialog.setPositiveButton("Ok") { _: DialogInterface?, _: Int ->
            if (deviceName.text!!.isNotEmpty() && deviceName.text != null) {
                val device = Device(
                    "",
                    "dev-${UUID.randomUUID()}",
                    account?.id!!,
                    spinnerRoom.selectedItem.let { it as Room }.idRoom,
                    deviceName.text.toString(),
                    spinnerDeviceType.selectedItem.toString()
                )

                insertDevice(device, view)
            } else {
                snackBarMessage(view, "Please, type d evice name")
            }
        }

        builderDialog.show()
    }

    private fun insertDevice(device: Device, view: View) {
        DeviceRestAPI()
            .insertDevice(device)
            .enqueue(object : Callback<Device> {
                override fun onFailure(call: Call<Device>?, t: Throwable?) {
                    t!!.printStackTrace()
                    snackBarMessage(view, "It's not possible add device")
                }

                override fun onResponse(call: Call<Device>?, response: Response<Device>?) {
                    if (response!!.body() == null) {
                        Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                        snackBarMessage(view, "It's not possible add device")
                    } else {
                        snackBarMessage(view, "Device added")
                    }
                }
            })
    }

    private fun insertRoom(room: Room, view: View) {
        RoomRestAPI()
            .insertRoom(room)
            .enqueue(object : Callback<Room> {
                override fun onFailure(call: Call<Room>?, t: Throwable?) {
                    t!!.printStackTrace()
                    snackBarMessage(view, "It's not possible add room")
                }

                override fun onResponse(call: Call<Room>?, response: Response<Room>?) {
                    if (response!!.body() == null) {
                        Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                        snackBarMessage(view, "It's not possible add room")
                    } else {
                        snackBarMessage(view, "Room added")
                    }
                }
            })
    }

    private fun loadDataRoomInSpinner(spinnerRoom: Spinner, view: View, context: Context) {
        RoomRestAPI()
            .getAllRooms()
            .enqueue(object : Callback<List<Room>> {
                override fun onFailure(call: Call<List<Room>>?, t: Throwable?) {
                    t!!.printStackTrace()
                }

                override fun onResponse(call: Call<List<Room>>?, response: Response<List<Room>>?) {
                    if (response!!.body() == null) {
                        Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                        snackBarMessage(view, "It's not possible load rooms")
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
                    }
                }
            })
    }
}
