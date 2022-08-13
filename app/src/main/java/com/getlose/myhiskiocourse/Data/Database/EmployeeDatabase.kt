package com.getlose.myhiskiocourse.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.getlose.myhiskiocourse.Data.Dao.EmployeeDao
import com.getlose.myhiskiocourse.Data.Entity.Employee
import com.getlose.myhiskiocourse.TypeConverter.Converters

@Database(entities = [Employee::class], version = 1)
@TypeConverters(Converters::class)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object{

        private var instance : EmployeeDatabase? = null

        fun getInstance(context: Context):EmployeeDatabase?{

            if(instance == null){
                instance = Room
                    .databaseBuilder(context,EmployeeDatabase::class.java,"employee.db")
                    .build()
            }

            return instance
        }

    }
}