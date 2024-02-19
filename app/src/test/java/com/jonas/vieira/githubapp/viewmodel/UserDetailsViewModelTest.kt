package com.jonas.vieira.githubapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jonas.vieira.githubapp.domain.model.DetailsModel
import com.jonas.vieira.githubapp.domain.model.UserRepoModel
import com.jonas.vieira.githubapp.domain.usecase.get_user_details.GetUserDetailsUseCase
import com.jonas.vieira.githubapp.domain.usecase.get_user_repos.GetUserRepoUseCase
import com.jonas.vieira.githubapp.ui.details.UserDetailsViewModel
import com.jonas.vieira.githubapp.ui.state.UiState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDetailsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val getUserDetailsUseCase: GetUserDetailsUseCase = mockk()
    private val getUserRepoUseCase: GetUserRepoUseCase = mockk()

    private lateinit var viewModel: UserDetailsViewModel
    private val userDetail: DetailsModel = mockk()
    private val userRepos: List<UserRepoModel> = mockk()
    private val login = "Login"

    private val uiStateDetails: Observer<UiState<DetailsModel>> = mockk(relaxed = true)
    private val uiStateRepos: Observer<UiState<List<UserRepoModel>>> = mockk(relaxed = true)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)

        viewModel = UserDetailsViewModel(getUserDetailsUseCase, getUserRepoUseCase)
        viewModel.stateDetails.observeForever(uiStateDetails)
        viewModel.stateRepos.observeForever(uiStateRepos)
    }

    @Test
    fun `should change stateDetails to success when get details`() {
        runTest {
            coEvery { getUserDetailsUseCase.invoke(login) } returns userDetail

            viewModel.getUserDetail(login)

            verify(exactly = 1) {
                uiStateDetails.onChanged(viewModel.stateDetails.value as UiState.Success)
            }
        }
    }

    @Test
    fun `should change stateDetails to error when get details`() {
        runTest {
            coEvery { getUserDetailsUseCase.invoke(login) }.throws(Throwable(""))

            viewModel.getUserDetail(login)

            verify(exactly = 1) {
                uiStateDetails.onChanged(viewModel.stateDetails.value as UiState.Error)
            }
        }
    }

    @Test
    fun `should change stateRepos to success when get user repositories`() {
        runTest {
            coEvery { getUserRepoUseCase.invoke(login) } returns userRepos

            viewModel.getUserRepoList(login)

            verify(exactly = 1) {
                uiStateRepos.onChanged(viewModel.stateRepos.value as UiState.Success)
            }
        }
    }

    @Test
    fun `should change stateRepos to error when get user repositories`() {
        runTest {
            coEvery { getUserRepoUseCase.invoke(login) }.throws(Throwable(""))

            viewModel.getUserRepoList(login)

            verify(exactly = 1) {
                uiStateRepos.onChanged(viewModel.stateRepos.value as UiState.Error)
            }
        }
    }
}