package com.jonas.vieira.githubapp.domain.usecase.get_user_details

import com.jonas.vieira.githubapp.domain.model.DetailsModel


interface GetUserDetailsUseCase {
    suspend operator fun invoke(userLogin: String): DetailsModel
}