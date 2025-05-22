package com.ankur.androidtest.cleancode.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ankur.androidtest.cleancode.domain.model.DomainModel

@Preview(heightDp = 200, widthDp = 200)
@Composable
fun DetailScreen(text: String,onItemBackClick: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("← Back", color = Color.Blue, modifier = Modifier.clickable { onItemBackClick.invoke() })
        Spacer(modifier = Modifier.height(16.dp))
        Text(text, style = MaterialTheme.typography.bodySmall)
    }
}
