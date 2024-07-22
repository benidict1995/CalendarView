package com.benidict.calendarview

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benidict.calendarview.dummy.EventDetails
import com.benidict.calendarview.dummy.events
import com.benidict.model.EventUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    val _events: MutableStateFlow<List<EventUIModel<EventDetails>>> = MutableStateFlow(emptyList())

    fun loadCalendarEvent(selectedDate: String) {
        Log.d("makerChecker", "selectedDate:$selectedDate")
        viewModelScope.launch {
            val invoke = events.filter {
                it.date == selectedDate
            }
            _events.value = invoke
        }
    }
}