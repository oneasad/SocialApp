package com.oneasad.socialapp.presentation

import com.oneasad.socialapp.data.local.LocalPost

data class PostUiState(
    val posts: List<LocalPost> = emptyList(),
    val selectedPost: LocalPost? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    )