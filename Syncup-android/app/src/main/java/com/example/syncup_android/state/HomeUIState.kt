package com.example.syncup_android.state

import com.example.syncup_android.data.model.User

data class HomeUIState(
    val leaderboard: HashMap<Int, User> = hashMapOf(),
    val leaderboardSize: Int = 0
)
