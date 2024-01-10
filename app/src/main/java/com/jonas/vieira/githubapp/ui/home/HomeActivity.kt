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

    private fun configureRecyclerView() {
        binding.recyclerViewUsersList.adapter = adapter
    }

    private fun handleErrorState(message: String) {
        showLoading(false)
        showAlertDialog(message)
    }

    private fun handleLoadingState() = showLoading(true)

    private fun handleSuccessState(data: List<UsersModel>) {
        showLoading(false)
        usersList = data
        adapter.usersList = data
    }

    private fun handleClickUserList() = UsersListAdapter.OnClickListener {
        goToUsersDetailActivity(it)
    }

    private fun goToUsersDetailActivity(it: UsersModel) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(EXTRA_USER_URL, it.login)
        startActivity(intent)
    }

    private fun searchUser() {
        binding.searchViewUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterSearchUser(newText)
                return true
            }
        })
    }

    private fun filterSearchUser(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<UsersModel>()
            for (i in usersList) {
                if (i.login.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(
                    this@HomeActivity, getString(R.string.user_not_find),
                    Toast.LENGTH_SHORT
                )
                    .show()
                adapter.usersList = emptyList()
            } else {
                adapter.usersList = filteredList
            }
        }
    }

    private fun showAlertDialog(message: String) {

        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(getString(R.string.error_list_users_title))
            setMessage(message)
            setPositiveButton(getString(R.string.try_again_message), OnClickListener(positiveClick))
            setNegativeButton(getString(R.string.close_message), OnClickListener(negativeClick))
            show()
        }
    }

    private fun showLoading(show: Boolean) {
        if (show)
            binding.homeProgressBar.visibility = View.VISIBLE
        else
            binding.homeProgressBar.visibility = View.GONE
    }

    companion object {
        const val EXTRA_USER_URL = "EXTRA_USER_URL"
    }
}