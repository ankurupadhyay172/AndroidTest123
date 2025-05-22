package com.ankur.androidtest.cleancode.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ankur.androidtest.cleancode.presentation.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: UiState,
    onReload: () -> Unit,
    onItemClick: (String) -> Unit
) {
    val refreshState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        onReload()
    }

    PullToRefreshBox(
        state = refreshState,
        isRefreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                onReload() // this should update the uiState via ViewModel
                // Wait for some time or use state to detect completion
                delay(1000) // Or wait until uiState.isLoading == false
                isRefreshing = false
            }
        }
    ) {
        when {
            uiState.isLoading -> {
                LoadingScreen()
            }

            uiState.error.isNotEmpty() -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize(), // fill remaining space
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Please check your internet connection")
                        }
                    }
                }
            }

            uiState.data != null -> {
                DataListScreen(items = uiState.data, onItemClick =  onItemClick )
            }
        }
    }
}
