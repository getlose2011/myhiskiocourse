package com.getlose.myhiskiocourse.Service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.getlose.myhiskiocourse.R
import com.getlose.myhiskiocourse.TwentyOneOneActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService(){

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val id = 0
        val title = remoteMessage.notification?.title ?: ""
        var body = remoteMessage.notification?.body ?: ""

        if ( remoteMessage.data.isNotEmpty()){
            getNotificationValue(remoteMessage.data, "myKey")?.let {
                body += it
                println("myKey:$it")
            }
        }

        if ( Build.VERSION.SDK_INT >= 26) {
            showNotificationO(body, title, id, "channel1", "促銷")
        }else{
            showNotification(body, title, id)
        }
    }

    private fun getNotificationValue(data: Map<String, String>, key: String) :String?{
        if ( data.filter { it.key == key }.map{it.value}.isNotEmpty() ){
            return data.filter { it.key == key }.map{it.value}[0]
        }else{
            return null
        }
    }

    private fun showNotification(body: String, title: String, id: Int) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_home_24)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(getPendingIntent())
            .build()
        notificationManager.notify(id, notificationBuilder)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotificationO(body: String, title: String, id: Int, channelId:String, channelName:String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_home_24)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(getPendingIntent())
            .build()

        val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)

        notificationManager.notify(id, notificationBuilder)
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, TwentyOneOneActivity::class.java)
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}
