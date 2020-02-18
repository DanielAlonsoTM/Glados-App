package com.samuraitech.gladosapp.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.samuraitech.gladosapp.ui.main.MainActivity
import com.samuraitech.gladosapp.R

object NotificationManager {
    fun displayNotification(context: Context, title: String, body: String) {

        //Set intent when notification is tapped
        val intent = Intent(context, MainActivity::class.java)
//            .apply {
//                action = "Hey"
//                putExtra("Hey", 0)
//            }

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val builder = NotificationCompat.Builder(context, MainActivity.channelId)
            .setSmallIcon(R.drawable.ic_action_home)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
//            .addAction(R.drawable.ic_action_bluetooth, context.getString(R.string.app_name), pendingIntent)

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Create notification, but only on API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "001"
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(channelId)
        }

        builder.setContentIntent(pendingIntent)

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(1, builder.build())
    }
}
