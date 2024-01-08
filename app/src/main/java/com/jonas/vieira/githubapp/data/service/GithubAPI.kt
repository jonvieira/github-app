package com.jonas.vieira.githubapp.data.service

import com.jonas.vieira.githubapp.data.model.UsersModelResponse
import retrofit2.http.GET

interface GithubAPI {

    @GET("/users")
    suspend fun getUsers(): List<UsersModelResponse>

}