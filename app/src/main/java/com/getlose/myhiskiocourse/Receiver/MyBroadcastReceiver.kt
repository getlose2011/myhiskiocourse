package com.getlose.myhiskiocourse.Receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

//接收
class MyBroadcastReceiver : BroadcastReceiver() {

    val TAG = MyBroadcastReceiver::class.java.simpleName

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: ")
        val name = intent?.getStringExtra("name")
        Log.d(TAG, "onReceive: name => $name")
    }
}