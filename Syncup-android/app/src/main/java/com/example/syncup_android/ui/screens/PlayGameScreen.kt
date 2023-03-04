package com.example.syncup_android.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.syncup_android.data.model.Activity
import com.example.syncup_android.data.model.Submission
import com.example.syncup_android.ui.navigation.NavRoutes
import com.example.syncup_android.viewmodel.PlayBingoViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PlayGameScreen (navController: NavController){
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = @Composable { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
            },
        ) {
            Tab(
                selected = pagerState.currentPage == 0,
                onClick = { scope.launch { pagerState.animateScrollToPage(0) } },
                text = {
                    Text(text = "Game activities")
                }
            )
            Tab(
                selected = pagerState.currentPage == 1,
                onClick = { scope.launch { pagerState.animateScrollToPage(1) } },
                text = {
                    Text(text = "Team submissions")
                }
            )
            }
        }
        HorizontalPager(
            count = 2,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 70.dp, bottom = 20.dp)
                .fillMaxSize(),
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) {
            if(pagerState.currentPage == 0) {
                BingoGame(navController = navController, scope = scope)
            }
            else{
                TeamSubmissions(scope = scope)
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BingoGame(navController: NavController, scope: CoroutineScope,playBingoViewModel: PlayBingoViewModel = viewModel()){
    val bingoUIState by playBingoViewModel.uiState.collectAsState()

    LaunchedEffect(Unit){
        scope.launch {
            try {
                playBingoViewModel.loadTasks()
            } catch (e: Exception) {
                (e).printStackTrace()
            }
        }
    }

    val completedActivities = bingoUIState.activities.filter { it.isCompleted == true }
    val earnedPoints = completedActivities.sumOf {it.points }
    val totalPoints = bingoUIState.activities.sumOf { it.points }

    val onAddSubmissionClick = { activityId: String ->
        navController.navigate("${NavRoutes.ActivityDetails.name}/$activityId"){
            launchSingleTop = true
        }
    }

    Card(modifier = Modifier
        .fillMaxSize()
        ) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center, text = "Total points: $earnedPoints out of $totalPoints")
        LazyVerticalGrid(modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp), columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),  content = {
            items(bingoUIState.activities.size){ index ->
                BingoActivityCard(activity = bingoUIState.activities[index] , index = index, onAddSubmissionClick = onAddSubmissionClick)
            }
        })
    }

}

@Composable
fun TeamSubmissions(scope: CoroutineScope,playBingoViewModel: PlayBingoViewModel = viewModel()){
    val bingoUIState by playBingoViewModel.uiState.collectAsState()

    LaunchedEffect(Unit){
        scope.launch {
            try {
                playBingoViewModel.loadSubmissions()
            } catch (e: Exception) {
                (e).printStackTrace()
            }
        }
    }
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)){
        itemsIndexed(bingoUIState.submissions){index, item ->
            SubmissionCard(item, index)

        }
    }

}

@Composable
fun BingoActivityCard(activity: Activity, index: Int, onAddSubmissionClick: (String) -> Unit){
    Column(modifier = Modifier.background(shape = RoundedCornerShape(15.dp), color = MaterialTheme.colorScheme.onPrimary)) {
        Column(modifier = Modifier
            .padding(15.dp)
            .heightIn(150.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, text = "Task ${index + 1}")
                if(activity.isCompleted!!) Icon(
                    imageVector = Icons.Outlined.CheckCircleOutline,
                    contentDescription = null,
                    tint = Color(0xFF289C56)
                ) else {}
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center, text = activity.name)
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.weight(1f))
                ClickableText(style = TextStyle(color = MaterialTheme.colorScheme.primary), text = AnnotatedString("View"), onClick = {onAddSubmissionClick(activity.id)})
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubmissionCard(submission: Submission, index: Int){
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(contentScale = ContentScale.Crop, modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape), model = submission.completedBy.profileImageUrl, contentDescription = null)
                Text(style = MaterialTheme.typography.titleMedium, text = "${submission.completedBy.firstName} ${submission.completedBy.lastName}")
                Text(color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, text = "${index + 2} hours ago")
            }
            Text(style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 15.dp), text = "Task: ${submission.activity.name}")
            AsyncImage(modifier = Modifier.fillMaxWidth().padding(top = 10.dp).clip(RoundedCornerShape(15.dp)), contentScale = ContentScale.Crop, model = submission.evidenceUrl, contentDescription = submission.activity.description)
        }

    }
}

