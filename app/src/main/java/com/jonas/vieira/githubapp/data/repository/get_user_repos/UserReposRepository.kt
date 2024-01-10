package com.jonas.vieira.githubapp.data.repository.get_user_repos

import com.jonas.vieira.githubapp.data.model.UserRepoModelResponse

interface UserReposRepository {
    suspend fun getRepos(userLogin: String): List<UserRepoModelResponse>
}