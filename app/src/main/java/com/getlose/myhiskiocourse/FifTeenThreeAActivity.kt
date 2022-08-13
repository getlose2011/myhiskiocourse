package com.getlose.myhiskiocourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.getlose.myhiskiocourse.Data.Database.EmployeeDatabase
import com.getlose.myhiskiocourse.Data.Entity.Employee
import com.getlose.myhiskiocourse.databinding.ActivityFifTeenThreeAactivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

///https://waynestalk.com/android-room-sqlcipher/
////刪除 database table 語法 => deleteDatabase("employee.db");
//Java – Android Room – error: Cannot figure out how to save this field into database =>
//https://itecnote.com/tecnote/java-android-room-error-cannot-figure-out-how-to-save-this-field-into-database/
class FifTeenThreeAActivity : AppCompatActivity() {

    private val TAG = FifTeenThreeAActivity::class.java.simpleName
    private lateinit var binding: ActivityFifTeenThreeAactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFifTeenThreeAactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectDb.setOnClickListener {
            readDB()
        }

        writeDb()

    }

    //存取DB不能在UI執行緒做
    private fun readDB() {
        //coroutine
        CoroutineScope(Dispatchers.IO).launch {
            val db = EmployeeDatabase.getInstance(this@FifTeenThreeAActivity)?.employeeDao()
            val list = db?.findAll()
            list?.forEach {
                Log.d(TAG, "selectDB: ${it.name}, ${it.createdAt}, ${it.id}, ${it.type}")
                //db.delete(it)

            }
        }
        //沒有coroutin寫法
        //        Thread{
        //            EmployeeDatabase.getInstance(this)?.employeeDao()?.findAll()
        //                ?.forEach {
        //                    Log.d(TAG, "selectDB: ${it.name}")
        //                }
        //        }.start()
    }

    //存取DB不能在UI執行緒做
    private fun writeDb() {
        //coroutine
        CoroutineScope(Dispatchers.IO).launch {
            EmployeeDatabase.getInstance(this@FifTeenThreeAActivity)?.employeeDao()?.insert(Employee("allen1", Employee.Type.PART_TIME))
        }
        //沒有coroutin寫法
        //        Thread{
        //            EmployeeDatabase.getInstance(this)?.employeeDao()?.insert(Employee("allen", Employee.Type.FULL_TIME))
        //        }.start()
    }
}