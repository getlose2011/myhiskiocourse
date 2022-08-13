package com.getlose.myhiskiocourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.getlose.myhiskiocourse.Data.Database.EmployeeDatabase
import com.getlose.myhiskiocourse.Data.Entity.Employee
import com.getlose.myhiskiocourse.databinding.ActivityFifTeenThreeAactivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

///https://waynestalk.com/android-room-sqlcipher/
////刪除 database table 語法 => deleteDatabase("employee.db");
//Java – Android Room – error: Cannot figure out how to save this field into database =>
//https://itecnote.com/tecnote/java-android-room-error-cannot-figure-out-how-to-save-this-field-into-database/
class FifTeenThreeAActivity : AppCompatActivity(),CoroutineScope {

    private lateinit var job : Job
    private val TAG = FifTeenThreeAActivity::class.java.simpleName
    private lateinit var binding: ActivityFifTeenThreeAactivityBinding
    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main//跑在 Main Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = Job()

        binding = ActivityFifTeenThreeAactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectDb.setOnClickListener {
            //產生協程，因為設定協程為Dispatchers.Main，所以可以直接存取ui
            launch {
                //readDB()
                val db = EmployeeDatabase.getInstance(this@FifTeenThreeAActivity)?.employeeDao()
                val list = db?.findAll()
                list?.forEach {
                    Log.d(TAG, "selectDB: ${it.name}, ${it.createdAt}, ${it.id}, ${it.type}")
                    //db.delete(it)
                    binding.txtShowSelect.text = "selectDB: ${it.name}, ${it.createdAt}, ${it.id}, ${it.type}"
                }
            }
        }

        writeDb()

    }

    //存取DB不能在UI執行緒做
    private fun writeDb() {
        //coroutine
        CoroutineScope(Dispatchers.IO).launch {
            EmployeeDatabase.getInstance(this@FifTeenThreeAActivity)?.employeeDao()?.insert(Employee("allen1", Employee.Type.PART_TIME))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //取消 Coroutines
        job.cancel()
    }
}