package com.example.syncup_android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.syncup_android.R
import com.example.syncup_android.data.UserContext
import com.example.syncup_android.ui.components.AchievementCard
import com.example.syncup_android.ui.components.LeaderboardCard
import com.example.syncup_android.viewmodel.HomeViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeViewModel: HomeViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val homeUIState by homeViewModel.uiState.collectAsState()

    //Load leaderboard when component is initialized
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                homeViewModel.getLeaderboard()
            } catch (e: Exception) {
                (e).printStackTrace()
            }
        }
    }



    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .offset(y = 50.dp)) {

            //Greeting section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        text = "Welcome back, ${UserContext.loggedUser?.firstName}"
                    )
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        text = "${UserContext.loggedUser?.position}"
                    )
                }
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape),
                        model = UserContext.loggedUser?.profileImageUrl,
                        contentDescription = "Your profile picture"
                    )
                }

            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.mask_group_4_1_1),
                contentDescription = null
            )
            Column(
                modifier = Modifier.padding(
                    start = 35.dp,
                    end = 35.dp,
                    top = 120.dp,
                    bottom = 20.dp
                )
            ) {
                Text(
                    modifier = Modifier.padding(top = 30.dp, bottom = 10.dp),
                    text = "Your achievements",
                    style = MaterialTheme.typography.titleMedium
                )
                //Row for showing achievements
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(0.dp, 125.dp)
                        .padding(top = 10.dp)
                ) {
                    AchievementCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        icon = Icons.Outlined.VideogameAsset,
                        name = "Games played",
                        score = 5
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    AchievementCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        icon = Icons.Outlined.StarOutline,
                        name = "Points",
                        score = UserContext.loggedUser?.points!!
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    AchievementCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        icon = Icons.Outlined.Schedule,
                        name = "Hours played",
                        score = 21
                    )
                }

                //Leaderboard section
                Text(
                    modifier = Modifier.padding(start = 5.dp, top = 27.dp, bottom = 10.dp),
                    style = MaterialTheme.typography.titleMedium,
                    text = "Leaderboard (${homeUIState.leaderboardSize})"
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    homeUIState.leaderboard.forEach { (key, value) ->
                        LeaderboardCard(position = key, user = value)
                    }
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    HomeScreen()
}