package com.jonas.vieira.githubapp.data.service

import com.jonas.vieira.githubapp.data.model.UserDetailsModelResponse
import com.jonas.vieira.githubapp.data.model.UserRepoModelResponse
import com.jonas.vieira.githubapp.data.model.UsersModelResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("/users")
    suspend fun getUsers(): List<UsersModelResponse>

    @GET("/users/{login}")
    suspend fun getDetail(@Path("login") userLogin: String): UserDetailsModelResponse

    @GET("/users/{login}/repos")
    suspend fun getUserRepos(@Path("login") userLogin: String): List<UserRepoModelResponse>
}