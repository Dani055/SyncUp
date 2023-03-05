package com.example.syncup_android.state

import com.example.syncup_android.data.model.Submission

data class ProfileUIState(
    val mySubmissions: List<Submission> = listOf()
)
