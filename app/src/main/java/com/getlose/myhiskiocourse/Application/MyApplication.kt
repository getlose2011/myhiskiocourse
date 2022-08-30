package com.getlose.myhiskiocourse.Application

import android.app.Application
import android.util.Log

class MyApplication: Application() {

    private val TAG = "MyApplication"

    init {
        Log.d(TAG, ": my applciation")
    }

    companion object{
        private var mInstance: MyApplication? = null
        @Synchronized
        fun getInstance(): MyApplication? {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

}