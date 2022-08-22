package com.getlose.myhiskiocourse.WorkManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

//設計Worker類別
class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    val TAG = MyWorker::class.java.simpleName

    override fun doWork(): Result {
        Thread.sleep(5000)
        Log.d(TAG, "doWork: ")
        Log.d(TAG, "end time: ${SimpleDateFormat("hh:mm:ss").format(Date())}")
        return Result.success()
    }
}