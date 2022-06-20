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
import com.getlose.myhiskiocourse.Adapter.ICourseAdapterListener
import com.getlose.myhiskiocourse.databinding.ActivityMainBinding

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
    }

    private val callBackFromCourseFun = object : ICourseAdapterListener {
        override fun onCourseSelected(position: Int) {
        Toast.makeText(this@MainActivity,String.format(getString(R.string.position), position),Toast.LENGTH_SHORT).show()

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
                    Intent(this@MainActivity, FourTeenOneActivity::class.java).also {
                        startActivity(it)
                    }
            }
    }}

}