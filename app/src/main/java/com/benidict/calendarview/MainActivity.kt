package com.benidict.calendarview

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benidict.calendarview.dummy.events
import com.benidict.calendarview.ui.theme.CalendarviewTheme
import com.benidict.compose.component.calendar.MonthCalendarView
import com.benidict.compose.utilities.nextMonth
import com.benidict.compose.utilities.previousMonth
import java.time.LocalDate
import java.util.Calendar

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalendarviewTheme {
                var selectedDateState by remember { mutableStateOf(LocalDate.now()) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MonthCalendarView(Modifier, selectedDateState, events,
                            onBackward = {
                                previousMonth(selectedDateState) {
                                    selectedDateState = it
                                }
                            }, onForward = {
                                nextMonth(selectedDateState) {
                                    selectedDateState = it
                                }
                            })
                    }
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        CalendarviewTheme {
            MonthCalendarView(Modifier, LocalDate.now(), events)
        }
    }
}