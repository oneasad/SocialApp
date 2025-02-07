package com.oneasad.socialapp.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oneasad.socialapp.data.local.LocalPost

@Composable
fun PostListScreen(
    posts: List<LocalPost>,
    onPostClick: (Int) -> Unit,
    onCreatePostClick: () -> Unit,
    modifier: Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onCreatePostClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Post")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = modifier.fillMaxSize()
        ) {
            items(posts) { post ->
                PostItem(
                    post,
                    onPostClick = { onPostClick(post.id) },
                )
            }
        }
    }
}


//@Preview
//@Composable
//fun PreviewPostListScreen() {
//    val posts = listOf(
//        Post(
//            title = "Title 1", body = "Body 1",
//            userId = 1,
//            id = 1
//        ), Post(
//            title = "Title 2", body = "Body 2",
//            userId = 1,
//            id = 1
//        )
//    )
//    PostListScreen(
//        posts = posts,
//        onPostClick = {},
//        modifier = Modifier
//    )
//}