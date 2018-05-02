package com.example.hanad.greenflagassignment1

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment

import java.util.Calendar

/**
 * Created by hanad on 04/02/2018.
 */

class DatePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_WEEK)

        return DatePickerDialog(activity, activity as DatePickerDialog.OnDateSetListener, year, month, day)


    }
}
