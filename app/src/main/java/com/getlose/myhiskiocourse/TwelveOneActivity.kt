package com.getlose.myhiskiocourse

import android.os.Bundle
import android.util.Log
import com.getlose.myhiskiocourse.Fragment.TwelveAFragment
import com.getlose.myhiskiocourse.Fragment.TwelveBFragment
import com.getlose.myhiskiocourse.Fragment.TwelveCFragment
import com.getlose.myhiskiocourse.databinding.ActivityTwelveOneBinding
import com.getlose.myhiskiocourse.interfaces.IBottomNavigationViewListener


class TwelveOneActivity : BaseActivity() {

    private val TAG = TwelveOneActivity::class.java.simpleName
    private lateinit var binding : ActivityTwelveOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityTwelveOneBinding.inflate(layoutInflater)

        setContentView(binding.root)

        changeFragment(R.id.container, TwelveAFragment.newInstance())

        binding.bottomNavigationView.setOnItemSelectedListener{
            Log.d(TAG, "item id => ${it.itemId}")
            //取得目前在container裡的Fragment物件
            val fragmentInstance = supportFragmentManager.findFragmentById(R.id.container)
            Log.d(TAG, "fragmentInstance => ${fragmentInstance}")
            when(it.itemId){
                R.id.home->{
                    if(fragmentInstance !is TwelveAFragment)
                        changeFragment(R.id.container, TwelveAFragment.newInstance())
                }
                R.id.profile->{
                    if(fragmentInstance !is TwelveBFragment)
                        changeFragment(R.id.container, TwelveBFragment.newInstance(callBackFromFragmentFun))
                }
                R.id.setting->{
                    if(fragmentInstance !is TwelveCFragment)
                        changeFragment(R.id.container, TwelveCFragment.newInstance("參數1","參數2",callBackFromFragmentFun))
                }
                else->{
                    if(fragmentInstance !is TwelveAFragment)
                        changeFragment(R.id.container, TwelveAFragment.newInstance())
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    //更新bottomNavigationView所選的icon
    private fun updateBottomSelectedItemId() {
        val fragmentInstance = supportFragmentManager.findFragmentById(R.id.container)
        var itemId : Int? = null
        if(fragmentInstance is TwelveAFragment)
            itemId = R.id.home
        if(fragmentInstance is TwelveBFragment)
            itemId = R.id.profile
        if(fragmentInstance is TwelveCFragment)
            itemId = R.id.setting

        if(itemId != null)
            binding.bottomNavigationView.selectedItemId = itemId
    }

    //fragment 載入時回傳此方法
    private val callBackFromFragmentFun = object : IBottomNavigationViewListener {
        override fun onSelected() {
            updateBottomSelectedItemId()
        }
    }
}