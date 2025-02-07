package com.oneasad.socialapp.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oneasad.socialapp.data.local.LocalPost
import com.oneasad.socialapp.data.local.PostDao
import com.oneasad.socialapp.domain.KtorRepository
import com.oneasad.socialapp.util.toLocalPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val postRepository: KtorRepository,
    private val postDao: PostDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeAllPosts()
    }

    // Observe posts from Room as a Flow
    private fun observeAllPosts() {
        viewModelScope.launch {
            postDao.getAll().collect { posts ->
                if (posts.isEmpty()) fetchRemoteData()
                _uiState.value = PostUiState(posts = posts, isLoading = false)
            }
        }
    }

    // Create new post
    fun createPost(post: LocalPost) {
        viewModelScope.launch {
            try {
                postDao.insert(post)  // Room Flow will update UI automatically
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }

    // Edit existing post
    fun editPost(post: LocalPost) {
        viewModelScope.launch {
            try {
                postDao.update(post)  // Room Flow will update UI automatically
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }

    // Delete post
    fun deletePost(postId: Int) {
        viewModelScope.launch {
            try {
                val post = _uiState.value.posts.find { it.id == postId }
                post?.let {
                    postDao.delete(post)  // Room Flow will update UI automatically
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }

    // Fetch remote data and populate Room
    fun fetchRemoteData() {
        viewModelScope.launch {
            try {
                val apiPosts = postRepository.getAllPosts()
                if (apiPosts.isNotEmpty()) {
                    val localPosts = apiPosts.map { it.toLocalPost() }
                    postDao.insertAll(localPosts)  // Room Flow will update UI automatically
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }

    // Reset posts based on internet connection and call getPosts to fetch new data
    fun onResetClicked(context: Context) {
        viewModelScope.launch {
            val result = resetPosts(context)
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        }
    }

    // Function to reset posts in Room by checking internet connection
    private suspend fun resetPosts(context: Context): String {
        var result: String
        try {
            if (isInternetAvailable(context)) {
                _uiState.value = PostUiState(posts = emptyList())
                postDao.deleteAll()
                fetchRemoteData()
                result = "Posts have been reset successfully."
            } else {
                result = "No internet connection."
            }
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(isError = true)
            result = "Error resetting posts: ${e.message}"
        }
        return result
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork?.let {
            connectivityManager.getNetworkCapabilities(it)
        }
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}
