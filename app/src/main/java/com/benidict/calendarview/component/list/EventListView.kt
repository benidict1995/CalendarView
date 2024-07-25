package com.benidict.calendarview.component.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benidict.calendarview.component.card.EventCardView
import com.benidict.calendarview.component.empty.EmptyEventListView
import com.benidict.calendarview.dummy.EventDetails
import com.benidict.model.EventUIModel
import java.time.LocalDate

@Composable
fun EventListView(selectedDate: LocalDate, events: List<EventUIModel<EventDetails>>) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        EventListHeaderView(selectedDate)
        if(events.isEmpty()) {
            EmptyEventListView()
        }
        LazyColumn {
            items(items = events, itemContent = { event ->
                EventCardView(event)
            })
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun EventListViewPreview() {
    MaterialTheme {
        EventListView(selectedDate = LocalDate.now(), emptyList())
    }
}