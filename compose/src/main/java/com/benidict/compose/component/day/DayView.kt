package com.benidict.compose.component.day

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.benidict.compose.utilities.getDayToday
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayView(day: String, date: String, shape: Shape, onSelectedDate: ((LocalDate) -> Unit)? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .clickable {
                onSelectedDate?.invoke(LocalDate.parse(date))
            }
    ) {
        if (date == getDayToday().toString()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(30.dp)
                    .clip(shape)
                    .background(Color.Blue)
            ) {
                Text(
                    color = Color.White,
                    text = day
                )
            }
        } else {
            Text(
                text = day
            )
        }
    }
}