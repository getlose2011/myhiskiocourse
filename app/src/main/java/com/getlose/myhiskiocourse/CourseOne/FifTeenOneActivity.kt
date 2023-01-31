package com.getlose.myhiskiocourse.CourseOne

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.getlose.myhiskiocourse.Service.MyService
import com.getlose.myhiskiocourse.databinding.ActivityFifTeenOneBinding

class FifTeenOneActivity : AppCompatActivity() {

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
            startActivity(Intent(this, FifTeenOneAActivity::class.java))
        }

        binding.btnWorker.setOnClickListener {
            startActivity(Intent(this, FifTeenOneBActivity::class.java))
        }
    }

    override fun onStop() {
        super.onStop()
        // 停止Service
        Log.d(TAG, "onStop1: ")
        stopService(service)
    }

}