package com.example.syncup_android.state

import com.example.syncup_android.data.model.Activity

data class ActivityDetailsState(
    val activity: Activity? = null,
    val isCompleted: Boolean = false
)
