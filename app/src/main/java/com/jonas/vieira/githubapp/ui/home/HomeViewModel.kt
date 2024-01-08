package com.jonas.vieira.githubapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonas.vieira.githubapp.domain.model.UsersModel
import com.jonas.vieira.githubapp.domain.usecase.GetUsersUseCase
import com.jonas.vieira.githubapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UiState<List<UsersModel>>>()
    val state: LiveData<UiState<List<UsersModel>>> get() = _state

    fun getUsers() = viewModelScope.launch {
        _state.value = UiState.Loading()

        try {
            val response = getUsersUseCase.invoke()
            response.let {
                _state.value = UiState.Success(it)
            }

        } catch (error: Throwable) {
            _state.value = UiState.Error(error.message.toString())
        }
    }
}