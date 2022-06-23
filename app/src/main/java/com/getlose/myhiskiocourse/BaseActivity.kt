package com.getlose.myhiskiocourse

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
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
    }

	//切換 Fragment
    protected fun changeFragment(containerId:Int, fragment: Fragment){
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
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