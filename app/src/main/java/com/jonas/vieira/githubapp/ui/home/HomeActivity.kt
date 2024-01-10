package com.jonas.vieira.githubapp.ui.home

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.jonas.vieira.githubapp.R
import com.jonas.vieira.githubapp.databinding.ActivityHomeBinding
import com.jonas.vieira.githubapp.domain.model.UsersModel
import com.jonas.vieira.githubapp.ui.details.UserDetailsActivity
import com.jonas.vieira.githubapp.ui.state.UiState.Error
import com.jonas.vieira.githubapp.ui.state.UiState.Loading
import com.jonas.vieira.githubapp.ui.state.UiState.Success
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { UsersListAdapter(this, handleClickUserList()) }

    private val positiveClick = { _: DialogInterface, _: Int -> getUsers() }
    private val negativeClick = { _: DialogInterface, _: Int -> finish() }

    private lateinit var usersList: List<UsersModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configureRecyclerView()
        observeStateViewModel()
        searchUser()
        getUsers()
    }

    private fun observeStateViewModel() {
        viewModel.state.observe(this) { uiState ->
            when (uiState) {
                is Loading -> handleLoadingState()
                is Success -> handleSuccessState(uiState.data)
                is Error -> handleErrorState(uiState.message)
            }
        }
    }

    private fun getUsers() {
        viewModel.getUsers()
    }

}