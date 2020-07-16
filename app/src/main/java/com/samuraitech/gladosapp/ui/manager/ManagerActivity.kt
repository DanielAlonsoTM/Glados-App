package com.samuraitech.gladosapp.ui.manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.adapter.ManagerDeviceAdapter
import com.samuraitech.gladosapp.adapter.ManagerRoomsAdapter
import com.samuraitech.gladosapp.api.DeviceRestAPI
import com.samuraitech.gladosapp.api.RoomRestAPI
import com.samuraitech.gladosapp.model.Device
import com.samuraitech.gladosapp.model.Room
import com.samuraitech.gladosapp.utils.DialogUtils
import java.util.ArrayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val linearLayoutRoom: LinearLayout = findViewById(R.id.linear_layout_room)
        val linearLayoutDevice: LinearLayout = findViewById(R.id.linear_layout_device)
        val imageViewRoom: ImageView = findViewById(R.id.image_linear_layout_room)
        val imageViewDevice: ImageView = findViewById(R.id.image_linear_layout_device)
        val buttonAddRoom: Button = findViewById(R.id.button_add_room)
        val buttonAddDevice: Button = findViewById(R.id.button_add_device)

        val recyclerRooms: RecyclerView = findViewById(R.id.recycler_view_manager_rooms)
        val recyclerDevices: RecyclerView = findViewById(R.id.recycler_view_manager_devices)

        //Set initial visibility of recycler views
        recyclerRooms.visibility = View.GONE
        recyclerDevices.visibility = View.GONE

        //Set layout expand and collapse
        linearLayoutRoom.setOnClickListener {
            if (recyclerRooms.visibility == View.GONE) {
                recyclerRooms.visibility = View.VISIBLE
                imageViewRoom.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_action_keyboard_arrow_up
                    )
                )
            } else {
                recyclerRooms.visibility = View.GONE
                imageViewRoom.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_action_keyboard_arrow_down
                    )
                )
            }
        }

        linearLayoutDevice.setOnClickListener {
            if (recyclerDevices.visibility == View.GONE) {
                recyclerDevices.visibility = View.VISIBLE
                imageViewDevice.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_action_keyboard_arrow_up
                    )
                )

            } else {
                recyclerDevices.visibility = View.GONE
                imageViewDevice.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_action_keyboard_arrow_down
                    )
                )
            }
        }

        //Set Add buttons
        buttonAddRoom.setOnClickListener {
            DialogUtils().alertDialogRoom(it, LayoutInflater.from(this))
        }

        buttonAddDevice.setOnClickListener {
            DialogUtils().alertDialogDevice(it, LayoutInflater.from(this), this)
        }

        val listRooms = ArrayList<Room>()
        val listDevices = ArrayList<Device>()

        //Load DataRooms
        getAllRooms(listRooms, recyclerRooms)

        //Load DataDevices
        getAllDevices(listDevices, recyclerDevices)
    }

    private fun getAllRooms(listRooms: ArrayList<Room>, recyclerRooms: RecyclerView) {
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
                                listRooms.add(it)
                            }

                            //Build RecyclerView
                            recyclerRooms.layoutManager = LinearLayoutManager(this@ManagerActivity)
                            recyclerRooms.adapter = ManagerRoomsAdapter(listRooms, this@ManagerActivity)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun getAllDevices(listDevices: ArrayList<Device>, recyclerDevices: RecyclerView) {
        DeviceRestAPI()
            .getAllDevices()
            .enqueue(object : Callback<List<Device>> {
                override fun onFailure(call: Call<List<Device>>?, t: Throwable?) {
                    t!!.printStackTrace()
                }

                override fun onResponse(call: Call<List<Device>>?, response: Response<List<Device>>?) {
                    try {
                        if (response!!.body() == null) {
                            Log.e("NULL_RESPONSE", "Response is null: $response")
                        } else {
                            response.body().forEach {
                                listDevices.add(it)
                            }

                            recyclerDevices.layoutManager = LinearLayoutManager(this@ManagerActivity)
                            recyclerDevices.adapter = ManagerDeviceAdapter(listDevices, this@ManagerActivity)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}
