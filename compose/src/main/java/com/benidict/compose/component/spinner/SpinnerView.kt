package com.benidict.compose.component.spinner

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.benidict.compose.R
import com.benidict.compose.utilities.monthYearFromDate
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun YearSpinner(
    selectedDate: LocalDate,
    years: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    SpinnerView(
        modifier = Modifier.wrapContentSize(),
        dropDownModifier = Modifier.height(500.dp),
        items = years,
        selectedItem = selectedItem,
        onItemSelected = onItemSelected,
        selectedItemFactory = { modifier, item ->
            Row(
                modifier = modifier
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    monthYearFromDate(selectedDate.withYear(item.toInt())),
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    Modifier.width(5.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = ""
                )
            }
        },
        dropdownItemFactory = { item, _ ->
            Text(text = item)
        }
    )
}

@Composable
fun <T> SpinnerView(
    modifier: Modifier = Modifier,
    dropDownModifier: Modifier = Modifier,
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    selectedItemFactory: @Composable (Modifier, T) -> Unit,
    dropdownItemFactory: @Composable (T, Int) -> Unit,
) {
    var expanded: Boolean by remember { mutableStateOf(false) }

    Box(modifier = modifier.wrapContentSize(Alignment.Center)) {
        selectedItemFactory(
            Modifier
                .clickable { expanded = true },
            selectedItem
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = dropDownModifier
        ) {
            items.forEachIndexed { index, element ->
                DropdownMenuItem(
                    text = { dropdownItemFactory(element, index) },
                    onClick = {
                        onItemSelected(items[index])
                        expanded = false
                    })
            }
        }
    }
}