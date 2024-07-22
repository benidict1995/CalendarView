package com.benidict.calendarview.component.list

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.benidict.calendarview.component.card.EventCardView
import com.benidict.calendarview.dummy.EventDetails
import com.benidict.model.EventUIModel

@Composable
fun EventListView(events: List<EventUIModel<EventDetails>>) {
    LazyColumn {
        items(items = events, itemContent = { event ->
            EventCardView(event)
        })
    }
}