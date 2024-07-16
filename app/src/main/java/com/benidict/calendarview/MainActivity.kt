package com.benidict.calendarview

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benidict.calendarview.ui.theme.CalendarviewTheme
import com.benidict.compose.component.calendar.MonthCalendarView
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalendarviewTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    MonthCalendarView(LocalDate.now(), emptyList(), onForward = {

    }, onBackward = {

        })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalendarviewTheme {
        Greeting("Android")
    }
}