package com.oneasad.socialapp.util

import com.oneasad.socialapp.data.ApiPost
import com.oneasad.socialapp.data.local.LocalPost

fun ApiPost.toLocalPost(): LocalPost {
    return LocalPost(id, userId, title, body)
}

fun LocalPost.toApiPost(): ApiPost {
    return ApiPost(userId, id, title, body)
}
