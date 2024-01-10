package com.jonas.vieira.githubapp.ui.details

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.jonas.vieira.githubapp.R
import com.jonas.vieira.githubapp.databinding.ActivityUserDetailsBinding
import com.jonas.vieira.githubapp.domain.model.DetailsModel
import com.jonas.vieira.githubapp.domain.model.UserRepoModel
import com.jonas.vieira.githubapp.ui.core.setImage
import com.jonas.vieira.githubapp.ui.home.HomeActivity.Companion.EXTRA_USER_URL
import com.jonas.vieira.githubapp.ui.state.UiState.Error
import com.jonas.vieira.githubapp.ui.state.UiState.Loading
import com.jonas.vieira.githubapp.ui.state.UiState.Success
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {

    private val viewModel: UserDetailsViewModel by viewModels()
    private val binding by lazy { ActivityUserDetailsBinding.inflate(layoutInflater) }
    private val adapter by lazy { UserRepoListAdapter(this) }

    private val positiveClick = { _: DialogInterface, _: Int -> getUserRepos() }
    private val negativeClick = { _: DialogInterface, _: Int -> finish() }

    private lateinit var userUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeStateDetailsViewModel()
        observeStateReposViewModel()
        getParametersExtra()
        configureRecycler()
        getUserDetail()
        getUserRepos()
    }

    private fun observeStateDetailsViewModel() {
        viewModel.stateDetails.observe(this) { uiState ->
            when (uiState) {
                is Loading -> handleLoadingState()
                is Success -> handleSuccessStateDetails(uiState.data)
                is Error -> handleErrorState(uiState.message)
            }
        }
    }

    private fun observeStateReposViewModel() {
        viewModel.stateRepos.observe(this) { uiState ->
            when (uiState) {
                is Loading -> handleLoadingState()
                is Success -> handleSuccessStateRepos(uiState.data)
                is Error -> handleErrorState(uiState.message)
            }
        }
    }

    private fun getParametersExtra() {
        userUrl = intent.getStringExtra(EXTRA_USER_URL).toString()
    }

    private fun configureRecycler() {
        binding.recyclerView.adapter = adapter
    }

    private fun getUserDetail() {
        binding.detailsUserProgressBar.visibility = View.VISIBLE
        if (checkIfURLIsEmpty()) handleErrorState("url nula") else viewModel.getUserDetail(userUrl)
    }

    private fun getUserRepos() {
        binding.recyclerUserProgressBar.visibility = View.VISIBLE
        if (checkIfURLIsEmpty()) handleErrorState("url nula") else viewModel.getUserRepoList(userUrl)
    }

    private fun checkIfURLIsEmpty(): Boolean = userUrl.isEmpty()

    private fun handleErrorState(message: String) {
        binding.detailsUserProgressBar.visibility = View.GONE

        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(getString(R.string.error_list_users_title))
            setMessage(message)
            setPositiveButton(getString(R.string.try_again_message), OnClickListener(positiveClick))
            setNegativeButton(getString(R.string.close_message), OnClickListener(negativeClick))
            show()
        }
    }

    private fun handleLoadingState() {
        binding.detailsUserProgressBar.visibility = View.VISIBLE
    }

    private fun handleSuccessStateDetails(userDetail: DetailsModel) {
        binding.detailsUserProgressBar.visibility = View.GONE
        bindUserInfo(userDetail)
    }

    private fun handleSuccessStateRepos(reposList: List<UserRepoModel>) {
        binding.recyclerUserProgressBar.visibility = View.GONE
        bindRepoList(reposList)
    }

    @SuppressLint("SetTextI18n")
    private fun bindUserInfo(userDetail: DetailsModel) {
        binding.tvName.text = userDetail.name
        binding.tvUserName.text = userDetail.username
        binding.tvCompanyName.text = userDetail.companyName
        binding.tvLocationAddress.text = userDetail.locationAddress
        binding.tvBlogAdress.text = userDetail.blog
        binding.tvFollowersNumber.text = userDetail.followers.toString()
        binding.tvFollowingNumber.text = userDetail.following.toString()
        binding.tvRepositories.text = userDetail.publicRepos.toString() + " Repositórios públicos"
        binding.imageViewUser.setImage(userDetail.avatar)

        binding.tvBio.apply {
            userDetail.bio.let {
                if (it.isEmpty()) {
                    this.visibility = View.GONE
                } else {
                    this.visibility = View.VISIBLE
                    this.text = userDetail.bio
                }
            }
        }

        val twitterAddress = userDetail.twitterAddress
        val tvTwitterAddress = binding.tvTwitterAddress
        val tvTwitter = binding.tvTwitter

        if (twitterAddress.isEmpty()) {
            tvTwitter.visibility = View.GONE
            tvTwitterAddress.visibility = View.GONE
        } else {
            tvTwitter.visibility = View.VISIBLE
            tvTwitterAddress.let {
                it.visibility = View.VISIBLE
                it.text = "@$twitterAddress"
            }
        }
    }

    private fun bindRepoList(reposList: List<UserRepoModel>) {
        adapter.repoList = reposList
    }
}