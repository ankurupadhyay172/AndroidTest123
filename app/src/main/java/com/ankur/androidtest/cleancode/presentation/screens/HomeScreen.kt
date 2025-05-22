package com.ankur.androidtest.cleancode.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ankur.androidtest.cleancode.presentation.UiState

@Composable
fun HomeScreen(
    uiState: UiState,
    onReload: () -> Unit,
    onItemClick: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        onReload()
    }

    if (uiState.isLoading) {
        LoadingScreen()
    } else if (uiState.error.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Please check your internet connection")
        }
    } else {
        uiState.data?.let { list ->
            DataListScreen(items = list, onItemClick =  onItemClick )
        }
    }
}
