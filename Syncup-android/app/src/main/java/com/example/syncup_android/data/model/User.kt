package com.example.syncup_android.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    @JsonProperty("_id")
    val id: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("firstName")
    val firstName: String,
    @JsonProperty("position")
    val position: String,
    @JsonProperty("lastName")
    val lastName: String,
    @JsonProperty("profileImageUrl")
    val profileImageUrl: String,
    @JsonProperty("points")
    val points: Int,
    @JsonProperty("roles")
    val roles: List<String>
    )
