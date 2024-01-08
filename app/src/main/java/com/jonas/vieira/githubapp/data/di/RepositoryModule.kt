package com.jonas.vieira.githubapp.data.di

import com.jonas.vieira.githubapp.data.repository.get_user.UsersRepository
import com.jonas.vieira.githubapp.data.repository.get_user.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun providesUsersRepository(repository: UsersRepositoryImpl): UsersRepository

}