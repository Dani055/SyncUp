package com.example.syncup_android.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApplicationScaffold (currentScreen: NavRoutes, navController: NavHostController, scope: CoroutineScope, modifier: Modifier = Modifier){
    val snackbarHostState = remember { SnackbarHostState() }
    val haptic = LocalHapticFeedback.current

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
    Scaffold(modifier = Modifier.navigationBarsPadding(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState){data ->
                val isError = (data.visuals as? SnackbarVisualsWithError)?.isError ?: false
                Snackbar(modifier = Modifier
                    .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                    .border(
                        2.dp,
                        if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    )
                    .background(color = if (isError) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surface),
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
        floatingActionButton = {
             if(currentScreen.name == NavRoutes.Games.name){
                 FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp), containerColor = MaterialTheme.colorScheme.inversePrimary, shape = RoundedCornerShape(16.dp),) {
                     androidx.compose.material.Icon(
                         imageVector = Icons.Default.Add,
                         contentDescription = "Add game"
                     )
                 }
             }
        },
        topBar = { AnimatedVisibility(visible = showTopBar) {
                TopBar(currentScreen = currentScreen, onNavigationIconClick = {})
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = showBottomBar) {
                BottomNavBar(currentScreen = currentScreen, onNavigationIconClick = {
                    navController.navigate(it){
                        launchSingleTop = true
                    }
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                })
            }
        },
        content = { innerPadding ->
            NavGraph(navController = navController, innerPadding = innerPadding, snackBar = snackbarHostState)
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