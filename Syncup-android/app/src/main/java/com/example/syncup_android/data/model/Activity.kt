package com.example.syncup_android.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Activity(
    @JsonProperty("_id")
    val id: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("points")
    val points: Int,
    @JsonProperty("isCompleted")
    val isCompleted: Boolean?
)
