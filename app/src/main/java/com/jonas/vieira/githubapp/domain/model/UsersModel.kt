package com.jonas.vieira.githubapp.domain.model

data class UsersModel(
    val userId: Int,
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String
)