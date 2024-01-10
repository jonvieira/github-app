package com.jonas.vieira.githubapp.domain.model

data class UserRepoModel(
    val name: String,
    val description: String,
    val visibility: String,
    val stargazersCount: Int,
    val watchersCount: Int,
    val language: String
)


