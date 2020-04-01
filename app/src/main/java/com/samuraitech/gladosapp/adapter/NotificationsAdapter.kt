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
import com.samuraitech.gladosapp.model.Notification
import com.samuraitech.gladosapp.model.NotificationType
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationsAdapter(private val notifications: ArrayList<Notification>, val context: Context) :
    RecyclerView.Adapter<ViewHolderNotification>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNotification {
        return ViewHolderNotification(LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false))
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: ViewHolderNotification, position: Int) {
        val notification = notifications[position]

        val viewParent = holder.notificationParent
        val textViewHeader = holder.notificationHeader
        val textViewContent = holder.notificationContent
        val iconNotification = holder.notificationIcon

        textViewHeader.text = notification.header
        textViewContent.text = notification.content
        iconNotification.setImageDrawable(notification.drawable)

        // Set background view
        val drawableBackground: Drawable

        drawableBackground = when (notification.type) {
            NotificationType.NORMAL -> ContextCompat.getDrawable(context, R.drawable.notification_normal)!!
            NotificationType.WARNING -> ContextCompat.getDrawable(context, R.drawable.notification_warning)!!
            NotificationType.ERROR -> ContextCompat.getDrawable(context, R.drawable.notification_error)!!
        }

        viewParent.background = drawableBackground
    }
}

class ViewHolderNotification(view: View) : RecyclerView.ViewHolder(view) {
    val notificationParent: View = view
    val notificationHeader: TextView = view.text_notification_header
    val notificationContent: TextView = view.text_notification_content
    val notificationIcon: ImageView = view.notification_icon
}
