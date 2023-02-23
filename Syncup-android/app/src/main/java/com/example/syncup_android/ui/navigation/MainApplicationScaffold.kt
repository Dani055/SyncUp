package com.example.syncup_android.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApplicationScaffold (currentScreen: NavRoutes, navController: NavHostController, scope: CoroutineScope, modifier: Modifier = Modifier){
    val showTopBar = when(currentScreen){
        NavRoutes.Onboarding -> false;
        NavRoutes.Login -> false;
        else -> true
    }
    val showBottomBar = when(currentScreen){
        NavRoutes.Onboarding -> false;
        NavRoutes.Login -> false;
        else -> true
    }
    Scaffold(
        topBar = { AnimatedVisibility(visible = showTopBar) {
            Text(text = "top bar rn")
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = showBottomBar) {
                Text(text = "metro boomin")
            }
        },
        content = { innerPadding ->
            NavGraph(navController = navController, innerPadding = innerPadding, modifier = modifier)
        })
}