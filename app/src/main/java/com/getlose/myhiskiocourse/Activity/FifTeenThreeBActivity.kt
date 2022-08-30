package com.getlose.myhiskiocourse.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.getlose.myhiskiocourse.databinding.ActivityFifTeenThreeBactivityBinding

class FifTeenThreeBActivity : AppCompatActivity() {

    private val TAG = FifTeenThreeBActivity::class.java.simpleName
    private lateinit var binding: ActivityFifTeenThreeBactivityBinding
    private val RC_CONTACTS: Int = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFifTeenThreeBactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //取得權限
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)

        //判斷是否給予於權限
        if(permission != PackageManager.PERMISSION_GRANTED){
            //跳出權限視窗
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),
                RC_CONTACTS)
        }else{
            readContact()
        }
    }

    @SuppressLint("Range")
    private fun readContact() {
        //取得contentProvider物件
        val cursor: Cursor? =
            contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (cursor != null) {

            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                Log.d(TAG, "cursor: ${name}")
            }
        }
    }

    //跳出權限視窗後，不論點是或否都會來此方法
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //判斷requestCode
        if(requestCode == RC_CONTACTS){
            //給予的權限
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                readContact()
            }
        }
    }
}