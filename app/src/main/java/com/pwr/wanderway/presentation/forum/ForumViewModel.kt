package com.pwr.wanderway.presentation.forum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwr.wanderway.data.model.api.forum.CreatedPost
import com.pwr.wanderway.data.model.api.forum.PublicPost
import com.pwr.wanderway.data.repository.ForumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForumViewModel @Inject constructor(
    private val forumRepository: ForumRepository
) : ViewModel() {


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun addPost(routeId: Int, title: String, post: String, rating: Int) {
        val newPost = CreatedPost(title, post, rating)

        viewModelScope.launch {
            _isLoading.value = true
            try {
                forumRepository.addPost(routeId, newPost)
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun getPost(postId: Int): PublicPost? {
        _isLoading.value = true
        return try {
            forumRepository.getPostById(postId)
        } catch (e: Exception) {
            null
        } finally {
            _isLoading.value = false
        }
    }


    suspend fun getAllPosts(): List<PublicPost> {
        _isLoading.value = true
        return try {
            forumRepository.getPosts()
        } catch (e: Exception) {
            emptyList()
        } finally {
            _isLoading.value = false
        }
    }
}
