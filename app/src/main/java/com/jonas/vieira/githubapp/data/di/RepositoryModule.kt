package com.jonas.vieira.githubapp.data.di

import com.jonas.vieira.githubapp.data.repository.get_user.UsersRepository
import com.jonas.vieira.githubapp.data.repository.get_user.UsersRepositoryImpl
import com.jonas.vieira.githubapp.data.repository.get_user_details.DetailsRepository
import com.jonas.vieira.githubapp.data.repository.get_user_details.DetailsRepositoryImpl
import com.jonas.vieira.githubapp.data.repository.get_user_repos.UserReposRepository
import com.jonas.vieira.githubapp.data.repository.get_user_repos.UserReposRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun providesUsersRepository(repository: UsersRepositoryImpl): UsersRepository

    @Binds
    fun providesUserDetailsRepository(repository: DetailsRepositoryImpl): DetailsRepository

    @Binds
    fun providesUserReposRepository(repository: UserReposRepositoryImpl): UserReposRepository
}