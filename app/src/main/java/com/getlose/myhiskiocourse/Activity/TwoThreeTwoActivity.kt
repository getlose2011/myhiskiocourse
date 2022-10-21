package com.getlose.myhiskiocourse.Activity

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.getlose.myhiskiocourse.Fragment.TwelveAFragment
import com.getlose.myhiskiocourse.R
import com.getlose.myhiskiocourse.databinding.ActivityTwoThreeTwoBinding

class TwoThreeTwoActivity : BaseActivity() {

    val TAG = TwoThreeTwoActivity::class.java.simpleName
    private lateinit var binding: ActivityTwoThreeTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTwoThreeTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ActionBarDrawerToogle 將drawerLayoutBar整合
        val toggle = ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar, R.string.drawer_open,R.string.drawer_close)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        changeFragment(R.id.container, TwelveAFragment.newInstance())

    }
}