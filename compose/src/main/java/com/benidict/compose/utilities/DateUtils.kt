package com.benidict.compose.utilities

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun monthYearFromDate(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    return date.format(formatter)
}