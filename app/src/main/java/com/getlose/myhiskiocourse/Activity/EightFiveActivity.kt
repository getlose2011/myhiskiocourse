package com.getlose.myhiskiocourse.Activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.getlose.myhiskiocourse.databinding.ActivityEightFiveBinding
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*



class EightFiveActivity : BaseActivity() {
    private lateinit var binding:ActivityEightFiveBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEightFiveBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.selectDateButton.setOnClickListener {
            selectDate()
        }

        binding.selectTimeButton.setOnClickListener {
            selectTime()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun selectDate() {

        //今天
        val today: Calendar = Calendar.getInstance()

        //隔天
        val defaultDate: Calendar = Calendar.getInstance().apply { add(Calendar.DATE, 1) }

        //2個月後
        val maxDate: Calendar = Calendar.getInstance().apply {
            add(Calendar.MONTH, 2)
        }

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
                binding.dateTextView.text =
                    LocalDate.of(year, monthOfYear + 1, dayOfMonth).format(formatter)
            },
            defaultDate.get(Calendar.YEAR),
            defaultDate.get(Calendar.MONTH),
            defaultDate.get(Calendar.DAY_OF_MONTH)
        )

        //設定最小日期
        datePickerDialog.datePicker.minDate = today.timeInMillis

        //設定最大日期
        datePickerDialog.datePicker.maxDate = maxDate.timeInMillis

        datePickerDialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun selectTime() {
        val today: Calendar = Calendar.getInstance()

        val timePickerDialog = TimePickerDialog(
            this, { view, hourOfDay, minute ->
                val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                binding.timeTextView.text = LocalTime.of(hourOfDay, minute).format(formatter)
            }, today.get(Calendar.HOUR_OF_DAY), today.get(Calendar.MINUTE), true
        )

        timePickerDialog.show()
    }

}