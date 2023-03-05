package com.example.syncup_android.ui.navigation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp



@Composable
fun TopBar (currentScreen: NavRoutes, onNavigationIconClick: () -> Unit){
        CenterAlignedTopAppBar(
            modifier = Modifier.systemBarsPadding(),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
            title = { Text(text = currentScreen.title, fontSize = 18.sp)},
            navigationIcon = {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Open navigation",)
                }
            }
        )
}