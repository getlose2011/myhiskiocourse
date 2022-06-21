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
import com.getlose.myhiskiocourse.interfaces.ICourseAdapterListener
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
                    Intent(this@MainActivity, ElevenThreeActivity::class.java).also {
                        startActivity(it)
                    }
                2 ->
                    Intent(this@MainActivity, ElevenFourActivity::class.java).also {
                        startActivity(it)
                    }
                3 ->
                    Intent(this@MainActivity, TwelveOneActivity::class.java).also {
                        startActivity(it)
                    }
                4 ->
                    Intent(this@MainActivity, FourTeenOneActivity::class.java).also {
                        startActivity(it)
                    }
                5 ->
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