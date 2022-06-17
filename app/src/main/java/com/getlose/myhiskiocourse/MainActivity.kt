package com.getlose.myhiskiocourse

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.getlose.myhiskiocourse.Adapter.CourseAdapter
import com.getlose.myhiskiocourse.Adapter.ICourseAdapterListener
import com.getlose.myhiskiocourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var course = resources.getStringArray(R.array.course_items)
        var adapter  = CourseAdapter(course, callBackFromCourseFun)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
    }

    private val callBackFromCourseFun = object : ICourseAdapterListener {
        override fun onCourseSelected(position: Int) {
        Toast.makeText(this@MainActivity,String.format(getString(R.string.position), position),Toast.LENGTH_SHORT).show()
    }}

}