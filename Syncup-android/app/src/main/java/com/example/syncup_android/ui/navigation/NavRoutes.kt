package com.example.syncup_android.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector


enum class NavRoutes(val title: String, val icon: ImageVector, val seletedIcon: ImageVector){
    Onboarding(title="Onboarding", icon = Icons.Default.Star, seletedIcon = Icons.Default.Star),
    Login(title="Login", icon = Icons.Default.Star, seletedIcon = Icons.Default.Star),
    Home(title="Home", icon = Icons.Outlined.Home, seletedIcon = Icons.Default.Home),
    Games(title="Games", icon = Icons.Outlined.Groups, seletedIcon = Icons.Default.Groups),
    PlayGame(title="House Bingo", icon = Icons.Outlined.Groups, seletedIcon = Icons.Default.Groups),
    ActivityDetails(title="House Bingo", icon = Icons.Outlined.Groups, seletedIcon = Icons.Default.Home),
    Profile(title="Profile", icon = Icons.Outlined.AccountCircle, seletedIcon = Icons.Default.AccountCircle),
}
