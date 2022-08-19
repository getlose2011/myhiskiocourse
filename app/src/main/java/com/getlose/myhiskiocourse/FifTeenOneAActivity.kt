package com.getlose.myhiskiocourse

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.getlose.myhiskiocourse.Service.MyService
import com.getlose.myhiskiocourse.databinding.ActivityFifTeenOneAactivityBinding

class FifTeenOneAActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifTeenOneAactivityBinding
    private val TAG = FifTeenOneAActivity::class.java.simpleName
    //localBinder要在service方法建立
    private lateinit var localBinder:MyService.LocalBinder

    //繫結service callback
    private val serviceConnection = object:ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected: ")
            //連結service，可以取得service 屬性或方法
            localBinder = service as MyService.LocalBinder
            //取得Myservice的name屬性
            localBinder.service.name
            Log.d(TAG, "service name: ${localBinder.service.name}")

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected: ")
            //disconnect service
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFifTeenOneAactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this,MyService::class.java)

        Log.d(TAG, "onCreate: ")
        //bind service
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
        unbindService(serviceConnection)
    }
}