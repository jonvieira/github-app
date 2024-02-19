package com.jonas.vieira.githubapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jonas.vieira.githubapp.domain.model.UsersModel
import com.jonas.vieira.githubapp.domain.usecase.get_user.GetUsersUseCase
import com.jonas.vieira.githubapp.ui.home.HomeViewModel
import com.jonas.vieira.githubapp.ui.state.UiState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val getUsersUseCase: GetUsersUseCase = mockk()

    private lateinit var viewModel: HomeViewModel
    private val usersList: List<UsersModel> = mockk()

    private val uiState: Observer<UiState<List<UsersModel>>> = mockk(relaxed = true)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)

        viewModel = HomeViewModel(getUsersUseCase)
        viewModel.state.observeForever(uiState)
    }

    @Test
    fun `should change uiState to success when get users`() {
        runTest {
            coEvery { getUsersUseCase.invoke() } returns usersList

            viewModel.getUsers()

            verify(exactly = 1) { uiState.onChanged(viewModel.state.value as UiState.Success) }
        }
    }


    @Test
    fun `should change uiState to error when get users`() {
        runTest {
            coEvery { getUsersUseCase.invoke() }.throws(Throwable(""))

            viewModel.getUsers()

            verify(exactly = 1) { uiState.onChanged(viewModel.state.value as UiState.Error) }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }
}