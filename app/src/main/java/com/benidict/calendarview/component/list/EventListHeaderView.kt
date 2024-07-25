package com.benidict.calendarview.component.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.calendarview.R
import com.benidict.compose.utilities.getCustomDateString
import java.time.LocalDate

@Composable
fun EventListHeaderView(selectedDate: LocalDate) {
    Spacer(
        modifier = Modifier.height(20.dp)
    )
    Text(
        fontSize = 34.sp,
        fontWeight = FontWeight.ExtraBold,
        text = stringResource(R.string.all_events)
    )
    Text(
        text = getCustomDateString(selectedDate.toString()),
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal
    )
    Spacer(
        modifier = Modifier.height(20.dp)
    )
}