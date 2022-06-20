package com.getlose.myhiskiocourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

open class BaseActivity : AppCompatActivity() {

    protected val INTENT_KEY_NAME = "INTENT_KEY_NAME"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

	//¤Á´«Fragment
    protected fun changeFragment(containerId:Int, fragment: Fragment){
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        transaction.commit()
    }

}