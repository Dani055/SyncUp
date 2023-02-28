package com.example.syncup_android.data.res

import com.example.syncup_android.data.model.Activity
import com.example.syncup_android.data.model.Submission
import com.fasterxml.jackson.annotation.JsonProperty

data class GetActivityByIdResponse(
    @JsonProperty("message")
    val message: String,
    @JsonProperty("activity")
    val activity: Activity,
    @JsonProperty("isCompleted")
    val isCompleted: Boolean,
    @JsonProperty("mySubmission")
    val mySubmission: Submission?
)
