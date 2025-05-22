package com.ankur.androidtest

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ankur.androidtest.cleancode.presentation.ContentViewModel
import com.ankur.androidtest.cleancode.presentation.screens.DetailScreen
import com.ankur.androidtest.cleancode.presentation.screens.HomeScreen
import com.ankur.androidtest.cleancode.presentation.screens.LoadingScreen


@Composable
fun AppNavigator(viewModel: ContentViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination ="home" ) {
            composable(route = "home"){
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                HomeScreen(
                    uiState = uiState,
                    onReload = { viewModel.getContent() },
                    onItemClick = { selectedItem ->
                        // Navigate to detail screen with selected item
                         navController.navigate("details/$selectedItem")
                    }
                )
            }
        composable("details/{item}") { backStackEntry ->
            val item = backStackEntry.arguments?.getString("item")
            item?.let { DetailScreen(it){
                navController.popBackStack()
            } }
        }
    }
}
