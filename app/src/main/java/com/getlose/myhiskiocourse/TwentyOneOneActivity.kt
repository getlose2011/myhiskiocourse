package com.getlose.myhiskiocourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.getlose.myhiskiocourse.databinding.ActivityTwentyOneOneBinding

//google推播設定 => https://firebase.google.com/

class TwentyOneOneActivity : BaseActivity() {

    private lateinit var binding : ActivityTwentyOneOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTwentyOneOneBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_twenty_one_one)


    }
}