package com.benidict.compose.component.year

import android.util.Log
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.benidict.compose.utilities.loadYears

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownYearView(
    onExpandedChange: (Boolean) -> Unit
) {
    val expand by remember { mutableStateOf(true) }

    val years = loadYears()
    ExposedDropdownMenuBox(
        expanded = expand,
        onExpandedChange = { ex ->
            onExpandedChange(!ex)
        }
    ) {
        years.forEach { selectionOption ->
            DropdownMenuItem(
                text = {
                    Text(text = selectionOption)
                },
                onClick = {}
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DropDownYearViewPreview() {
    MaterialTheme {
        DropDownYearView {

        }
    }
}