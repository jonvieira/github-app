package com.jonas.vieira.githubapp.data

import com.jonas.vieira.githubapp.data.model.UserDetailsModelResponse
import com.jonas.vieira.githubapp.data.repository.get_user_details.DetailsRepository
import com.jonas.vieira.githubapp.data.repository.get_user_details.DetailsRepositoryImpl
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
class GetUserDetailsRepositoryTest {

    private val service = mockk<GithubAPI>()

    @Mock
    private val repository: DetailsRepository = DetailsRepositoryImpl(service)
    private val usersDetail: UserDetailsModelResponse = mockk()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should return users detail list`() {
        runTest {
            coEvery { service.getDetail("login") } returns usersDetail

            val result = repository.getDetails("login")

            Assert.assertEquals(usersDetail, result)
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}