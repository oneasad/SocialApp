package com.oneasad.socialapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.oneasad.socialapp.data.local.LocalPost

@Composable
fun EditPostScreen(
    post: LocalPost,
    onEditPost: (LocalPost) -> Unit,
    modifier: Modifier
) {

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }  // State for userId

    LaunchedEffect(post) {
        post.let {
            title = it.title
            body = it.body
            userId = it.userId.toString()  // Pre-fill userId
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = body,
            onValueChange = { body = it },
            label = { Text("Body") },
            modifier = Modifier.fillMaxHeight(0.5f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (userId.isNotEmpty() && title.isNotEmpty() && body.isNotEmpty()) {
                onEditPost(
                    LocalPost(
                    id = post.id,
                    userId = userId.toInt(),  // Convert userId to Int
                    title = title,
                    body = body
                    )
                )
            }
        }) {
            Text("Save Changes")
        }
    }
}
