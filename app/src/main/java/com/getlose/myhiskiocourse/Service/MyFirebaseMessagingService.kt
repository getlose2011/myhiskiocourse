package com.getlose.myhiskiocourse.Service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.getlose.myhiskiocourse.CourseOne.MainActivity
import com.getlose.myhiskiocourse.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService(){

    private val TAG = "MyFirebaseMessagingService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)


        val id = 0
        val channelId = remoteMessage.notification?.channelId ?: ""
        val title = remoteMessage.notification?.title ?: ""
        var body = remoteMessage.notification?.body ?: ""

        Log.d(TAG, "onMessageReceived: ${remoteMessage.notification?.channelId}")

        if ( remoteMessage.data.isNotEmpty()){
            getNotificationValue(remoteMessage.data, "myKey")?.let {
                body += it
                println("myKey:$it")
            }
        }

        if ( Build.VERSION.SDK_INT >= 26) {
            showNotificationO(body, title, remoteMessage.hashCode(), channelId, channelId,remoteMessage.data)
        }else{
            showNotification(body, title, remoteMessage.hashCode(),remoteMessage.data)
        }
    }

    private fun getNotificationValue(data: Map<String, String>, key: String) :String?{
        if ( data.filter { it.key == key }.map{it.value}.isNotEmpty() ){
            return data.filter { it.key == key }.map{it.value}[0]
        }else{
            return null
        }
    }

    private fun showNotification(body: String, title: String, id: Int,data: Map<String, String>) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_message_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(getPendingIntent(data))
            .build()
        notificationManager.notify(id, notificationBuilder)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotificationO(body: String, title: String, id: Int, channelId:String, channelName:String,data: Map<String, String>) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_message_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(getPendingIntent(data))
            .build()

        val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)

        notificationManager.notify(id, notificationBuilder)
    }

    private fun getPendingIntent(data: Map<String, String>): PendingIntent {

        val intent = Intent(this, MainActivity::class.java).apply {
            if ( data.isNotEmpty()){
                val type = getNotificationValue(data, "type")
                val title = getNotificationValue(data, "title")
                val data = getNotificationValue(data, "data")
                putExtra("type",type)
                putExtra("title",title)
                putExtra("data",data)
            }
        }
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}
