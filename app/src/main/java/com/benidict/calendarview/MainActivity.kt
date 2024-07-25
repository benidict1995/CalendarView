package com.benidict.calendarview

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.calendarview.dummy.events
import com.benidict.calendarview.ui.theme.CalendarviewTheme
import com.benidict.compose.component.calendar.MonthCalendarView
import com.benidict.compose.utilities.nextMonth
import com.benidict.compose.utilities.previousMonth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import androidx.hilt.navigation.compose.hiltViewModel
import com.benidict.calendarview.component.empty.EmptyEventListView
import com.benidict.calendarview.component.list.EventListView
import com.benidict.calendarview.dummy.EventDetails
import com.benidict.calendarview.ui.theme.ImageBackgroundColor
import com.benidict.calendarview.ui.theme.PurpleGrey80
import com.benidict.compose.utilities.loadYears
import com.benidict.model.EventUIModel

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

                val sheetState = rememberModalBottomSheetState(
                    skipPartiallyExpanded = true
                )
                var bottomSheetState by remember { mutableStateOf(false) }

                var selectedDate by remember { mutableStateOf(LocalDate.now()) }

                var selectedYear by remember { mutableStateOf(loadYears()[0]) }

                LaunchedEffect(Unit) {
                    viewModel.loadCalendarEvent(selectedDate)
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            },
                            title = {
                                Text(
                                    fontWeight = FontWeight.Black,
                                    fontSize = 20.sp,
                                    text = ""
                                )
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                bottomSheetState = true
                            }
                        ) {
                            Icon(
                                painterResource(com.benidict.compose.R.drawable.ic_calendar),
                                contentDescription = "",
                                tint = Color.Black
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        val events = viewModel._events.collectAsState()
                        val monthEvents = viewModel._monthEvents.collectAsState()
                        val years = viewModel._years.collectAsState()
                        EventListView(selectedDate, events.value)

                        if (bottomSheetState) {
                            ModalBottomSheet(
                                modifier = Modifier.fillMaxHeight(0.7f),
                                sheetState = sheetState,
                                onDismissRequest = {
                                    bottomSheetState = false
                                },
                                shape = RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 10.dp
                                ),
                            ) {
                                CalendarModal(
                                    monthEvents = monthEvents.value,
                                    years = years.value,
                                    selectedYear = selectedYear,
                                    selectedDate,
                                    sheetState, showBottomSheet = {
                                        bottomSheetState = it
                                    }, onSelectedDate = {
                                        selectedDate = it
                                        viewModel.loadCalendarEvent(it)
                                    },
                                    onSelectedYear = {
                                        selectedYear = it.toString()
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
    fun CalendarModal(
        monthEvents: List<EventUIModel<EventDetails>>,
        years: List<String>,
        selectedYear: String,
        selectedDateState: LocalDate,
        sheetState: SheetState,
        showBottomSheet: (Boolean) -> Unit,
        onSelectedDate: (LocalDate) -> Unit,
        onSelectedYear: (Int) -> Unit
    ) {
        val scope = rememberCoroutineScope()
        Column(modifier = Modifier.fillMaxSize()) {
            Row {
                Spacer(
                    modifier = Modifier.width(10.dp)
                )
                IconButton(
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (sheetState.isVisible.not()) {
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
            MonthCalendarView(Modifier,
                selectedYear = selectedYear,
                selectedDate = selectedDateState,
                years = years,
                events = monthEvents,
                onBackward = {
                    previousMonth(selectedDateState) {
                        onSelectedDate(it)
                    }
                }, onForward = {
                    nextMonth(selectedDateState) {
                        onSelectedDate(it)
                    }
                }, onYearChanged = {
                    onSelectedYear(it)
                    onSelectedDate(selectedDateState.withYear(it))
                }, onSelectedDate = {
                    onSelectedDate(it)
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (sheetState.isVisible.not()) {
                            showBottomSheet(false)
                        }
                    }
                })
        }
    }
}