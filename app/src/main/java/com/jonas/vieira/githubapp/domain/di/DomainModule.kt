package com.jonas.vieira.githubapp.domain.di

import com.jonas.vieira.githubapp.domain.usecase.get_user.GetUsersUseCase
import com.jonas.vieira.githubapp.domain.usecase.get_user.GetUsersUseCaseImpl
import com.jonas.vieira.githubapp.domain.usecase.get_user_details.GetUserDetailsUseCase
import com.jonas.vieira.githubapp.domain.usecase.get_user_details.GetUserDetailsUseCaseImpl
import com.jonas.vieira.githubapp.domain.usecase.get_user_repos.GetUserRepoUseCase
import com.jonas.vieira.githubapp.domain.usecase.get_user_repos.GetUserRepoUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun provideGetUsersUseCase(useCase: GetUsersUseCaseImpl): GetUsersUseCase

    @Binds
    fun provideGetUserDetailsUseCase(useCase: GetUserDetailsUseCaseImpl): GetUserDetailsUseCase

    @Binds
    fun provideGetUserReposUseCase(useCase: GetUserRepoUseCaseImpl): GetUserRepoUseCase
}