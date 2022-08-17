package com.getlose.myhiskiocourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.getlose.myhiskiocourse.Service.MyService
import com.getlose.myhiskiocourse.databinding.ActivityFifTeenOneBinding
import com.getlose.myhiskiocourse.databinding.ActivityMainBinding

class FifTeenOneActivity : AppCompatActivity() {

    private var service : Intent? = null//Intent(this, MyService::class.java)
    private val TAG = FifTeenOneActivity::class.java.simpleName
    private lateinit var binding: ActivityFifTeenOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifTeenOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        service = Intent(this, MyService::class.java)

        // 在MainActivity中啟動HelloService
        // 在MainActivity中啟動HelloService

        startService(service)
    }

    override fun onStop() {
        super.onStop()
        // 停止Service
        //  stopService(service)
    }

}