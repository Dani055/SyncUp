package com.example.syncup_android.state

import com.example.syncup_android.data.model.Activity
import com.example.syncup_android.data.model.Submission

data class PlayBingoState (
    val activities: List<Activity> = listOf(),
    val submissions: List<Submission> = listOf()
)