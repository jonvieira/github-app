package com.jonas.vieira.githubapp.domain.model

data class DetailsModel(
    val name: String,
    val username: String,
    val bio: String,
    val avatar: String,
    val companyName: String,
    val locationAddress: String,
    val blog: String,
    val twitterAddress: String,
    val followers: Int,
    val following: Int,
    val publicRepos: Int
)
