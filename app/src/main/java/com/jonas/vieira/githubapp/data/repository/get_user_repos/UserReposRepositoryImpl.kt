package com.jonas.vieira.githubapp.data.repository.get_user_repos

import com.jonas.vieira.githubapp.data.model.UserRepoModelResponse
import com.jonas.vieira.githubapp.data.service.GithubAPI
import javax.inject.Inject

class UserReposRepositoryImpl @Inject constructor(
    private val service: GithubAPI
) :
    UserReposRepository {
    override suspend fun getRepos(userLogin: String): List<UserRepoModelResponse> {
        return service.getUserRepos(userLogin)
    }
}