package com.gacode.dog.view.search.filter

import android.app.Dialog
import android.app.TimePickerDialog

import android.os.Bundle
import android.text.format.DateFormat

import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_filter.*
import java.util.*

class TimePickerFragment(from_timeElement: TextInputEditText) : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    val from_timeElement = from_timeElement

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }



    override fun onTimeSet(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {

        from_timeElement.setText(hourOfDay.toString() + ":" + minute)
    }
}