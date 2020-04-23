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
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.model.EventType
import com.samuraitech.gladosapp.model.EventRegister
import kotlinx.android.synthetic.main.item_event_register.view.*

class EventRegistersAdapter(private val eventRegisters: ArrayList<EventRegister>, val context: Context) :
    RecyclerView.Adapter<ViewHolderNotification>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNotification {
        return ViewHolderNotification(LayoutInflater.from(context).inflate(R.layout.item_event_register, parent, false))
    }

    override fun getItemCount(): Int {
        return eventRegisters.size
    }

    override fun onBindViewHolder(holder: ViewHolderNotification, position: Int) {
        val eventRegister = eventRegisters[position]

        val viewParent = holder.eventRegisterParent
        val textViewHeader = holder.eventRegisterHeader
        val textViewContent = holder.eventRegisterContent
        val iconEventRegister = holder.eventRegisterIcon

        textViewHeader.text = eventRegister.header
        textViewContent.text = eventRegister.content
        iconEventRegister.setImageDrawable(eventRegister.drawable)

        // Set background view
        val drawableBackground: Drawable

        drawableBackground = when (eventRegister.type) {
            EventType.NORMAL -> ContextCompat.getDrawable(context, R.drawable.event_register_normal)!!
            EventType.WARNING -> ContextCompat.getDrawable(context, R.drawable.event_register_warning)!!
            EventType.ERROR -> ContextCompat.getDrawable(context, R.drawable.event_register_error)!!
        }

        viewParent.background = drawableBackground
    }
}

class ViewHolderNotification(view: View) : RecyclerView.ViewHolder(view) {
    val eventRegisterParent: View = view
    val eventRegisterHeader: TextView = view.text_notification_header
    val eventRegisterContent: TextView = view.text_notification_content
    val eventRegisterIcon: ImageView = view.notification_icon
}
