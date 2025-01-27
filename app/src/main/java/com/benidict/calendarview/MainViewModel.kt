package com.benidict.calendarview

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.calendarview.dummy.EventDetails
import com.benidict.calendarview.dummy.events
import com.benidict.compose.utilities.loadYears
import com.benidict.model.EventUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    val _events: MutableStateFlow<List<EventUIModel<EventDetails>>> = MutableStateFlow(emptyList())
    val _years: MutableStateFlow<List<String>> = MutableStateFlow(loadYears())
    val _monthEvents: MutableStateFlow<List<EventUIModel<EventDetails>>> = MutableStateFlow(
        emptyList()
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadCalendarEvent(selectedDate: LocalDate) {
        viewModelScope.launch {
            val invoke = events.filter {
                (selectedDate.toString()   >= (it.startDate)) &&
                        (selectedDate.toString() <= (it.endDate))
            }
            _events.value = invoke

            val invokeMontEvent = events.filter {
                selectedDate.month == LocalDate.parse(it.date).month
            }
            _monthEvents.value = invokeMontEvent
        }
    }
}