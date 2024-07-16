package com.benidict.compose.component.control

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.benidict.compose.R
import com.benidict.compose.utilities.monthYearFromDate
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarController(
    selectedDate: LocalDate,
    onForward: (() -> Unit)? = null,
    onBackward: (() -> Unit)? = null
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (rowYear, rowMonthController) = createRefs()

        Row(
            modifier = Modifier.constrainAs(rowYear) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 10.dp)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = monthYearFromDate(selectedDate),
                fontWeight = FontWeight.Bold
            )
            Spacer(
                Modifier.width(5.dp)
            )
            Image(
                painterResource(R.drawable.ic_arrow_forward),
                colorFilter = ColorFilter.tint(Color.DarkGray),
                contentDescription = "",
            )
        }

        Row(
            modifier = Modifier.constrainAs(rowMonthController) {
                top.linkTo(parent.top)
                end.linkTo(parent.end, margin = 10.dp)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(R.drawable.ic_arrow_backward),
                colorFilter = ColorFilter.tint(Color.DarkGray),
                contentDescription = "",
                modifier = Modifier.clickable {
                    onBackward?.invoke()
                }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painterResource(R.drawable.ic_arrow_forward),
                colorFilter = ColorFilter.tint(Color.DarkGray),
                contentDescription = "",
                modifier = Modifier.clickable {
                    onForward?.invoke()
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun CalendarControllerPreview() {
    MaterialTheme {
        CalendarController(LocalDate.now())
    }
}