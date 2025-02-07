package com.oneasad.socialapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

class KtorClientProvider {
    fun createClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
            defaultRequest {
                headers.append(HttpHeaders.Accept, ContentType.Application.Json)
            }
        }
    }
}

//<editor-fold desc="Original simple ktor">
class KtorApiService(private val client: HttpClient) {
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    suspend fun getAllPosts(): List<ApiPost> {
        val response = client.get("$baseUrl/posts")
        return response.body<List<ApiPost>>()
    }

    suspend fun getPostById(id: Int): ApiPost {
        val response = client.get("$baseUrl/posts/$id")
        return response.body<ApiPost>()
    }

    suspend fun createPost(post: ApiPost) {
        client.post("$baseUrl/posts") {
            contentType(ContentType.Application.Json)
            setBody(post)
        }
    }

    suspend fun updatePost(post: ApiPost) {
        client.put("$baseUrl/posts/${post.id}") {
            contentType(ContentType.Application.Json)
            setBody(post)
        }
    }

    suspend fun deletePost(id: Int) {
        client.delete("$baseUrl/posts/$id")
    }
}
//</editor-fold>