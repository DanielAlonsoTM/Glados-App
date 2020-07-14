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
import com.samuraitech.gladosapp.api.RoomRestAPI
import com.samuraitech.gladosapp.model.Room
import com.samuraitech.gladosapp.utils.Constants
import kotlinx.android.synthetic.main.item_editable_room.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ManagerRoomsAdapter(private val rooms: ArrayList<Room>, val context: Context) :
    RecyclerView.Adapter<ViewHolderManagerRooms>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderManagerRooms {
        return ViewHolderManagerRooms(LayoutInflater.from(context).inflate(R.layout.item_editable_room, parent, false))
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    override fun onBindViewHolder(holder: ViewHolderManagerRooms, position: Int) {
        val room = rooms[position]

        val nameLayout = holder.roomNameLayout
        val editTextName = holder.roomName
        val saveButton = holder.saveButton
        val deleteButton = holder.deleteButton

        nameLayout.hint = room.name

        saveButton.setOnClickListener {
            if (editTextName.text.isNotEmpty()) {
                room.name = editTextName.text.toString()
            }
            updateRoom(room, nameLayout)
        }

        deleteButton.setOnClickListener {
            deleteRoom(room, position)
        }
    }

    private fun deleteRoom(room: Room, position: Int) {
        RoomRestAPI()
            .deleteRoom(room.idDocument)
            .enqueue(object : Callback<Room> {
                override fun onFailure(call: Call<Room>?, t: Throwable?) {
                    t!!.printStackTrace()
                    Toast.makeText(context, "It's not possible delete room", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Room>?, response: Response<Room>?) {
                    if (response!!.body() == null) {
                        Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                        Toast.makeText(context, "It's not possible delete room", Toast.LENGTH_SHORT).show()
                    } else {
                        rooms.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, itemCount)

                        Toast.makeText(context, "Room deleted", Toast.LENGTH_SHORT)
                    }
                }
            })
    }

    private fun updateRoom(room: Room, nameLayout: TextInputLayout) {
        RoomRestAPI()
            .updateRoom(room)
            .enqueue(object : Callback<Room> {
                override fun onFailure(call: Call<Room>?, t: Throwable?) {
                    t!!.printStackTrace()
                }

                override fun onResponse(call: Call<Room>?, response: Response<Room>?) {
                    if (response!!.body() == null) {
                        Log.e(Constants.TAG_NULL_RESPONSE, "Null response: $response")
                    } else {
                        Toast.makeText(context, "Room edited!", Toast.LENGTH_SHORT).show()
                        nameLayout.hint = room.name
                    }
                }
            })
    }
}

class ViewHolderManagerRooms(view: View) : RecyclerView.ViewHolder(view) {
    val roomNameLayout: TextInputLayout = view.edit_room_name
    val roomName: EditText = view.input_room_name
    val saveButton: Button = view.button_room_save
    val deleteButton: Button = view.button_room_delete
}

