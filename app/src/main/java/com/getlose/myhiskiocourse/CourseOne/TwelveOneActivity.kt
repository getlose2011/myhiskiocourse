package com.getlose.myhiskiocourse.CourseOne

import android.os.Bundle
import android.util.Log
import com.getlose.myhiskiocourse.Fragment.TwelveAFragment
import com.getlose.myhiskiocourse.Fragment.TwelveBFragment
import com.getlose.myhiskiocourse.Fragment.TwelveCFragment
import com.getlose.myhiskiocourse.Interfaces.IBottomNavigationViewListener
import com.getlose.myhiskiocourse.R
import com.getlose.myhiskiocourse.databinding.ActivityTwelveOneBinding

interface TempToolbarTitleListener {
    fun updateTitle(title: String)
}

class TwelveOneActivity : BaseActivity(),TempToolbarTitleListener {

    private val TAG = TwelveOneActivity::class.java.simpleName
    private lateinit var binding : ActivityTwelveOneBinding
    //private lateinit var a:TwelveAFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityTwelveOneBinding.inflate(layoutInflater)

        setContentView(binding.root)

        changeFragment(R.id.container, TwelveAFragment.newInstance(),TwelveAFragment::class.java.simpleName)


        binding.bottomNavigationView.setOnItemSelectedListener{
            Log.d(TAG, "item id => ${it.itemId}")
            //取得目前在container裡的Fragment物件
            val fragmentInstance = supportFragmentManager.findFragmentById(R.id.container)
            Log.d(TAG, "fragmentInstance => ${fragmentInstance}")
            when(it.itemId){
                R.id.home ->{
                    if(fragmentInstance !is TwelveAFragment)
                        changeFragment(R.id.container, TwelveAFragment.newInstance(),TwelveAFragment::class.java.simpleName,true)
                }
                R.id.profile ->{
                    if(fragmentInstance !is TwelveBFragment)
                        changeFragment(R.id.container, TwelveBFragment.newInstance(callBackFromFragmentFun),TwelveBFragment::class.java.simpleName,false)
                }
                R.id.setting ->{
                    if(fragmentInstance !is TwelveCFragment)
                        changeFragment(R.id.container, TwelveCFragment.newInstance("參數1","參數2",callBackFromFragmentFun),TwelveCFragment::class.java.simpleName,true)
                }
                else->{
                    if(fragmentInstance !is TwelveAFragment)
                        changeFragment(R.id.container, TwelveAFragment.newInstance(),TwelveAFragment::class.java.simpleName)
                }
            }


            return@setOnItemSelectedListener true
        }

        //binding.bottomNavigationView.getMenu().findItem(R.id.home).setTitle("title")

        //binding.bottomNavigationView.setSelectedItemId(R.id.home)
        //updateBottomSelectedItemId()
    }

    //更新bottomNavigationView所選的icon
    private fun updateBottomSelectedItemId() {

        val fragmentInstance = supportFragmentManager.findFragmentById(R.id.container)

        if(fragmentInstance == null){
            Log.d(TAG, "updateBottomSelectedItemId: item id null")
        }else{
            Log.d(TAG, "updateBottomSelectedItemId: item id not null")
        }

        var itemId : Int? = null
        var _title : String? = null
        if(fragmentInstance is TwelveAFragment){
            itemId = R.id.home
            _title = "home"
        }else if(fragmentInstance is TwelveBFragment) {
            itemId = R.id.profile
            _title = "profile"
        }
        else if(fragmentInstance is TwelveCFragment){
            itemId = R.id.setting
            _title = "setting"
        }
        Log.d(TAG, "updateBottomSelectedItemId: _title ${_title}")
        if(itemId != null){
            binding.bottomNavigationView.selectedItemId = itemId
            //_title?.let {
            //    title = it
           // }
        }

    }

    override fun onBackPressed() {

        updateBottomSelectedItemId()
        //Log.d(TAG, "TwelveAFragment onBackPressed: ")
        val count = supportFragmentManager.backStackEntryCount
        Log.d(TAG, "onBackPressed: $count")
        if (count == 0 ){
            super.onBackPressed()
        }else{
            supportFragmentManager.popBackStack()
            val fragmentInstance = supportFragmentManager.findFragmentById(R.id.container)
            Log.d(TAG, "onBackPressed: ${fragmentInstance!!::class.java.simpleName}")

        }
    }

    //fragment 載入時回傳此方法
    private val callBackFromFragmentFun = object : IBottomNavigationViewListener {
        override fun onSelected() {
            updateBottomSelectedItemId()
        }
    }

    override fun updateTitle(title1: String) {
        title = title1
    }
}