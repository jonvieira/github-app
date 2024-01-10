package com.jonas.vieira.githubapp.domain.usecase.get_user_repos

import com.jonas.vieira.githubapp.data.model.UserRepoModelResponse
import com.jonas.vieira.githubapp.data.repository.get_user_repos.UserReposRepository
import com.jonas.vieira.githubapp.domain.model.UserRepoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserRepoUseCaseImpl @Inject constructor(
    private val repository: UserReposRepository
) : GetUserRepoUseCase {

    override suspend fun invoke(userLogin: String): List<UserRepoModel> {
        return withContext(Dispatchers.IO) {
            val result = repository.getRepos(userLogin)
            mapperToDomainLayer(result)
        }
    }

    private fun mapperToDomainLayer(user: List<UserRepoModelResponse>): List<UserRepoModel> {
        return user.map {
            UserRepoModel(
                name = it.name ?: "",
                description = it.description ?: "",
                visibility = it.visibility ?: "",
                stargazersCount = it.stargazersCount ?: 0,
                watchersCount = it.watchersCount ?: 0,
                language = it.language ?: ""
            )
        }
    }
}