package com.jonas.vieira.githubapp.data.repository.get_user

import com.jonas.vieira.githubapp.data.model.UsersModelResponse
import com.jonas.vieira.githubapp.data.service.GithubAPI
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val service: GithubAPI
) : UsersRepository {

    override suspend fun getUsers(): List<UsersModelResponse> {
        return service.getUsers()
    }
}