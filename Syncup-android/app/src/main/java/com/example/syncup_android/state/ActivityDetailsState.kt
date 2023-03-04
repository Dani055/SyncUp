package com.example.syncup_android.state

import com.example.syncup_android.data.model.Activity
import com.example.syncup_android.data.model.Submission

data class ActivityDetailsState(
    val activity: Activity? = null,
    val isCompleted: Boolean = false,
    val mySubmission: Submission? = null
)
