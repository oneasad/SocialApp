package com.oneasad.socialapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.oneasad.socialapp.data.local.LocalPost
import com.oneasad.socialapp.presentation.screens.CreatePostScreen
import com.oneasad.socialapp.presentation.screens.EditPostScreen
import com.oneasad.socialapp.presentation.screens.PostListScreen
import com.oneasad.socialapp.presentation.screens.PostScreen
import com.oneasad.socialapp.presentation.screens.SettingsScreen

fun NavGraphBuilder.postNavigation(
    navController: NavController,
    posts: List<LocalPost>,
    onDeletePost: (Int) -> Unit,
    modifier: Modifier,
    viewModel: PostViewModel
) {
    navigation(startDestination = "post_list", route = "post") {

        composable("post_list") {
            PostListScreen(
                posts = posts,
                onPostClick = { postId ->
                    navController.navigate("post/$postId")
                },
                onCreatePostClick = { navController.navigate("create_post") },
                modifier = modifier
            )
        }

        composable(
            route = "post/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: -1
            val post = posts.find { it.id == postId }

            if (post != null) {
                PostScreen(
                    post = post,
                    onEditClick = { navController.navigate("edit_post/$postId") },
                    onDeleteClick = { onDeletePost(post.id) },
                    modifier = modifier
                )

            } else {
                Text(text = "Post not found", modifier = Modifier.padding(16.dp))
            }
        }

        composable(
            route = "edit_post/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: -1
            val post = posts.find { it.id == postId }

            if (post != null) {
                EditPostScreen(
                    post = post,
                    onEditPost = { editedPost ->
                        viewModel.editPost(editedPost)
                        navController.navigate("post_list")
                    },
                    modifier = modifier
                )

            } else {
                Text(text = "Post not found", modifier = Modifier.padding(16.dp))
            }
        }

        composable("create_post") {
            CreatePostScreen(
                onPostCreated = { newPost ->
                    viewModel.createPost(newPost)
                    navController.navigate("post_list")
                },
                modifier = modifier
            )
        }

        composable("settings") {
            SettingsScreen(
                onResetClicked = { viewModel.onResetClicked(it) },
            )
        }
    }
}
