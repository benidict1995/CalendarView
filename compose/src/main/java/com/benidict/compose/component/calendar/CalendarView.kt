package com.benidict.compose.component.calendar

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benidict.compose.component.control.CalendarController
import com.benidict.compose.component.day.DayHeader
import com.benidict.compose.component.day.DayView
import com.benidict.compose.shape.CircleEventLegendsView
import com.benidict.compose.utilities.daysInMonth
import com.benidict.compose.utilities.loadYears
import com.benidict.compose.utilities.removeTheEmptyFirstWeek
import com.benidict.model.CalendarUIModel
import com.benidict.model.EventUIModel
import java.time.LocalDate

@SuppressLint("ModifierParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun <T : Any> MonthCalendarView(
    modifier: Modifier,
    selectedYear: String,
    selectedDate: LocalDate,
    years: List<String>,
    events: List<EventUIModel<T>>,
    onForward: (() -> Unit)? = null,
    onBackward: (() -> Unit)? = null,
    onSelectedDate: ((LocalDate) -> Unit)? = null,
    onYearChanged: (Int) -> Unit
) {
    val days: ArrayList<CalendarUIModel<T>> = daysInMonth(selectedDate, events)
    val filteredDaysInMonth = removeTheEmptyFirstWeek(days)
    Column {
        CalendarController(
            years = years,
            selectedYear = selectedYear,
            selectedDate = selectedDate,
            onBackward = {
                onBackward?.invoke()
            }, onForward = {
                onForward?.invoke()
            }, onYearChanged = {
                onYearChanged(it)
            })
        Spacer(modifier = modifier.height(20.dp))
        DayHeader()
        Spacer(modifier = modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(7)
        ) {
            items(filteredDaysInMonth.size) { day ->
                Box(
                    modifier = modifier
                        .weight(1.0f, true)
                        .then(
                            modifier.wrapContentSize(Alignment.Center)
                        )
                ) {
                    val eventList = filteredDaysInMonth[day].events
                    Column {
                        Spacer(
                            modifier = modifier
                                .height(10.dp)
                                .width(5.dp)
                        )
                        DayView(
                            filteredDaysInMonth[day].day.orEmpty(),
                            filteredDaysInMonth[day].date.orEmpty(),
                            CircleShape,
                            onSelectedDate = {
                                onSelectedDate?.invoke(it)
                            }
                        )
                        Spacer(Modifier.height(4.dp))
                        if (eventList?.isNotEmpty() == true) {
                            CircleEventLegendsView()
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MonthCalendarViewPreview() {
    val events: List<EventUIModel<Any>> = emptyList()
    MaterialTheme {
        MonthCalendarView(
            modifier = Modifier,
            selectedYear = "",
            selectedDate = LocalDate.now(),
            years = emptyList(),
            events = events
        ) {

        }
    }
}