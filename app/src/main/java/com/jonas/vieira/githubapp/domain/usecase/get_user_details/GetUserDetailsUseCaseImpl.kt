package com.jonas.vieira.githubapp.domain.usecase.get_user_details

import com.jonas.vieira.githubapp.data.model.UserDetailsModelResponse
import com.jonas.vieira.githubapp.data.repository.get_user_details.DetailsRepository
import com.jonas.vieira.githubapp.domain.model.DetailsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserDetailsUseCaseImpl @Inject constructor(
    private val detailsRepository: DetailsRepository
) : GetUserDetailsUseCase {

    override suspend fun invoke(userLogin: String): DetailsModel {
        return withContext(Dispatchers.IO) {
            val result = detailsRepository.getDetails(userLogin)
            mapperToDomainLayer(result)
        }
    }

    private fun mapperToDomainLayer(user: UserDetailsModelResponse): DetailsModel {
        return DetailsModel(
            name = user.login,
            username = user.name,
            bio = user.bio ?: "",
            avatar = user.avatarUrl,
            companyName = user.company ?: "",
            locationAddress = user.location ?: "",
            blog = user.blog ?: "",
            twitterAddress = user.twitterUsername ?: "",
            followers = user.followers,
            following = user.following,
            publicRepos = user.publicRepos
        )
    }
}
