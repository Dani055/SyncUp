package com.example.syncup_android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.syncup_android.api.repository.BingoRepository
import com.example.syncup_android.data.UserContext
import com.example.syncup_android.data.req.CreateSubmissionRequest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (modifier: Modifier = Modifier){
    val scope = rememberCoroutineScope()
    val bingoRepo = BingoRepository()
    Column() {
        Text(text = UserContext.loggedUser!!.email)
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {
            scope.launch {
                try {
                    val res = bingoRepo.getActivities()
                    println(res)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }) {
            Text(text = "test get activities")
        }
        Button(onClick = {
            scope.launch {
                try {
                    val res = bingoRepo.getActivityById("63f1d4dda51354fa72cd6229")
                    println(res)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }) {
            Text(text = "Test get activity")
        }
        Button(onClick = {
            scope.launch {
                try {
                    val res = bingoRepo.getSubmissionsForActivity("63f1d4dda51354fa72cd6229")
                    println(res)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }) {
            Text(text = "Test get submissions")
        }
        Button(onClick = {
            scope.launch {
                try {
                    val req = CreateSubmissionRequest("asd123", "63f1d4dda51354fa72cd6229")
                    val res = bingoRepo.createSubmission(req)
                    println(res)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }) {
            Text(text = "Test create submission")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    HomeScreen()
}