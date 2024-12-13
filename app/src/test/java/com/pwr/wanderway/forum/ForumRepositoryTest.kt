package com.pwr.wanderway.forum

import com.pwr.wanderway.data.model.api.forum.AllPostsDTO
import com.pwr.wanderway.data.model.api.forum.CreatedPost
import com.pwr.wanderway.data.model.api.forum.PublicPost
import com.pwr.wanderway.data.repository.ForumRepository
import com.pwr.wanderway.dateFormat
import com.pwr.wanderway.network.ApiService
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever
import retrofit2.Response
import java.util.Date
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class ForumRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var forumRepository: ForumRepository

    @Before
    fun setUp() {
        apiService = mock()
        forumRepository = ForumRepository(apiService)
    }

    private fun mockPostsDTO(): AllPostsDTO {
        return AllPostsDTO(
            posts = listOf(
                PublicPost(
                    id = 1,
                    title = "Test Post 1",
                    body = "Content 1",
                    rating = 5,
                    author = "Author 1",
                    created_at = dateFormat.parse("2023-12-12T10:00:00Z"),
                    route = 1,
                    random_poi_id = 2
                ),
                PublicPost(
                    id = 2,
                    title = "Test Post 2",
                    body = "Content 2",
                    rating = 4,
                    author = "Author 2",
                    created_at = dateFormat.parse("2023-12-12T10:00:00Z"),
                    route = 2,
                    random_poi_id = 3
                )
            )
        )
    }

    @Test
    fun `getPosts returns list of posts when response is successful`() = runTest {
        val mockPostsDTO = mockPostsDTO()
        val mockResponse = Response.success(mockPostsDTO)

        whenever(apiService.getForumPosts()).thenReturn(mockResponse)

        val posts = forumRepository.getPosts()

        assertEquals(mockPostsDTO.posts, posts)
        verify(apiService).getForumPosts()
    }

    @Test
    fun `getPosts throws exception when response is not successful`() = runTest {
        val mockErrorResponse =
            Response.error<AllPostsDTO>(500, okhttp3.ResponseBody.create(null, "Error"))

        whenever(apiService.getForumPosts()).thenReturn(mockErrorResponse)

        val exception = assertFailsWith<Exception> { forumRepository.getPosts() }

        assertEquals("Failed to fetch posts: Error", exception.message)
        verify(apiService).getForumPosts()
    }

    @Test
    fun `getPostById returns post when response is successful`() = runTest {
        val mockPost = PublicPost(
            id = 1,
            title = "Test Post",
            body = "Content",
            rating = 5,
            author = "Author",
            created_at = Date(),
            route = 1,
            random_poi_id = 2
        )
        val mockResponse = Response.success(mockPost)

        whenever(apiService.getForumPostById(1)).thenReturn(mockResponse)

        val post = forumRepository.getPostById(1)

        assertEquals(mockPost, post)
        verify(apiService).getForumPostById(1)
    }

    @Test
    fun `getPostById throws exception when response is not successful`() = runTest {
        val mockErrorResponse =
            Response.error<PublicPost>(404, okhttp3.ResponseBody.create(null, "Not Found"))

        whenever(apiService.getForumPostById(1)).thenReturn(mockErrorResponse)

        val exception = assertFailsWith<Exception> { forumRepository.getPostById(1) }

        assertEquals("Failed to fetch posts: Not Found", exception.message)
        verify(apiService).getForumPostById(1)
    }

    @Test
    fun `addPost completes successfully when response is successful`() = runTest {
        val mockResponse = Response.success<ResponseBody>(null)
        val createdPost = CreatedPost(title = "Test Post", body = "Content", rating = 5)

        whenever(apiService.addForumPost(1, createdPost)).thenReturn(mockResponse)

        forumRepository.addPost(1, createdPost)

        verify(apiService).addForumPost(1, createdPost)
    }

}
