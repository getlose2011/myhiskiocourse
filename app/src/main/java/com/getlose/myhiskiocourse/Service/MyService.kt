package com.getlose.myhiskiocourse.Service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait

//Service需手動將其結束
//1.stopSelf()
//2.stopService
class MyService : Service() {

    private val TAG = MyService::class.java.simpleName
    
    override fun onCreate() {
        super.onCreate()
        // 僅初次建立時呼叫
        Log.d(TAG, "onCreate: ")
    }

    // 每次startService時會呼叫
    //ui執行緒
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        /*
        Log.d(TAG, "onStartCommand: start")
        Thread{
            Thread.sleep(5000)
            Log.d(TAG, "onStartCommand: 完成")
        }.start()
        Log.d(TAG, "onStartCommand: end")
        //stopSelf();  // 停止Service
        return START_STICKY*/

        Log.d(TAG, "onStartCommand: start")
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            Log.d(TAG, "onStartCommand: 完成")
            stopSelf() // 停止Service
        }
        Log.d(TAG, "onStartCommand: end")
        //
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: ")
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: 1")
    }
}