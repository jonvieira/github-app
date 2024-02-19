package com.jonas.vieira.githubapp.data

import com.jonas.vieira.githubapp.data.model.UsersModelResponse
import com.jonas.vieira.githubapp.data.repository.get_user.UsersRepository
import com.jonas.vieira.githubapp.data.repository.get_user.UsersRepositoryImpl
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
class GetUserRepositoryTest {

    private val service: GithubAPI = mockk()

    @Mock
    private val repository: UsersRepository = UsersRepositoryImpl(service)
    private val usersList: List<UsersModelResponse> = mockk()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should return user list`() {
        runTest {
            coEvery { service.getUsers() } returns usersList

            val result = repository.getUsers()

            Assert.assertEquals(usersList, result)
        }
    }


    @After
    fun tearDown() {
        unmockkAll()
    }
}