package com.benidict.compose.utilities

import com.benidict.model.CalendarUIModel
import java.time.LocalDate
import java.time.YearMonth

fun <T> daysInMonth(date: LocalDate, events: List<T>?): ArrayList<CalendarUIModel<T>> {
    val daysInMonthArray = ArrayList<CalendarUIModel<T>>()
    val months = YearMonth.from(date)
    val days = months.lengthOfMonth()
    val firstDayOfMonth: LocalDate = date.withDayOfMonth(1)
    val dayOfWeek = firstDayOfMonth.dayOfWeek.value
    val month = parseCalendarMonthFormat(date.toString())
    for (i in 1.. 42) {
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
}

fun parseCalendarMonthFormat(calendarDate: String): String {
    val split = calendarDate.split("-")

    return "${split[0]}-${split[1]}"
}