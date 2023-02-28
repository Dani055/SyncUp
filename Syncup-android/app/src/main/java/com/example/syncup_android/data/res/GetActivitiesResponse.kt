package com.example.syncup_android.data.res

import com.example.syncup_android.data.model.Activity
import com.fasterxml.jackson.annotation.JsonProperty

data class GetActivitiesResponse(
    @JsonProperty("message")
    val message: String,
    @JsonProperty("activities")
    val activities: List<Activity>
)
