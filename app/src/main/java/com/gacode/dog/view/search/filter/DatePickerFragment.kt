package com.gacode.dog.view.search.filter

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker

import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class DatePickerFragment(date: TextInputEditText) : DialogFragment(), DatePickerDialog.OnDateSetListener {
    val dateElement = date

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        dateElement.setText(year.toString()+"-"+month+"-"+day)
    }
}