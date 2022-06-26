package com.getlose.myhiskiocourse

import android.app.Application
import android.util.Log

class MyApplication:Application() {

    private val TAG = "MyApplication"

    init {
        Log.d(TAG, ": my applciation")
    }

}