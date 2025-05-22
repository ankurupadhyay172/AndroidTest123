package com.ankur.androidtest.cleancode.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ankur.androidtest.cleancode.domain.model.DomainModel


@Composable
fun ReloadScreen(items: List<DomainModel>) {
    Column {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            color = Color(0xFF101638)
        )
        DataListScreen(items, onItemClick = {})
    }
}
