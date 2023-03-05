package com.example.syncup_android.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.syncup_android.ui.components.BingoActivityCard
import com.example.syncup_android.ui.components.BingoSubmissionCard
import com.example.syncup_android.ui.navigation.NavRoutes
import com.example.syncup_android.viewmodel.PlayBingoViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PlayGameScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        //The 2 tabs for tasks and submissions
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
        if (pagerState.currentPage == 0) {
            BingoGame(navController = navController, scope = scope)
        } else {
            TeamSubmissions(scope = scope)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BingoGame(
    navController: NavController,
    scope: CoroutineScope,
    playBingoViewModel: PlayBingoViewModel = viewModel()
) {
    val bingoUIState by playBingoViewModel.uiState.collectAsState()

    //Load tasks when component is initialized
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                playBingoViewModel.loadTasks()
            } catch (e: Exception) {
                (e).printStackTrace()
            }
        }
    }

    //Calculate how many points are earned in this game
    val completedActivities = bingoUIState.activities.filter { it.isCompleted == true }
    val earnedPoints = completedActivities.sumOf { it.points }
    val totalPoints = bingoUIState.activities.sumOf { it.points }

    val onAddSubmissionClick = { activityId: String ->
        navController.navigate("${NavRoutes.ActivityDetails.name}/$activityId") {
            launchSingleTop = true
        }
    }

    Card(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            text = "Total points: $earnedPoints out of $totalPoints"
        )

        //Grid for showing the bingo cards
        LazyVerticalGrid(modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            content = {
                items(bingoUIState.activities.size) { index ->
                    BingoActivityCard(
                        activity = bingoUIState.activities[index],
                        index = index,
                        onAddSubmissionClick = onAddSubmissionClick
                    )
                }
            })
    }

}

@Composable
fun TeamSubmissions(scope: CoroutineScope, playBingoViewModel: PlayBingoViewModel = viewModel()) {
    val bingoUIState by playBingoViewModel.uiState.collectAsState()

    //Load submissions when component is initialized
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                playBingoViewModel.loadSubmissions()
            } catch (e: Exception) {
                (e).printStackTrace()
            }
        }
    }

    //Show all contents in a scrollable column
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        itemsIndexed(bingoUIState.submissions) { index, item ->
            BingoSubmissionCard(item, index)
        }
    }

}


