package com.example.syncup_android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.VideogameAsset
import androidx.compose.material.icons.outlined.VideogameAssetOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.syncup_android.R
import com.example.syncup_android.api.repository.AuthRepository
import com.example.syncup_android.api.repository.BingoRepository
import com.example.syncup_android.data.UserContext
import com.example.syncup_android.data.req.CreateSubmissionRequest
import com.example.syncup_android.ui.components.AchievementCard
import com.example.syncup_android.ui.components.LeaderboardCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (modifier: Modifier = Modifier){
    val scope = rememberCoroutineScope()
    val authRepo = AuthRepository()

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(start = 40.dp,end = 40.dp).offset(y = 50.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Column() {
                    Text(style = MaterialTheme.typography.titleLarge, text = "Welcome back, ${UserContext.loggedUser?.firstName}")
                    Text(style = MaterialTheme.typography.bodyMedium, text = "${UserContext.loggedUser?.position}")
                }
                AsyncImage(contentScale = ContentScale.Crop, modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape), model = UserContext.loggedUser?.profileImageUrl, contentDescription = "Your profile picture")
            }
        }
            Box(modifier = Modifier
                .fillMaxSize()){
                Image(modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop , painter = painterResource(id = R.drawable.mask_group_4_1_1), contentDescription = null)
                Column(modifier = Modifier.padding(start = 35.dp, end = 35.dp, top = 120.dp, bottom = 20.dp)) {
                    Text(modifier = Modifier.padding(top = 30.dp, bottom = 10.dp), text = "Your achievements", style = MaterialTheme.typography.titleMedium)
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)) {
                        AchievementCard(modifier = Modifier.weight(1f), icon = Icons.Outlined.VideogameAsset, name = "Games played", score = 5)
                        Spacer(modifier = Modifier.width(20.dp))
                        AchievementCard(modifier = Modifier.weight(1f),icon = Icons.Outlined.StarOutline, name = "Points", score = UserContext.loggedUser?.points!!)
                        Spacer(modifier = Modifier.width(20.dp))
                        AchievementCard(modifier = Modifier.weight(1f),icon = Icons.Outlined.Leaderboard, name = "Position", score = 7)
                    }
                    Text(modifier = Modifier.padding(start = 5.dp, top = 40.dp, bottom = 12.dp), style = MaterialTheme.typography.titleMedium, text = "Leaderboard")
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
                        LeaderboardCard(position = 6, user = UserContext.loggedUser!!)
                        LeaderboardCard(position = 7, user = UserContext.loggedUser!!)
                        LeaderboardCard(position = 8, user = UserContext.loggedUser!!)
                        LeaderboardCard(position = 9, user = UserContext.loggedUser!!)
                    }
                }
            }


    }
    Column(modifier = Modifier
        .fillMaxSize()) {

    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    HomeScreen()
}