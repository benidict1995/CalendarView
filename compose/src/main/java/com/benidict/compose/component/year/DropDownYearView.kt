package com.benidict.compose.component.year

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benidict.compose.utilities.loadYears

@Composable
fun DropDownYearView(
    modifier: Modifier
) {
    var stroke by remember { mutableStateOf(1) }
    var expand by remember { mutableStateOf(false) }

    DropdownMenu(
        expanded = expand,
        onDismissRequest = {
            expand = false
            stroke = if (expand) 2 else 1
        },
        modifier = Modifier
            .background(White)
            .padding(2.dp)
            .fillMaxWidth(.4f)
    ) {
        Column {
            val years = loadYears()
            LazyVerticalGrid(
                columns = GridCells.Fixed(7)
            ) {
                items(years.size) { year ->
                    Box(
                        modifier = modifier
                            .weight(1.0f, true)
                            .then(
                                modifier.wrapContentSize(Alignment.Center)
                            )
                    ) {
                        Text(
                            text = year.toString()
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DropDownYearViewPreview(
    modifier: Modifier
) {
    MaterialTheme {
        DropDownYearView(Modifier)
    }
}