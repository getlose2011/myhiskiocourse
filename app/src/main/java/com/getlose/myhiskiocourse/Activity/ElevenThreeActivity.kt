package com.getlose.myhiskiocourse.Activity

import android.os.Bundle
import com.getlose.myhiskiocourse.databinding.ActivityElevenThreeBinding

class ElevenThreeActivity : BaseActivity() {

    private lateinit var binding : ActivityElevenThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElevenThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}