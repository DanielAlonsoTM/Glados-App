package com.samuraitech.gladosapp.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_event_register.view.*

import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.model.EnumType.EventType
import com.samuraitech.gladosapp.model.EventRegister

class EventRegistersAdapter(private val eventRegisters: ArrayList<EventRegister>, val context: Context) :
    RecyclerView.Adapter<ViewHolderEventRegister>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEventRegister {
        return ViewHolderEventRegister(
            LayoutInflater.from(context).inflate(R.layout.item_event_register, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return eventRegisters.size
    }

    override fun onBindViewHolder(holder: ViewHolderEventRegister, position: Int) {
        val eventRegister = eventRegisters[position]

        val viewParent = holder.eventRegisterParent
        val textViewHeader = holder.eventRegisterHeader
        val textViewContent = holder.eventRegisterContent
        val iconEventRegister = holder.eventRegisterIcon

        textViewHeader.text = eventRegister.title
        textViewContent.text = eventRegister.description

        val drawableIcon = when (eventRegister.deviceType) {
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
            null -> {
                R.drawable.ic_action_warning
            }
            else -> {
                R.drawable.ic_action_warning
            }
        }

        iconEventRegister.setImageDrawable(ContextCompat.getDrawable(context, drawableIcon))

        iconEventRegister.setColorFilter(
            ContextCompat.getColor(context, R.color.colorWhite),
            android.graphics.PorterDuff.Mode.SRC_IN
        )

        // Set background view
        val drawableBackground: Drawable

        drawableBackground = when (eventRegister.type) {
            "NORMAL" -> ContextCompat.getDrawable(context, R.drawable.event_register_normal)!!
            "WARNING" -> ContextCompat.getDrawable(context, R.drawable.event_register_warning)!!
            "ERROR" -> ContextCompat.getDrawable(context, R.drawable.event_register_error)!!
            else -> ContextCompat.getDrawable(context, R.drawable.event_register_error)!!
        }

        viewParent.background = drawableBackground
    }
}

class ViewHolderEventRegister(view: View) : RecyclerView.ViewHolder(view) {
    val eventRegisterParent: View = view
    val eventRegisterHeader: TextView = view.text_notification_header
    val eventRegisterContent: TextView = view.text_notification_content
    val eventRegisterIcon: ImageView = view.notification_icon
}
