package com.benidict.compose.utilities

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

const val DATE_FORMAT_8 = "EEEE, MMMM "

fun getCustomDateString(dateFormat: String): String {
    val date: Date = java.sql.Date.valueOf(dateFormat)
    var tmp = SimpleDateFormat("d", Locale.getDefault())
    var str = tmp.format(date)
    str = if (date.date in 11..13) {
        str + "th "
    } else {
        if (str.endsWith("1")) {
            str + "st "
        } else if (str.endsWith("2")) {
            str + "nd "
        } else if (str.endsWith("3")) {
            str + "rd "
        } else {
            str + "th "
        }
    }
    tmp = SimpleDateFormat(DATE_FORMAT_8, Locale.getDefault())
    return tmp.format(date) + str
}


@RequiresApi(Build.VERSION_CODES.O)
fun monthYearFromDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    return date.format(formatter)
}

fun loadYears(): List<String> {
    val years: MutableList<String> = ArrayList()
    val yearsInt: MutableList<Int> = ArrayList()
    val thisYear: Int = Calendar.getInstance().get(Calendar.YEAR)
    for (i in 1900..thisYear) {
        yearsInt.add(i)
    }
    yearsInt.sortedDescending().forEach { i ->
        years.add(i.toString())
    }

    return years
}