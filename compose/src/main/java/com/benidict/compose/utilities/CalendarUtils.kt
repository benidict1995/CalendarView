package com.benidict.compose.utilities

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.benidict.model.CalendarUIModel
import java.time.LocalDate
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
fun <T: Any> daysInMonth(selectedDate: LocalDate, events: List<T>?): ArrayList<CalendarUIModel<T>> {
    val daysInMonthArray = ArrayList<CalendarUIModel<T>>()
    val months = YearMonth.from(selectedDate)
    val days = months.lengthOfMonth()
    val firstDayOfMonth: LocalDate = selectedDate.withDayOfMonth(1)
    val dayOfWeek = firstDayOfMonth.dayOfWeek.value
    val month = parseCalendarMonthFormat(selectedDate.toString())
    for (i in 1..42) {
        if (i <= dayOfWeek || i > (days + dayOfWeek)) {
            daysInMonthArray.add(
                CalendarUIModel(
                    id = i,
                    day = "",
                    date = "",
                    events = emptyList()
                )
            )
        } else {
            daysInMonthArray.add(
                CalendarUIModel(
                    id = i,
                    day = (i - dayOfWeek).toString(),
                    date = YearMonth.parse(month).atDay((i - dayOfWeek)).toString(),
                    events = events
                )
            )
        }
    }
    return daysInMonthArray
}

fun <T: Any> removeTheEmptyFirstWeek(daysInMonth: ArrayList<CalendarUIModel<T>>): ArrayList<CalendarUIModel<T>> {
    var daysEmptyCounter = 0
    daysInMonth.forEachIndexed { index, calendarUIModel ->
        when (index) {
            0, 1, 2, 3, 4, 5, 6 -> if (calendarUIModel.day?.isEmpty() == true) daysEmptyCounter += 1
            else -> {}
        }
    }

    return if (daysEmptyCounter == 7) {
        setNewSetOfDate(daysInMonth)
    } else {
        daysInMonth
    }
}

private fun <T: Any> setNewSetOfDate(daysInMonth: ArrayList<CalendarUIModel<T>>): ArrayList<CalendarUIModel<T>> {
    val newDaysInMonth = ArrayList<CalendarUIModel<T>>()
    daysInMonth.forEachIndexed { index, calendarUIModel ->
        if (index >= 7) {
            newDaysInMonth.add(calendarUIModel)
        }
    }

    return newDaysInMonth
}

fun parseCalendarMonthFormat(calendarDate: String): String {
    val split = calendarDate.split("-")

    return "${split[0]}-${split[1]}"
}

fun getDayToday(): LocalDate = LocalDate.now()