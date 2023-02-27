package com.example.syncup_android.ui.navigation

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavRoutes(val title: String, val icon: ImageVector){
    Onboarding(title="Onboarding", icon = Icons.Default.Star),
    Login(title="Login", icon = Icons.Default.Star),
    Home(title="Home", icon = Icons.Default.Star)
}
