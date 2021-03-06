package com.getlose.myhiskiocourse

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.getlose.myhiskiocourse.Adapter.CourseAdapter
import com.getlose.myhiskiocourse.databinding.ActivityMainBinding
import com.getlose.myhiskiocourse.Interfaces.ICourseAdapterListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : BaseActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding
    //取得活動結果
    private val startActivityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){activityResult->
            //第2頁回傳資料
            if ( activityResult.resultCode == Activity.RESULT_OK){
                val name = activityResult.data!!.getStringExtra(INTENT_KEY_NAME)
                Log.d(TAG, "name: 按了修改鍵,修改後的name=>$name")
            }else if ( activityResult.resultCode == Activity.RESULT_CANCELED){
                Log.d(TAG, "name: 按了取消鍵")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var course = resources.getStringArray(R.array.course_items)
        var adapter  = CourseAdapter(course, callBackFromCourseFun)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

        initFCM()
        receiveFcmMessage()
    }

    ///接收FCM傳來的訊息
    private fun receiveFcmMessage() {
        val type = intent.getStringExtra("type")?:""
        val title = intent.getStringExtra("title")?:""
        val data = intent.getStringExtra("data")?:""

        Log.d(TAG, "receiveFcmMessage: type: $type, title: $title, data: $data")

        if(type == "push"){
            startActivity(Intent(this, TwentyOneOneActivity::class.java).apply {
                putExtra("title",title)
                putExtra("data",data)
            })
        }
    }

    //all courses activity
    private val callBackFromCourseFun = object : ICourseAdapterListener {
        override fun onCourseSelected(position: Int) {
        Toast.makeText(baseContext,String.format(getString(R.string.position), position),Toast.LENGTH_SHORT).show()

            when (position) {
                0 -> {
                    val name = "7-3 intent"
                    val intent = Intent(this@MainActivity, SevenThreeActivity::class.java)
                    intent.putExtra(INTENT_KEY_NAME, name)

                    startActivityLauncher.launch(intent)
                }
                1 ->
                    Intent(this@MainActivity, EightFiveActivity::class.java).also {
                        startActivity(it)
                    }
                2 ->
                    Intent(this@MainActivity, ElevenThreeActivity::class.java).also {
                        startActivity(it)
                    }
                3 ->
                    Intent(this@MainActivity, ElevenFourActivity::class.java).also {
                        startActivity(it)
                    }
                4 ->
                    Intent(this@MainActivity, TwelveOneActivity::class.java).also {
                        startActivity(it)
                    }
                5 ->
                    Intent(this@MainActivity, ThirteenOneActivity::class.java).also {
                        startActivity(it)
                    }
                6 ->
                    Intent(this@MainActivity, ThirteenTwoActivity::class.java).also {
                        startActivity(it)
                    }
                7 ->
                    Intent(this@MainActivity, FourTeenOneActivity::class.java).also {
                        startActivity(it)
                    }
                8 ->
                    Intent(this@MainActivity, SevenTeenThreeActivity::class.java).also {
                        startActivity(it)
                    }
                9 ->
                    Intent(this@MainActivity, TwentyOneOneActivity::class.java).also {
                        startActivity(it)
                    }
            }
    }}

    //init fcm
    private fun initFCM() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result
            Log.d(TAG,"Token:$token")
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()

        })
    }

}