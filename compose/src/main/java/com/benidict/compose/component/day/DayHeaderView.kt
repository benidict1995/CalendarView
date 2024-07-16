package com.benidict.compose.component.day

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.benidict.compose.utilities.days

@Composable
fun DayHeader() {
    Row(
        Modifier.fillMaxWidth()
    ) {
        days.map {
            Box(
                modifier = Modifier.weight(1.0f, true).then(
                    Modifier.wrapContentSize(Alignment.Center)
                )
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
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