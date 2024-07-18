package com.benidict.compose.utilities

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
fun monthYearFromDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    return date.format(formatter)
}

fun loadYears(): List<Int> {
    val years: MutableList<Int> = ArrayList()
    val thisYear: Int = Calendar.getInstance().get(Calendar.YEAR)
    for (i in 1900..thisYear) {
        years.add(i)
    }
    return years
}