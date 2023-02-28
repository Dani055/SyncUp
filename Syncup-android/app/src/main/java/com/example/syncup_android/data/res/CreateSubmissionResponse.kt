package com.example.syncup_android.data.res

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateSubmissionResponse(
    @JsonProperty("message")
    val message: String
)
