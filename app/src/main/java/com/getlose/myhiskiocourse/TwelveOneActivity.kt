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
            when(it.itemId){
                R.id.home->{
                    changeFragment(R.id.container, TwelveAFragment.newInstance())
                }
                R.id.profile->{
                    changeFragment(R.id.container, TwelveBFragment.newInstance())
                }
                R.id.setting->{
                    changeFragment(R.id.container, TwelveCFragment.newInstance("參數1","參數2"))
                }
                else->{
                    changeFragment(R.id.container, TwelveAFragment.newInstance())
                }
            }
            return@setOnItemSelectedListener true
        }
    }


}