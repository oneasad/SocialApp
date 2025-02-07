package com.oneasad.socialapp.data

import kotlinx.serialization.Serializable

@Serializable
data class ApiPost(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

