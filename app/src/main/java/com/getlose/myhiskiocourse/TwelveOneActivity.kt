package com.getlose.myhiskiocourse

import android.os.Bundle
import android.util.Log
import com.getlose.myhiskiocourse.Fragment.TwelveAFragment
import com.getlose.myhiskiocourse.Fragment.TwelveBFragment
import com.getlose.myhiskiocourse.Fragment.TwelveCFragment
import com.getlose.myhiskiocourse.databinding.ActivityTwelveOneBinding

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
                        changeFragment(R.id.container, TwelveBFragment.newInstance())
                }
                R.id.setting->{
                    if(fragmentInstance !is TwelveCFragment)
                        changeFragment(R.id.container, TwelveCFragment.newInstance("參數1","參數2"))
                }
                else->{
                    if(fragmentInstance !is TwelveAFragment)
                        changeFragment(R.id.container, TwelveAFragment.newInstance())
                }
            }
            return@setOnItemSelectedListener true
        }
    }


}