package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.communication.ClientService
import com.samuraitech.gladosapp.model.Device
import com.samuraitech.gladosapp.model.EnumType.ActionType
import com.samuraitech.gladosapp.model.Message
import com.samuraitech.gladosapp.utils.MessagesUtils
import kotlinx.android.synthetic.main.item_device.view.*
import java.time.LocalDateTime
import kotlin.collections.ArrayList

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

        switchDevice.setOnCheckedChangeListener { _, isChecked ->
            val actionType = if (isChecked) ActionType.TURN_ON else ActionType.TURN_OFF

            //Build instruction
            Thread(Runnable {
                val instruction = Message.Instruction(
                    device.id.toString(),
                    device.roomId,
                    actionType,
                    1000
                )

                val message = Message.Body(
                    "A001",
                    LocalDateTime.now().toString(),
                    Message.MessageType.INSTRUCTION,
                    instruction
                )

                MessagesUtils.messageToJSONFormat(message)?.let { ClientService.sendMessageToServer(it) }
            }).start()
        }
    }
}

class ViewHolderDevice(view: View) : RecyclerView.ViewHolder(view) {
    val deviceName: TextView = view.text_device_name
    val deviceImage: ImageView = view.image_device
    val deviceSwitch: SwitchMaterial = view.switch_device
}
