package com.example.syncup_android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.syncup_android.R
import com.example.syncup_android.ui.components.GameCard
import com.example.syncup_android.ui.navigation.NavRoutes

@Composable
fun GamesScreen (navController: NavController){
    val onPlayClicked = {
        navController.navigate(NavRoutes.PlayGame.name){
            launchSingleTop = true
        }
    }
    Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)) {
        GameCard("House bingo", R.drawable.first_bingo, "Last updated 1 hour ago", "Compete with your colleagues and earn points. Let’s see who can win this game.", onPlayClicked = onPlayClicked)
        GameCard("Trivia", R.drawable.second_bingo, "Last updated 3 hours ago", "Test your knowledge and earn points with each question answered correctly.", onPlayClicked = onPlayClicked)

    }

}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    GamesScreen(rememberNavController())
}
