package com.jonas.vieira.githubapp.data

import com.jonas.vieira.githubapp.data.model.UserRepoModelResponse
import com.jonas.vieira.githubapp.data.repository.get_user_repos.UserReposRepository
import com.jonas.vieira.githubapp.data.repository.get_user_repos.UserReposRepositoryImpl
import com.jonas.vieira.githubapp.data.service.GithubAPI
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class GetUserRepoRepositoryTest {

    private val service: GithubAPI = mockk()

    @Mock
    private val repository: UserReposRepository = UserReposRepositoryImpl(service = service)
    private val repoList: List<UserRepoModelResponse> = mockk()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should return user repositories list`() {
        runTest {
            coEvery { service.getUserRepos("login") } returns repoList

            val result = repository.getRepos("login")

            Assert.assertEquals(repoList, result)
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}