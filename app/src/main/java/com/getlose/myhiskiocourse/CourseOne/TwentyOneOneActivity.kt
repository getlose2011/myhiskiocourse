package com.getlose.myhiskiocourse.CourseOne

import android.os.Bundle
import com.getlose.myhiskiocourse.databinding.ActivityTwentyOneOneBinding

//google推播設定 => https://firebase.google.com/

class TwentyOneOneActivity : BaseActivity() {

    private lateinit var binding : ActivityTwentyOneOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTwentyOneOneBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val title = intent.getStringExtra("title")?:""
        val data = intent.getStringExtra("data")?:""

        binding.tvMessage.text = title
        binding.tvData.text = data
    }
}