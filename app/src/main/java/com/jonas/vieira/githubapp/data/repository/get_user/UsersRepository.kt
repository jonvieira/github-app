package com.jonas.vieira.githubapp.data.repository.get_user

import com.jonas.vieira.githubapp.data.model.UsersModelResponse

interface UsersRepository {
    suspend fun getUsers(): List<UsersModelResponse>
}