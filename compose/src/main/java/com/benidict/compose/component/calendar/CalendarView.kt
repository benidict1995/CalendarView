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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benidict.compose.component.day.DayHeader
import com.benidict.compose.component.day.DayView
import com.benidict.compose.utilities.daysInMonth
import com.benidict.compose.utilities.removeTheEmptyFirstWeek
import com.benidict.model.CalendarUIModel
import java.time.LocalDate

@SuppressLint("ModifierParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun <T : Any> MonthCalendarView(selectedDate: LocalDate, events: List<T>) {
    val days: ArrayList<CalendarUIModel<T>> = daysInMonth(selectedDate, events)
    val filteredDaysInMonth = removeTheEmptyFirstWeek(days)
    Column {
        DayHeader()
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid (
            columns = GridCells.Fixed(7)
        ) {
            items(filteredDaysInMonth.size) { day ->
                Box(
                    modifier = Modifier.weight(1.0f, true)
                        .then(
                        Modifier.wrapContentSize(Alignment.Center)
                    )
                ) {
                    Column {
                        Spacer(
                            modifier = Modifier.height(10.dp).width(5.dp)
                        )
                        DayView(filteredDaysInMonth[day].day.orEmpty(), filteredDaysInMonth[day].date.orEmpty(), CircleShape)
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
    MaterialTheme {
        MonthCalendarView(LocalDate.now(), emptyList())
    }
}