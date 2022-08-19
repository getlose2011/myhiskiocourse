package com.getlose.myhiskiocourse

import android.app.Service
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.getlose.myhiskiocourse.Service.MyService
import com.getlose.myhiskiocourse.databinding.ActivityFifTeenOneBinding

class FifTeenOneActivity : AppCompatActivity() {

    //private var service : Intent? = null//Intent(this, MyService::class.java)
    private val TAG = FifTeenOneActivity::class.java.simpleName
    private lateinit var binding: ActivityFifTeenOneBinding
    private  var service : Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifTeenOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        service = Intent(this, MyService::class.java)

        startService(service)

        binding.button4.setOnClickListener {
            startActivity(Intent(this,FifTeenOneAActivity::class.java))
        }
    }

    override fun onStop() {
        super.onStop()
        // 停止Service
        Log.d(TAG, "onStop1: ")
        stopService(service)
    }

}