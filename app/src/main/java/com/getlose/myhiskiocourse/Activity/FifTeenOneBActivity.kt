package com.getlose.myhiskiocourse.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.getlose.myhiskiocourse.WorkManager.MyWorker
import com.getlose.myhiskiocourse.databinding.ActivityFifTeenOneBactivityBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class FifTeenOneBActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifTeenOneBactivityBinding
    private val TAG = FifTeenOneAActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifTeenOneBactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //產生WorkRequest請求物件
        val workerRequest = OneTimeWorkRequestBuilder<MyWorker>()
            //特定時間後執行，就算Activity或APP結束，也可確保它會執行工作
            .setInitialDelay(15, TimeUnit.SECONDS)
            .build()

        //排定工作
        WorkManager.getInstance(this)
            .enqueue(workerRequest)

        Log.d(TAG, "start time: ${SimpleDateFormat("hh:mm:ss").format(Date())}")
    }
}