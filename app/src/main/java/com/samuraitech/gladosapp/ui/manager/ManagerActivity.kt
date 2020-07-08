package com.samuraitech.gladosapp.ui.manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.adapter.ManagerDeviceAdapter
import com.samuraitech.gladosapp.adapter.ManagerRoomsAdapter
import com.samuraitech.gladosapp.api.DeviceRestAPI
import com.samuraitech.gladosapp.api.RoomRestAPI
import com.samuraitech.gladosapp.model.Device
import com.samuraitech.gladosapp.model.Room
import java.util.ArrayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listRooms = ArrayList<Room>()
        val listDevices = ArrayList<Device>()

        //Load DataRooms
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
                            val recyclerRooms: RecyclerView = findViewById(R.id.recycler_view_manager_rooms)
                            recyclerRooms.layoutManager = LinearLayoutManager(this@ManagerActivity)
                            recyclerRooms.adapter = ManagerRoomsAdapter(listRooms, this@ManagerActivity)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })

        //Load DataDevices
        DeviceRestAPI()
            .getAllDevices()
            .enqueue(object : Callback<List<Device>> {
                override fun onFailure(call: Call<List<Device>>?, t: Throwable?) {
                    t!!.printStackTrace()
                }

                override fun onResponse(call: Call<List<Device>>?, response: Response<List<Device>>?) {
                    try {
                        if (response!!.body() == null){
                            Log.e("NULL_RESPONSE", "Response is null: $response")
                        } else {
                            response.body().forEach {
                                listDevices.add(it)
                            }

                            val recyclerDevices: RecyclerView = findViewById(R.id.recycler_view_manager_devices)
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
