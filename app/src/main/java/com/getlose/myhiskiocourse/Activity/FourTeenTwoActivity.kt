package com.getlose.myhiskiocourse.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.getlose.myhiskiocourse.databinding.ActivityFourTeenTwoBinding
import java.lang.Exception

class FourTeenTwoActivity : AppCompatActivity() {

    private val MESSAGE_FINISH = 1
    private val MESSAGE_UPDATE = 2
    private val MESSAGE_FAIL = 3

    private val TAG = FifTeenThreeBActivity::class.java.simpleName
    private lateinit var binding: ActivityFourTeenTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourTeenTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var handler = object : Handler(Looper.myLooper()!!){
            //接收
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                //更新ui
                val text = when (msg.what) {
                    MESSAGE_FINISH -> {
                        "finish"
                    }
                    MESSAGE_UPDATE -> {
                        msg.obj.toString()
                    }
                    MESSAGE_FAIL -> {
                        "fail"
                    }
                    else -> {
                        "parameter error"
                    }
                }
                binding.textView4.text = text
            }
        }

        binding.button5.setOnClickListener {
            //background thread
            Thread{

                for(i in 1..10){
                    Thread.sleep(800)
                    val message = Message()
                    try{
                        if(i == 10){
                            message.what = MESSAGE_FINISH
                        }else{
                            //自訂分類
                            message.what = MESSAGE_UPDATE
                            //自帶參數(任何型態都可以)
                            message.obj = "${i}"
                        }
                    }catch (ex: Exception){
                        message.what = MESSAGE_FAIL
                    }
                    //傳送
                    handler.sendMessage(message)
                }
            }.start()
        }

    }

}