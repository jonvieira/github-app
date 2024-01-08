package com.jonas.vieira.githubapp.domain.usecase

import com.jonas.vieira.githubapp.domain.model.UsersModel

interface GetUsersUseCase {
    suspend operator fun invoke(): List<UsersModel>
}