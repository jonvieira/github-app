package com.jonas.vieira.githubapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonas.vieira.githubapp.domain.model.DetailsModel
import com.jonas.vieira.githubapp.domain.model.UserRepoModel
import com.jonas.vieira.githubapp.domain.usecase.get_user_details.GetUserDetailsUseCase
import com.jonas.vieira.githubapp.domain.usecase.get_user_repos.GetUserRepoUseCase
import com.jonas.vieira.githubapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getUserRepoUseCase: GetUserRepoUseCase
) : ViewModel() {

    private val _stateDetails = MutableLiveData<UiState<DetailsModel>>()
    val stateDetails: LiveData<UiState<DetailsModel>> get() = _stateDetails

    private val _stateRepos = MutableLiveData<UiState<List<UserRepoModel>>>()
    val stateRepos: LiveData<UiState<List<UserRepoModel>>> get() = _stateRepos

    fun getUserDetail(userUrl: String) = viewModelScope.launch {
        _stateDetails.value = UiState.Loading()

        try {
            val response = getUserDetailsUseCase.invoke(userUrl)
            response.let {
                _stateDetails.value = UiState.Success(it)
            }

        } catch (error: Throwable) {
            _stateDetails.value = UiState.Error(error.message.toString())
        }
    }

    fun getUserRepoList(userUrl: String) = viewModelScope.launch {
        _stateRepos.value = UiState.Loading()

        try {
            val response = getUserRepoUseCase.invoke(userUrl)
            response.let {
                _stateRepos.value = UiState.Success(it)
            }

        } catch (error: Throwable) {
            _stateRepos.value = UiState.Error(error.message.toString())
        }
    }
}