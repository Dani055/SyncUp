package com.example.syncup_android.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    val snackbarHostState = remember { SnackbarHostState() }
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
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState){data ->
                val isError = (data.visuals as? SnackbarVisualsWithError)?.isError ?: false
                Snackbar(modifier = Modifier
                    .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                    .border(2.dp, if(isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary)
                    .background(color = if(isError) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surface),
                    action = {
                        TextButton(
                            onClick = {data.dismiss()},
                        ) { Text(data.visuals.actionLabel ?: "") }
                    }
                ) {
                    Text(data.visuals.message)
                }
            }
        },
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
            NavGraph(navController = navController, innerPadding = innerPadding, modifier = modifier, snackBar = snackbarHostState)
        })
}

class SnackbarVisualsWithError(
    override val message: String,
    val isError: Boolean
) : SnackbarVisuals {
    override val actionLabel: String
        get() = "X"
    override val withDismissAction: Boolean
        get() = true
    override val duration: SnackbarDuration
        get() = SnackbarDuration.Short
}