package com.karmakarin.chatu.di.repo

import com.karmakarin.chatu.data.repo.ChatRepoImpl
import com.karmakarin.chatu.data.repo.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTaskRepo(taskRepository: ChatRepoImpl) : ChatRepository
}