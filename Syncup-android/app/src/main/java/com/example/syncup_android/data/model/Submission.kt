package com.example.syncup_android.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Submission(
    @JsonProperty("_id")
    val id: String,
    @JsonProperty("evidenceUrl")
    val evidenceUrl: String,
    @JsonProperty("completedBy")
    val completedBy: User,
    @JsonProperty("activity")
    val activity: Activity
)
