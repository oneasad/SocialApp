package com.oneasad.socialapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class LocalPost(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)
