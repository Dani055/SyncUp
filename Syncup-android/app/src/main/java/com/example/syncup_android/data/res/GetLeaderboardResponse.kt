package com.example.syncup_android.data.res

import com.example.syncup_android.data.model.User
import com.fasterxml.jackson.annotation.JsonProperty

data class GetLeaderboardResponse(
    @JsonProperty("message")
    val message: String,
    @JsonProperty("sortedUsers")
    val sortedUsers: List<User>
)
