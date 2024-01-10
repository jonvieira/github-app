package com.jonas.vieira.githubapp.domain.usecase.get_user

import com.jonas.vieira.githubapp.domain.model.UsersModel

interface GetUsersUseCase {
    suspend operator fun invoke(): List<UsersModel>
}