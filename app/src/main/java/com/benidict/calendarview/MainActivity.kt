package com.benidict.calendarview

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.benidict.calendarview.dummy.events
import com.benidict.calendarview.ui.theme.CalendarviewTheme
import com.benidict.compose.component.calendar.MonthCalendarView
import com.benidict.compose.utilities.nextMonth
import com.benidict.compose.utilities.previousMonth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import androidx.hilt.navigation.compose.hiltViewModel
import com.benidict.calendarview.component.list.EventListView

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalendarviewTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
                var showBottomSheet by remember { mutableStateOf(false) }
                val selectedDate by remember { mutableStateOf(LocalDate.now()) }
                LaunchedEffect(Unit) {
                    viewModel.loadCalendarEvent(selectedDate.toString())
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            text = { },
                            icon = {
                                Image(
                                    painterResource(com.benidict.compose.R.drawable.ic_calendar), ""
                                )
                            },
                            onClick = {
                                showBottomSheet = true
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        val events = viewModel._events.collectAsState()
                        EventListView(events.value)

                        if (showBottomSheet) {
                            ModalBottomSheet(
                                modifier = Modifier.fillMaxHeight(0.8f),
                                sheetState = sheetState,
                                onDismissRequest = {
                                    showBottomSheet = false
                                },
                                shape = RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 10.dp
                                ),
                            ) {
                                CalendarModal(sheetState, showBottomSheet = {
                                    showBottomSheet = it
                                }, onSelectedDate = {
                                    viewModel.loadCalendarEvent(it)
                                })
                            }
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun CalendarModal(sheetState: SheetState, showBottomSheet: (Boolean) -> Unit, onSelectedDate: (String) -> Unit) {
        val scope = rememberCoroutineScope()
        var selectedDateState by remember { mutableStateOf(LocalDate.now()) }
        Column(modifier = Modifier.fillMaxSize()) {
            Row {
                Spacer(
                    modifier = Modifier.width(10.dp)
                )
                IconButton(
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet(false)
                            }
                        }
                    },
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                ) {
                    Icon(Icons.Filled.Close, contentDescription = "", tint = Color.Black)
                }
            }
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            MonthCalendarView(Modifier, selectedDateState, events,
                onBackward = {
                    previousMonth(selectedDateState) {
                        selectedDateState = it
                    }
                }, onForward = {
                    nextMonth(selectedDateState) {
                        selectedDateState = it
                    }
                }, onYearChanged = {
                    selectedDateState = selectedDateState.withYear(it)
                }, onSelectedDate = {
                    onSelectedDate(it)
                    showBottomSheet(false)
                })
        }
    }
}