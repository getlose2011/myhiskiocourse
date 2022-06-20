package com.getlose.myhiskiocourse

import android.os.Bundle
import android.view.View
import com.getlose.myhiskiocourse.databinding.ActivityFourTeenOneBinding

class FourTeenOneActivity : BaseActivity() {

    private lateinit var binding : ActivityFourTeenOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourTeenOneBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.button.setOnClickListener {
            //模擬耗時的工作
            Thread.sleep(3000)
            //非同步執行緒
            Thread {
                //ui線程
                runOnUiThread {
                    binding.textView.text = "A"
                    binding.button.visibility = View.VISIBLE
                }
            }.start()
        }
    }

}