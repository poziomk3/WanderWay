package com.pwr.wanderway.data.repository

import com.pwr.wanderway.data.model.api.forum.CreatedPost
import com.pwr.wanderway.data.model.api.forum.PublicPost
import com.pwr.wanderway.network.ApiService
import javax.inject.Inject

class ForumRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getPosts(): List<PublicPost> {
        val response = apiService.getForumPosts()
        return if (response.isSuccessful) {
            response.body()?.posts.orEmpty()
        } else {
            throw Exception("Failed to fetch posts: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getPostById(postId: Int): PublicPost? {
        val response = apiService.getForumPostById(postId)
        return if (response.isSuccessful) {
            response.body()
        } else {
            throw Exception("Failed to fetch posts: ${response.errorBody()?.string()}")
        }
    }

    suspend fun addPost(routeId: Int, createdPost: CreatedPost) {
        val response = apiService.addForumPost(routeId, createdPost)
        if (!response.isSuccessful) {
            throw Exception("Failed to post a new post: ${response.errorBody()?.string()}")
        }
    }
}