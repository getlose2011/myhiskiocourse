package com.getlose.myhiskiocourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.getlose.myhiskiocourse.databinding.ActivityTwelveOneBinding

class TwelveOneActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTwelveOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityTwelveOneBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}