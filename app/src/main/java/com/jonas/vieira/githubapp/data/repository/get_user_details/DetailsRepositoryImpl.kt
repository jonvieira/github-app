package com.jonas.vieira.githubapp.data.repository.get_user_details

import com.jonas.vieira.githubapp.data.model.UserDetailsModelResponse
import com.jonas.vieira.githubapp.data.service.GithubAPI
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val service: GithubAPI
) : DetailsRepository {

    override suspend fun getDetails(userLogin: String): UserDetailsModelResponse {
        return service.getDetail(userLogin)
    }
}