package com.karmakarin.chatu.di.db

import com.karmakarin.chatu.data.model.ChatEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideRealmConfig(): RealmConfiguration {
        return RealmConfiguration.create(
            schema = setOf(
                ChatEntity::class
            )
        )
    }

    @Provides
    @Singleton
    fun provideRealm(configuration: RealmConfiguration): Realm {
        return Realm.open(configuration)
    }
}