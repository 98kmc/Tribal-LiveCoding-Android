package com.kmc.android_views_skeleton.di

import com.kmc.android_views_skeleton.modules.random_joke.RandomJokeRepositoryRepresentable
import com.kmc.android_views_skeleton.modules.random_joke.data.JokeRepository
import com.kmc.android_views_skeleton.utils.networking.api.JokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideRandomJokeRepository(api: JokeApi): RandomJokeRepositoryRepresentable =
        JokeRepository(api)
}