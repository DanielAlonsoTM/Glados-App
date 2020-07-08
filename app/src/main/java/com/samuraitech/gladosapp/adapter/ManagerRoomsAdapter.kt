package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.model.Room
import kotlinx.android.synthetic.main.item_editable_room.view.*


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

        nameLayout.hint = room.name
    }
}

class ViewHolderManagerRooms(view: View) : RecyclerView.ViewHolder(view) {
    val roomNameLayout: TextInputLayout = view.edit_room_name
    val roomName: EditText = view.input_room_name
}

