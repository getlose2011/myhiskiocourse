package com.getlose.myhiskiocourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.getlose.myhiskiocourse.databinding.ActivityElevenThreeBinding

class ElevenThreeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityElevenThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElevenThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}