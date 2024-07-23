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