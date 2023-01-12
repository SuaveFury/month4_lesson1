package com.example.month4_lesson1.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.month4_lesson1.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseNotification : FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        Log.e("ololo", "onMessageReceived: "+message.notification?.title )
        Log.e("ololo", "onMessageReceived: "+message.notification?.body )
        super.onMessageReceived(message)
        sendNotification(message)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification(remoteMessage: RemoteMessage){
        val notificationBuilder = NotificationCompat.Builder(this, "task_channelId")
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
        notificationBuilder.setContentTitle(remoteMessage.notification?.title)
        notificationBuilder.setContentText(remoteMessage.notification?.body)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            "task_channelId",
        "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT)

        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(1, notificationBuilder.build())
    }
}