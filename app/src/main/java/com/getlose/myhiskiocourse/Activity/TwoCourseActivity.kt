package com.getlose.myhiskiocourse.Activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.getlose.myhiskiocourse.Adapter.CourseAdapter
import com.getlose.myhiskiocourse.Interfaces.ICourseAdapterListener
import com.getlose.myhiskiocourse.R
import com.getlose.myhiskiocourse.databinding.ActivityTwoCourseBinding

class TwoCourseActivity : BaseActivity() {

    private val TAG = TwoCourseActivity::class.java.simpleName
    private lateinit var binding : ActivityTwoCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTwoCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var course = resources.getStringArray(R.array.course_items_2)
        var adapter  = CourseAdapter(course, callBackFromCourseFun)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
    }

    //all courses activity
    private val callBackFromCourseFun = object : ICourseAdapterListener {
        override fun onCourseSelected(position: Int) {
            when (position) {
                0 -> {
                    Intent(this@TwoCourseActivity, SixFourTwoActivity::class.java).also {
                        startActivity(it)                    }
                }

            }
        }

    }
}