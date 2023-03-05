package com.example.syncup_android.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.syncup_android.data.UserContext
import com.example.syncup_android.data.model.Submission
import com.example.syncup_android.ui.navigation.NavRoutes
import com.example.syncup_android.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()
    val profileUiState by profileViewModel.uiState.collectAsState()
    val context = LocalContext.current

    //Load user's submissions when component is initialized
    LaunchedEffect(Unit) {
        scope.launch {
            try {
                profileViewModel.loadMySubmissions()
            } catch (e: Exception) {
                (e).printStackTrace()
            }
        }
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(50.dp, 50.dp)
                )
        ) {

        }
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 25.dp)
                .size(150.dp)
                .clip(CircleShape),
            model = UserContext.loggedUser?.profileImageUrl,
            contentDescription = "Your profile picture"
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 190.dp, start = 27.dp, end = 27.dp, bottom = 27.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(style = MaterialTheme.typography.titleMedium, text = "My profile")
                Icon(
                    modifier = Modifier.padding(start = 11.dp),
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit your profile"
                )
            }

            //User name field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Row(Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp)) {
                    Icon(
                        modifier = Modifier.padding(end = 15.dp),
                        tint = Color(0xFF9A9A9A),
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null
                    )
                    Text(color = Color(0xFF9A9A9A), text = "Name")
                }
                Text(
                    modifier = Modifier.padding(end = 25.dp),
                    text = "${UserContext.loggedUser?.firstName} ${UserContext.loggedUser?.lastName}"
                )
            }

            //User email field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Row(Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp)) {
                    Icon(
                        modifier = Modifier.padding(end = 15.dp),
                        tint = Color(0xFF9A9A9A),
                        imageVector = Icons.Outlined.Mail,
                        contentDescription = null
                    )
                    Text(color = Color(0xFF9A9A9A), text = "Email")
                }
                Text(
                    modifier = Modifier.padding(end = 25.dp),
                    text = "${UserContext.loggedUser?.email}"
                )
            }

            ////User date of birth field
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Row(Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp)) {
                    Icon(
                        modifier = Modifier.padding(end = 15.dp),
                        tint = Color(0xFF9A9A9A),
                        imageVector = Icons.Outlined.Cake,
                        contentDescription = null
                    )
                    Text(color = Color(0xFF9A9A9A), text = "Date of birth")
                }
                Text(modifier = Modifier.padding(end = 25.dp), text = "24 May 1997")
            }


            Text(
                modifier = Modifier.padding(top = 28.dp, bottom = 10.dp),
                style = MaterialTheme.typography.titleMedium,
                text = "My submissions"
            )

            //Display submissions in a scrollable row
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                itemsIndexed(profileUiState.mySubmissions) { index, item ->
                    MySubmissionShareCard(submission = item, context)
                }
            }

            //Button to log out
            Button(modifier = Modifier
                .width(150.dp)
                .padding(top = 36.dp), onClick = {
                scope.launch {
                    navController.navigate(NavRoutes.Login.name)
                    UserContext.logout()
                }
            }) {
                Text(text = "Log out")
            }
        }
    }
}

@Composable
fun MySubmissionShareCard(submission: Submission, context: Context) {
    Box(contentAlignment = Alignment.TopEnd, modifier = Modifier
        .clip(RoundedCornerShape(15.dp))
        .clickable(onClick = {
            shareSubmission(context, submission)
        })
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(140.dp)
                .height(100.dp),
            model = submission.evidenceUrl,
            contentDescription = submission.activity.description
        )
        IconButton(colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = Color.Black.copy(
                alpha = 0.6f
            )
        ), modifier = Modifier
            .padding(top = 8.dp, end = 8.dp)
            .size(20.dp)
            .clip(
                RoundedCornerShape(25.dp)
            ), onClick = { shareSubmission(context, submission) }) {
            Icon(
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(15.dp),
                imageVector = Icons.Default.Share,
                contentDescription = "Share submission"
            )
        }
    }
}

private fun shareSubmission(context: Context, submission: Submission) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, submission.evidenceUrl)
        putExtra(Intent.EXTRA_TEXT, "Game: House Bingo \r\n Points: ${submission.activity.points}")
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            "Share your submission"
        )
    )
}