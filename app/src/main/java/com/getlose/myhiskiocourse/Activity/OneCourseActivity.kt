package com.getlose.myhiskiocourse.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.getlose.myhiskiocourse.Adapter.CourseAdapter
import com.getlose.myhiskiocourse.Interfaces.ICourseAdapterListener
import com.getlose.myhiskiocourse.R
import com.getlose.myhiskiocourse.databinding.ActivityOneCourseBinding

class OneCourseActivity : BaseActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityOneCourseBinding
    //取得活動結果
    private val startActivityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult->
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

        binding = ActivityOneCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var course = resources.getStringArray(R.array.course_items_1)
        var adapter  = CourseAdapter(course, callBackFromCourseFun)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
    }

    //all courses activity
    private val callBackFromCourseFun = object : ICourseAdapterListener {
        override fun onCourseSelected(position: Int) {
            when (position) {
                0 -> {
                    val name = "7-3 intent"
                    val intent = Intent(this@OneCourseActivity, SevenThreeActivity::class.java)
                    intent.putExtra(INTENT_KEY_NAME, name)

                    startActivityLauncher.launch(intent)
                }
                1 ->
                    Intent(this@OneCourseActivity, EightFiveActivity::class.java).also {
                        startActivity(it)
                    }
                2 ->
                    Intent(this@OneCourseActivity, ElevenThreeActivity::class.java).also {
                        startActivity(it)
                    }
                3 ->
                    Intent(this@OneCourseActivity, ElevenFourActivity::class.java).also {
                        startActivity(it)
                    }
                4 ->
                    Intent(this@OneCourseActivity, TwelveOneActivity::class.java).also {
                        startActivity(it)
                    }
                5 ->
                    Intent(this@OneCourseActivity, ThirteenOneActivity::class.java).also {
                        startActivity(it)
                    }
                6 ->
                    Intent(this@OneCourseActivity, ThirteenTwoActivity::class.java).also {
                        startActivity(it)
                    }
                7 ->
                    Intent(this@OneCourseActivity, FourTeenOneActivity::class.java).also {
                        startActivity(it)
                    }
                8 ->
                    Intent(this@OneCourseActivity, FourTeenTwoActivity::class.java).also {
                        startActivity(it)
                    }
                9 ->
                    Intent(this@OneCourseActivity, FifTeenOneActivity::class.java).also {
                        startActivity(it)
                    }
                10 ->
                    Intent(this@OneCourseActivity, FifTeenTwoActivity::class.java).also {
                        startActivity(it)
                    }
                11 ->
                    Intent(this@OneCourseActivity, FifTeenThreeAActivity::class.java).also {
                        startActivity(it)
                    }
                12 ->
                    Intent(this@OneCourseActivity, FifTeenThreeBActivity::class.java).also {
                        startActivity(it)
                    }
                13 ->
                    Intent(this@OneCourseActivity, SevenTeenThreeActivity::class.java).also {
                        startActivity(it)
                    }
                14 ->
                    Intent(this@OneCourseActivity, TwentyOneOneActivity::class.java).also {
                        startActivity(it)
                    }
            }
        }

    }

}