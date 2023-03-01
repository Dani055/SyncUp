package com.example.syncup_android.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavBar (currentScreen: NavRoutes, onNavigationIconClick: (String) -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        enumValues<NavRoutes>().forEach {
            if (it != NavRoutes.Login && it != NavRoutes.Onboarding) {
                NavigationBarItem(
                    icon = { Icon(imageVector = if (currentScreen.name == it.name) it.seletedIcon else it.icon, modifier = Modifier.size(28.dp), contentDescription = it.title) },
                    selected = currentScreen.name == it.name,
                    onClick = { onNavigationIconClick(it.name) },
                    label = {Text(text = it.title)},
                    colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.secondaryContainer)
                )
            }
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview(){
    BottomNavBar(currentScreen = NavRoutes.Home, onNavigationIconClick = {})
}
