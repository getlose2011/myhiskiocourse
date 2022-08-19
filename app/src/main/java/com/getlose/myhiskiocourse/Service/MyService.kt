package com.getlose.myhiskiocourse.Service

import android.app.Service
import android.content.Intent
import android.os.Binder
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
//呼叫 service和綁定 service 生命週期
//https://developer.android.com/static/images/service_lifecycle.png
class MyService : Service() {

    private val TAG = MyService::class.java.simpleName
    var name = "service"

    private val localBinder = LocalBinder()

    //讓其它的Activity取得此Service
    inner class LocalBinder: Binder() {
        val service:MyService
        get() = this@MyService
    }

    override fun onCreate() {
        super.onCreate()
        // 僅初次建立時呼叫
        Log.d(TAG, "onCreate: ")
    }

    // 每次startService時會呼叫
    //ui執行緒
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d(TAG, "onStartCommand: start")
        Thread{
            Thread.sleep(1500)
            Log.d(TAG, "onStartCommand: 完成")
            //stopSelf();  // 停止Service
        }.start()
        Log.d(TAG, "onStartCommand: end")
        //
        return START_STICKY


    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: ")
        return localBinder
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: 1")
    }
}