package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.material.switchmaterial.SwitchMaterial
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.api.InstructionRestAPI
import com.samuraitech.gladosapp.model.ContentInstruction
import com.samuraitech.gladosapp.model.Device
import com.samuraitech.gladosapp.model.EnumType.ActionType
import com.samuraitech.gladosapp.model.Instruction
import com.samuraitech.gladosapp.utils.Constants
import kotlinx.android.synthetic.main.item_device.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.util.*

class DevicesInRoomAdapter(private val devices: ArrayList<Device>, val context: Context) :
    RecyclerView.Adapter<ViewHolderDevice>() {

    val account = GoogleSignIn.getLastSignedInAccount(context)

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

        val drawableIcon = when (device.type) {
            "Bulb" -> {
                R.drawable.icon_bulb
            }
            "Smart Tv" -> {
                R.drawable.ic_action_tv
            }
            "Bluetooth Speaker" -> {
                R.drawable.ic_action_speaker
            }
            "Curtains" -> {
                R.drawable.ic_action_home
            }
            else -> {
                R.drawable.ic_action_warning
            }
        }


        imageViewDevice.setImageDrawable(ContextCompat.getDrawable(context, drawableIcon))

        imageViewDevice.setColorFilter(
            ContextCompat.getColor(context, R.color.colorWhite),
            android.graphics.PorterDuff.Mode.SRC_IN
        )

        switchDevice.setOnCheckedChangeListener { _, isChecked ->
            val actionType = if (isChecked) ActionType.TURN_ON else ActionType.TURN_OFF

            //Build instruction
            Thread(Runnable {
                if (account == null) {
                    Toast.makeText(context, "It's not possible execute this", Toast.LENGTH_SHORT).show()
                } else {
                    val instruction = Instruction(
                        "0",
                        "inst-${UUID.randomUUID()}",
                        account.id!!,
                        LocalDateTime.now().toString(),
                        ContentInstruction(
                            device.idDevice,
                            device.roomId,
                            actionType,
                            1000
                        ),
                        0
                    )

                    insertInstruction(instruction)
                }
            }).start()
        }
    }

    private fun insertInstruction(instruction: Instruction) {
        InstructionRestAPI()
            .insertInstruction(instruction).enqueue(object : Callback<Instruction> {
                override fun onFailure(call: Call<Instruction>?, t: Throwable?) {
                    t!!.printStackTrace()
                }

                override fun onResponse(call: Call<Instruction>?, response: Response<Instruction>?) {
                    try {
                        if (response!!.body() == null) {
                            Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                        } else {
                            val result = if (response.isSuccessful) "SUCCESSFUL" else "ERROR"
                            Log.d("RESULT_RESPONSE", result)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }
}

class ViewHolderDevice(view: View) : RecyclerView.ViewHolder(view) {
    val deviceName: TextView = view.text_device_name
    val deviceImage: ImageView = view.image_device
    val deviceSwitch: SwitchMaterial = view.switch_device
}
