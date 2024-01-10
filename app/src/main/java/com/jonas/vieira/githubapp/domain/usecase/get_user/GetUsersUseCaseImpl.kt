package com.jonas.vieira.githubapp.domain.usecase.get_user

import com.jonas.vieira.githubapp.data.model.UsersModelResponse
import com.jonas.vieira.githubapp.data.repository.get_user.UsersRepository
import com.jonas.vieira.githubapp.domain.model.UsersModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUsersUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : GetUsersUseCase {

    override suspend fun invoke(): List<UsersModel> {
        return withContext(Dispatchers.IO) {
            val result = usersRepository.getUsers()
            mapperToDomainLayer(result)
        }
    }

    private fun mapperToDomainLayer(result: List<UsersModelResponse>): List<UsersModel> {
        return result.map {
            UsersModel(
                userId = it.id,
                login = it.login,
                avatarUrl = it.avatar,
                htmlUrl = it.htmlUrl
            )
        }
    }
}