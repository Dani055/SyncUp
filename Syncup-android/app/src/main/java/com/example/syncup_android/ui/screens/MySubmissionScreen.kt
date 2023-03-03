package com.example.syncup_android.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.syncup_android.permissions.CheckAndRequestCameraPermission

@Composable
fun MySubmissionScreen (navController: NavController, activityId: String?){
    CheckAndRequestCameraPermission()
    Text(text = activityId!!)
}