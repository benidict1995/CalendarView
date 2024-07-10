package com.benidict.compose.component.day

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DayHeader() {
    val days = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
    Row {
       days.map {
           Box(
               modifier = Modifier.weight(1.0f, true).background(Color.Blue).then(
                   Modifier.wrapContentSize(Alignment.Center)
               )
           ) {
               Text(
                   text = it
               )
           }
       }
    }
}

@Preview(showBackground = true)
@Composable
fun DayHeaderPreview() {
    MaterialTheme {
        DayHeader()
    }
}