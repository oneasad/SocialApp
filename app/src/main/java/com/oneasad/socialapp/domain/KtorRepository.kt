package com.oneasad.socialapp.domain

import com.oneasad.socialapp.data.KtorApiService
import com.oneasad.socialapp.data.ApiPost

class KtorRepository(private val api: KtorApiService) {

    suspend fun getAllPosts(): List<ApiPost> {
        return api.getAllPosts()
    }

    suspend fun getPostById(id: Int): ApiPost {
        return api.getPostById(id)
    }

    suspend fun createPost(post: ApiPost){
        api.createPost(post)
    }
    suspend fun updatePost(post: ApiPost){
        api.updatePost(post)
    }
    suspend fun deletePost(id: Int){
        api.deletePost(id)
    }
}
