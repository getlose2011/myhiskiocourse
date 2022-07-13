package com.getlose.myhiskiocourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.getlose.myhiskiocourse.databinding.ActivityThirteenOneBinding
import com.getlose.myhiskiocourse.databinding.ActivityThirteenTwoBinding

class ThirteenOneActivity : AppCompatActivity() {

    private lateinit var binding : ActivityThirteenOneBinding
    private val SP_NAME = "SP_NAME"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirteenOneBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSpSave.setOnClickListener {
            val userName = binding.edSp.text.toString()

            val pref = getSharedPreferences(SP_NAME, MODE_PRIVATE)
                pref
                .edit()
                .putString("userName",userName)
                .apply()
        }

        binding.btnSpGet.setOnClickListener {
            val pref = getSharedPreferences(SP_NAME, MODE_PRIVATE)
            val userName = pref.getString("userName","")
            binding.edSp.setText(userName)
        }
    }
}