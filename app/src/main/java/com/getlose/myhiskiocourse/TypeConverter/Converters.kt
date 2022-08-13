package com.getlose.myhiskiocourse.TypeConverter

import androidx.room.TypeConverter
import com.getlose.myhiskiocourse.Data.Entity.Employee
import java.util.*

class Converters {
    @TypeConverter
    fun fromDate(value: Date) = value.time

    @TypeConverter
    fun toDate(value: Long) = Date(value)

    @TypeConverter
    fun fromType(value: Employee.Type) = value.name

    @TypeConverter
    fun toType(value: String) = Employee.Type.valueOf(value)
}