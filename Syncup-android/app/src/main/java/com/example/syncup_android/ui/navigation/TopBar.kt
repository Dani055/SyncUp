package com.example.syncup_android.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
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