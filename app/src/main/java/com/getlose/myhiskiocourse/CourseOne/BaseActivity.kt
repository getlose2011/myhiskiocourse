package com.getlose.myhiskiocourse.CourseOne

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.getlose.myhiskiocourse.Interfaces.IRetrofitCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseActivity : AppCompatActivity() {

    protected val INTENT_KEY_NAME = "INTENT_KEY_NAME"
    private val TAG = "BaseActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //新增2個Channel，不管有沒有收到推播，都可以先設定Channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel("促銷", "促銷","promotion")
            createChannel("會員", "會員","member")
            createChannel("文章", "文章","article")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(channelId: String, channelName: String, description: String) {
        //先建立Channel
        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.description = description
        notificationManager.createNotificationChannel(notificationChannel)
    }

	//切換 Fragment
    protected fun changeFragment(containerId:Int, fragment: Fragment, tagName:String, addStack:Boolean = false){

        //取得目前在container裡的Fragment物件
        val currentFragment = supportFragmentManager.findFragmentById(containerId)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        transaction.add(containerId, fragment, tagName); // 使用 add 方法。
        if(addStack)transaction.addToBackStack(fragment::class.java.simpleName)
        transaction.commit()

    }

    //retrofit callback
    protected fun  <T> retrofit2Callback(callback: IRetrofitCallBack<T>, pb: ProgressBar?) : Callback<T> {
        pb?.visibility = View.VISIBLE
        return object : Callback<T> {
            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                Log.d(TAG, "onResponse: body callbacks=> ")
                callback.onResponse(call, response)
                pb?.visibility = View.GONE
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                callback.onFailure(call, t)
                pb?.visibility = View.GONE
            }
        }
    }

}