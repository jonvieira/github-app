package com.jonas.vieira.githubapp.domain.usecase.get_user_repos

import com.jonas.vieira.githubapp.domain.model.UserRepoModel

interface GetUserRepoUseCase {
    suspend operator fun invoke(userLogin: String): List<UserRepoModel>
}