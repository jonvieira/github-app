package com.jonas.vieira.githubapp.data.di

import com.jonas.vieira.githubapp.data.service.GithubAPI
import com.jonas.vieira.githubapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    private val timeout = 6L

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(builderOkHttpClient())
            .addConverterFactory(createGsonConverterFactory())
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)

    private fun builderOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(builderLogInterceptor())
            .readTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }

    private fun builderLogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun createGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}