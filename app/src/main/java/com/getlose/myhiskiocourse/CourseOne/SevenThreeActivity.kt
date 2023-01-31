package com.getlose.myhiskiocourse.CourseOne

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.getlose.myhiskiocourse.databinding.ActivitySevenThreeBinding

class SevenThreeActivity : BaseActivity() {

    private lateinit var binding : ActivitySevenThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySevenThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(INTENT_KEY_NAME)?:""
        binding.nameEditText.setText(name)

        binding.send.setOnClickListener {
            val intent = Intent()
            val name:String = binding.nameEditText.text.toString()
            intent.putExtra("INTENT_KEY_NAME", name)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.cancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}