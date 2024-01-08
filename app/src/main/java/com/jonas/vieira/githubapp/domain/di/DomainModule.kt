package com.jonas.vieira.githubapp.domain.di

import com.jonas.vieira.githubapp.domain.usecase.GetUsersUseCase
import com.jonas.vieira.githubapp.domain.usecase.GetUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun provideGetUsersUseCase(useCase: GetUsersUseCaseImpl): GetUsersUseCase
}