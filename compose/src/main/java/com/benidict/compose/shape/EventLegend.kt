package com.benidict.compose.shape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircleEventLegendsView() {
    Column(modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier.size(5.dp).clip(CircleShape).background(Color.Red)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircleEventLegendsViewPreview() {
    MaterialTheme {
        CircleEventLegendsView()
    }
}