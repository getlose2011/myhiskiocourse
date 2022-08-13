package com.getlose.myhiskiocourse.Data.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//https://waynestalk.com/android-room-sqlcipher/

/*@Entity(tableName = "employees")
data class Employee(
    val name: String,
    val type: Type,

    val createdAt: Date,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
){
    enum class Type {
        FULL_TIME, PART_TIME,
    }
}*/

@Entity(tableName = "employees")
data class Employee(
    val name: String,
    val type: Type,
    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
) {
    enum class Type {
        FULL_TIME, PART_TIME,
    }
}
