package com.benidict.calendarview.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.benidict.calendarview.dummy.EventDetails
import com.benidict.model.EventUIModel

@Composable
fun EventCardView(eventUIModel: EventUIModel<EventDetails>) {
    Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(
                start = 10.dp,
                top = 10.dp,
                bottom = 10.dp
            )
        ) {
            Text(
                text = eventUIModel.startDate
            )
            Text(
                text = eventUIModel.eventDetails?.name.orEmpty()
            )
        }
    }
}