package com.getlose.myhiskiocourse.CourseOne

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.getlose.myhiskiocourse.Receiver.MyBroadcastReceiver
import com.getlose.myhiskiocourse.databinding.ActivityFifTeenTwoBinding


//Broadcast => APP可以發送及接收來至anroid系統或其它APP廣播訊息
class FifTeenTwoActivity : AppCompatActivity() {

    private val TAG = FifTeenTwoActivity::class.java.simpleName
    private lateinit var binding: ActivityFifTeenTwoBinding
    private lateinit var receiver : MyBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFifTeenTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiver = MyBroadcastReceiver()

        binding.btnSendBroadcast.setOnClickListener {
            //設定廣播 action 識別碼
            val intent = Intent("com.getlose.myhiskiocourse.action1")
            intent.putExtra("name","test broadcast message")
            //發送廣播
            sendBroadcast(intent)
        }
    }

    //使用者可以開始互動
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
        //filter攔截訊息
        val filter = IntentFilter("com.getlose.myhiskiocourse.action1")
        registerReceiver(receiver,filter)
    }

    //使用者正要離開，Activity不在前景，釋放系統資源
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
        unregisterReceiver(receiver)
    }


}