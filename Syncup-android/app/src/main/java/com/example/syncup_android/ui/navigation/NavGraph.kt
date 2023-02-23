package com.example.syncup_android.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.syncup_android.ui.screens.LoginScreen
import com.example.syncup_android.ui.screens.OnboardingScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavGraph(modifier: Modifier = Modifier, navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Onboarding.name
    )
    {
        composable(route = NavRoutes.Onboarding.name) {
            OnboardingScreen(onGetStartedClick = {navController.navigate(NavRoutes.Login.name)})
        }
        composable(route = NavRoutes.Login.name) {
            LoginScreen()
        }
    }

}

