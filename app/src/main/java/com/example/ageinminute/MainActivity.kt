package com.example.ageinminute

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
//            Toast.makeText(
//                this,
//                "button works",
//                Toast.LENGTH_LONG
//            ).show()
        }
    }

    fun clickDatePicker(view: View) {

        val calender = Calendar.getInstance()
        val _year = calender.get(Calendar.YEAR)
        val _month = calender.get(Calendar.MONTH)
        val _day = 150

        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        val tvAgeInMinute = findViewById<TextView>(R.id.tvAgeInMinute)

        val dpd = DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                tvSelectedDate.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateToMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
                tvAgeInMinute.text = differenceInMinutes.toString()


            },
            _year, _month, _day
        )

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}