package com.benidict.calendarview.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.calendarview.R
import com.benidict.calendarview.dummy.EventDetails
import com.benidict.calendarview.ui.theme.ImageBackgroundColor
import com.benidict.model.EventUIModel

@Composable
fun EventCardView(eventUIModel: EventUIModel<EventDetails>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp,
                    top = 10.dp,
                    bottom = 10.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ImageBackgroundColor),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = com.benidict.compose.R.drawable.ic_calendar),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                )
            }

            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = "${eventUIModel.startTime} - ${eventUIModel.endTime}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = eventUIModel.eventDetails?.name.orEmpty(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}