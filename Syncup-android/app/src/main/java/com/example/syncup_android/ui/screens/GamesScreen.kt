package com.example.syncup_android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.syncup_android.R
import com.example.syncup_android.ui.components.GameCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen (){
    Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)) {
        GameCard("House bingo", R.drawable.first_bingo, "Last updated 1 hour ago", "Compete with your colleagues and earn points. Let’s see who can win this game.")
        GameCard("Trivia", R.drawable.second_bingo, "Last updated 3 hours ago", "Test your knowledge and earn points with each question answered correctly.")

    }

}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    GamesScreen()
}
