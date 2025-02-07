package com.oneasad.socialapp.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oneasad.socialapp.presentation.PostUiState
import com.oneasad.socialapp.presentation.PostViewModel
import com.oneasad.socialapp.presentation.components.ErrorScreen
import com.oneasad.socialapp.presentation.components.LoadingScreen
import com.oneasad.socialapp.presentation.postNavigation
import org.koin.androidx.compose.koinViewModel

@Composable
fun BlogHomeScreen(
    postUiState: PostUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    navController: NavHostController
) {
    val viewModel = koinViewModel<PostViewModel>()
    when {
        postUiState.isLoading -> LoadingScreen(modifier = modifier.fillMaxSize())
        postUiState.isError -> ErrorScreen(
            retryAction = retryAction,
            errorMessage = "No Posts Loaded",
            modifier = modifier.fillMaxSize()
        )
        else -> NavHost(
            navController = navController,
            startDestination = "post"
        ) {
            postNavigation(
                navController,
                postUiState.posts,
                viewModel = viewModel,
                modifier = Modifier.padding(contentPadding),
                onDeletePost = { postId ->
                    viewModel.deletePost(postId)
                    navController.popBackStack("post_list", inclusive = false) // Ensure back stack resets properly
                }
            )
        }
    }
}
