package com.getlose.myhiskiocourse.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.getlose.myhiskiocourse.Adapter.CourseAdapter
import com.getlose.myhiskiocourse.Interfaces.ICourseAdapterListener
import com.getlose.myhiskiocourse.R
import com.getlose.myhiskiocourse.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : BaseActivity() {

	private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var course = resources.getStringArray(R.array.course_items)
        var adapter  = CourseAdapter(course, callBackFromCourseFun)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

        initFCM()
        receiveFcmMessage()
	
	}

    ///接收FCM傳來的訊息
    private fun receiveFcmMessage() {
        val type = intent.getStringExtra("type")?:""
        val title = intent.getStringExtra("title")?:""
        val data = intent.getStringExtra("data")?:""

        Log.d(TAG, "receiveFcmMessage: type: $type, title: $title, data: $data")

        if(type == "push"){
            startActivity(Intent(this, TwentyOneOneActivity::class.java).apply {
                putExtra("title",title)
                putExtra("data",data)
            })
        }
    }

    //all courses activity
    private val callBackFromCourseFun = object : ICourseAdapterListener {
        override fun onCourseSelected(position: Int) {
        Toast.makeText(baseContext,String.format(getString(R.string.position), position),Toast.LENGTH_SHORT).show()

            when (position) {
                0 -> {
                    Intent(this@MainActivity, OneCourseActivity::class.java).also {
                        startActivity(it)
                    }
                }
            }
    }}

    //init fcm
    private fun initFCM() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result
            Log.d(TAG,"Token:$token")
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()

        })
    }

}