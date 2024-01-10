package com.jonas.vieira.githubapp.data.repository.get_user_details

import com.jonas.vieira.githubapp.data.model.UserDetailsModelResponse


interface DetailsRepository {
    suspend fun getDetails(userLogin: String): UserDetailsModelResponse
}